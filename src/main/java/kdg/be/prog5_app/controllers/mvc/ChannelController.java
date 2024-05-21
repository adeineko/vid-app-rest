package kdg.be.prog5_app.controllers.mvc;

import jakarta.servlet.http.HttpServletRequest;
import kdg.be.prog5_app.security.CustomUserDetails;
import kdg.be.prog5_app.services.ChannelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static kdg.be.prog5_app.domain.UserRole.ADMIN;

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
    public String getAddChannelPage(@AuthenticationPrincipal CustomUserDetails user,
                                    HttpServletRequest request) {
        if (user != null && request.isUserInRole(ADMIN.getCode())) {
            return "channel/AddChannel";
        }
        return "unauthenticated";
    }


}
