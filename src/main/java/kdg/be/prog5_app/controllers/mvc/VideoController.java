package kdg.be.prog5_app.controllers.mvc;

import kdg.be.prog5_app.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public String getVideosPage(Model model) {
        model.addAttribute("videos", videoService.getVideos());
        return "video/Videos";
    }

    @GetMapping("/add")
    public String getAddVideoPage() {
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
        return "redirect:/videos";
    }
}
