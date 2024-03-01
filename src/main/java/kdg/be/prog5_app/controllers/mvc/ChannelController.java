package kdg.be.prog5_app.controllers.mvc;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelService channelService;

//    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public String getChannelsPage(Model model) {
        model.addAttribute("channels", channelService.getChannels());
        return "channel/Channels";
    }

    @PostMapping("delete/{id}")
    public String deleteChannel(@PathVariable(value = "id") Long id) {
        channelService.deleteChannel(id);
        return "redirect:/channels";
    }

    @GetMapping("/{id}")
    public String getChannel(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("videos", channelService.findVideosByChannelId(id));
        model.addAttribute("channel", channelService.getChannel(id));
        return "channel/details";
    }

    @GetMapping("/add")
    public String getChannelPage(Model model) {
        model.addAttribute("getPage", new Channel());
        return "channel/AddChannel";
    }

    @PostMapping("create")
    public String addChannel(@Valid @ModelAttribute Channel channel, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("getPage", new Channel());
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "channel/AddChannel";
        }
        channelService.addChannel(channel);
        model.addAttribute("getPage", new Channel());
        model.addAttribute("message", "Channel added!");
        return "channel/AddChannel";
    }
}
