package kdg.be.prog5_app.controllers.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kdg.be.prog5_app.controllers.api.dto.ChannelDto;
import kdg.be.prog5_app.controllers.api.dto.UpdateChannelDto;
import kdg.be.prog5_app.controllers.api.dto.VideoDto;
import kdg.be.prog5_app.domain.ChannelVideo;
import kdg.be.prog5_app.exceptions.UserNotFoundException;
import kdg.be.prog5_app.services.ChannelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kdg.be.prog5_app.domain.UserRole.ADMIN;

@RestController
@RequestMapping("/api/channels")
public class ChannelsController {
    private final ChannelService channelService;
    private final ModelMapper modelMapper;

    @Autowired
    public ChannelsController(ChannelService channelService, ModelMapper modelMapper) {
        this.channelService = channelService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<ChannelDto> addChannel(@RequestBody @Valid ChannelDto channelDto,
                                          HttpServletRequest request) {
        if (!request.isUserInRole(ADMIN.getCode())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            var createdChannel = channelService.addChannel(
                    channelDto.getName(),
                    channelDto.getDate(),
                    channelDto.getSubscribers()
            );
            return new ResponseEntity<>(
                    modelMapper.map(createdChannel, ChannelDto.class),
                    HttpStatus.CREATED
            );
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/{id}")
    ResponseEntity<ChannelDto> getOneChannel(@PathVariable("id") long channelId) {
        var channel = channelService.getChannel(channelId);
        if (channel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(
                modelMapper.map(channel, ChannelDto.class));
    }

    @GetMapping("/{id}/videos")
    ResponseEntity<List<VideoDto>> getChannelWithVideos(@PathVariable("id") long channelId) {
        var channel = channelService.getChannelWithVideos(channelId);
        if (channel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (channel.getVideos().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(channel.getVideos()
                .stream()
                .map(ChannelVideo::getVideo)
                .map(video -> modelMapper.map(video, VideoDto.class))
                .toList());
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteChannel(@PathVariable("id") long channelId,
                                       HttpServletRequest request) {
        if (!request.isUserInRole(ADMIN.getCode())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (channelService.removeChannel(channelId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("{id}")
    ResponseEntity<Void> changeChannel(@PathVariable("id") long channelId,
                                       @RequestBody @Valid UpdateChannelDto updateChannelDto,
                                       HttpServletRequest request) {
        if (!request.isUserInRole(ADMIN.getCode())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (channelService.changeChannelName(channelId, updateChannelDto.getName(), updateChannelDto.getSubscribers())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
