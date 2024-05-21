package kdg.be.prog5_app.controllers.mvc;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.repositories.ChannelRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ChannelControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ChannelRepository channelRepository;

    @Test
    void getChannelsPageShouldReturnChannelsViewWithChannelsData() throws Exception {
        List<Channel> channels = channelRepository.findAll();

        Channel channel1 = channels.stream()
                .filter(channel -> "Beyond Fireship".equals(channel.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Expected channel not found: Beyond Fireship"));

        Channel channel2 = channels.stream()
                .filter(channel -> "Tech Acad".equals(channel.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Expected channel not found: Tech Acad"));

        mockMvc.perform(get("/channels"))
                .andExpect(status().isOk())
                .andExpect(view().name("channel/Channels"))
                .andExpect(model().attribute("channels", hasSize(2)))
                .andExpect(model().attribute("channels", containsInAnyOrder(
                        samePropertyValuesAs(channel1, "id", "videos"),
                        samePropertyValuesAs(channel2, "id", "videos")
                )));
    }

    @Test
    @WithUserDetails("anna")
    void getChannelShouldReturnChannelDetailsViewWithChannelData() throws Exception {
        // Retrieve an existing channel from the repository
        Channel existingChannel = channelRepository.findById(1L)
                .orElseThrow(() -> new AssertionError("Expected channel not found: id=1"));

        mockMvc.perform(get("/channels/{id}", existingChannel.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("channel/details"))
                .andExpect(model().attribute("channel", samePropertyValuesAs(existingChannel, "id", "videos")));
    }

    @Test
    @WithUserDetails("anna")
    void getAddChannelPageAsAdmin() throws Exception {
        mockMvc.perform(get("/channels/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("channel/AddChannel"));
    }

    @Test
    @WithUserDetails("walter")
    void getAddChannelPageAsNonAdmin() throws Exception {
        mockMvc.perform(get("/channels/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("unauthenticated"));
    }
}


