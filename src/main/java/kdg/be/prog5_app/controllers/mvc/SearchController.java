package kdg.be.prog5_app.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {
    @GetMapping("/search-channels")
    public String searchChannels() {
        return "channel/search-channels";
    }

    @GetMapping("/search-videos")
    public String searchVideos() {
        return "video/search-videos";
    }

}
