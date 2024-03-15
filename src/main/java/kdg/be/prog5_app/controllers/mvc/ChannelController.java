package kdg.be.prog5_app.controllers.mvc;

import kdg.be.prog5_app.services.ChannelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public String getChannelsPage(Model model) {
        model.addAttribute("channels", channelService.getChannels());
        return "channel/Channels";
    }

    @GetMapping("/{id}")
    public String getChannel(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("channel", channelService.getChannel(id));
        return "channel/details";
    }

    @GetMapping("/add")
    public String getAddChannelPage() {
        return "channel/AddChannel";
    }
}
