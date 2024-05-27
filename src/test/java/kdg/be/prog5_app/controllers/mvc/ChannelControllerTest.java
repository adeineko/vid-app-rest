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
        List<Channel> channels = Arrays.asList(
                new Channel("Beyond Fireship", LocalDate.parse("2022-09-04"), 329000),
                new Channel("Tech Acad", LocalDate.parse("2017-03-28"), 75000),
                new Channel("Coding Wizards", LocalDate.parse("2023-01-15"), 480000),
                new Channel("Data Science Insights", LocalDate.parse("2020-08-20"), 105000),
                new Channel("Game Dev Hub", LocalDate.parse("2018-12-10"), 210000),
                new Channel("AI Explorers", LocalDate.parse("2021-06-28"), 145000)
        );

        // Perform the mockMvc request
        mockMvc.perform(get("/channels"))
                .andExpect(status().isOk())
                .andExpect(view().name("channel/Channels"))
                .andExpect(model().attribute("channels", hasSize(6)))
                .andExpect(model().attribute("channels", containsInAnyOrder(
                        samePropertyValuesAs(channels.get(0), "id", "videos"),
                        samePropertyValuesAs(channels.get(1), "id", "videos"),
                        samePropertyValuesAs(channels.get(2), "id", "videos"),
                        samePropertyValuesAs(channels.get(3), "id", "videos"),
                        samePropertyValuesAs(channels.get(4), "id", "videos"),
                        samePropertyValuesAs(channels.get(5), "id", "videos")
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


