package kdg.be.prog5_app.controllers.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kdg.be.prog5_app.controllers.api.dto.NewVideoDto;
import kdg.be.prog5_app.controllers.api.dto.VideoDto;
import kdg.be.prog5_app.security.CustomUserDetails;
import kdg.be.prog5_app.services.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static kdg.be.prog5_app.domain.UserRole.ADMIN;

@RestController
@RequestMapping("/api/videos")
public class VideosController {
    private final VideoService videoService;
    private final ModelMapper modelMapper;

    @Autowired
    public VideosController(VideoService videoService, ModelMapper modelMapper) {
        this.videoService = videoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    ResponseEntity<VideoDto> addVideo(@RequestBody @Valid NewVideoDto newVideoDto) {
        var createdVideo = videoService.addVideo(
                newVideoDto.getTitle(),
                newVideoDto.getViews(),
                newVideoDto.getLink(),
                newVideoDto.getGenre()
        );
        return new ResponseEntity<>(
                modelMapper.map(createdVideo, VideoDto.class),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteVideo(@PathVariable("id") long videoId) {
        if (videoService.removeVideo(videoId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
