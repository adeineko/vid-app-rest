package kdg.be.prog5_app.controllers.mvc;

import kdg.be.prog5_app.services.ChannelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/channels-csv")
public class ChannelCsvController {
    private final ChannelService channelService;

    public ChannelCsvController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public ModelAndView uploadCsvChannels() {
        var mav = new ModelAndView("channel/channels-csv");
        mav.addObject("inProgress", false);
        return mav;
    }

    @PostMapping
    public ModelAndView uploadCsv(
            @RequestParam("channels-csv") MultipartFile file)
            throws IOException {
        var mav = new ModelAndView("channel/channels-csv");
        channelService.processChannelsCsv(file.getInputStream());
        mav.addObject("inProgress", true);

        return mav;
    }

}
