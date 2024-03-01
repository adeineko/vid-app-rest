package kdg.be.prog5_app.controllers.mvc;

import jakarta.validation.Valid;
import kdg.be.prog5_app.domain.Video;
import kdg.be.prog5_app.services.ChannelService;
import kdg.be.prog5_app.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;
    private final ChannelService channelService;

    @Autowired
    public VideoController(VideoService videoServiceJdbc, ChannelService channelServiceJdbc) {
        this.videoService = videoServiceJdbc;
        this.channelService = channelServiceJdbc;
    }


    @GetMapping
    public String getVideosPage(Model model) {
        model.addAttribute("video", videoService.showVideos());
        return "video/Videos";
    }

    @GetMapping("/add")
    public String getVideoPage(Model model) {
        model.addAttribute("getPage", new Video());
        model.addAttribute("channels", channelService.getChannels());
//        model.addAttribute("addChannel", channelServiceJdbc.showChannels());
        return "video/AddVideos";
    }

    @PostMapping("create")
    public String addVideo(@Valid @ModelAttribute Video video, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("channels", channelService.getChannels());
            model.addAttribute("getPage", new Video());
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "video/AddVideos";
        }
        videoService.addVideo(video);
        // videoService.saveChannelId(video);
        model.addAttribute("channels", channelService.getChannels());
        model.addAttribute("getPage", new Video());
        model.addAttribute("message", "Video added!");
        return "video/AddVideos";
    }

    @GetMapping("/{id}")
    public String getVideo(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("video", videoService.getVideo(id));
        return "video/details";
    }

    @PostMapping("delete/{id}")
    public String deleteVideo(@PathVariable(value = "id") Long id) {
        videoService.deleteVideo(id);
//        videoService.deleteVideoFromChannelVideo(id);
        return "redirect:/videos";
    }
}
