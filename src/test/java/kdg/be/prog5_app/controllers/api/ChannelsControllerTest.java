package kdg.be.prog5_app.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.repositories.ChannelRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ChannelsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private long createdChannelId;

    //    private long createdIssueAssignmentId;
    @BeforeEach
    public void setupEach() {
        var createdChannel = channelRepository.save(
                new Channel("Dummy channel", LocalDate.now(), 45));
        createdChannelId = createdChannel.getId();
    }

    @AfterEach
    public void tearDownEach() {
        channelRepository.deleteById(createdChannelId);
    }

    @Test
    public void getVideosOfChannelShouldReturnNotFoundForNonExistentChannel() throws Exception {
        mockMvc.perform(
                        get("/api/channels/{channelId}/videos", 200)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getVideosOfChannelShouldReturnNoContentIfNoVideosForChannel() throws Exception {
        mockMvc.perform(
                        get("/api/channels/{channelId}/videos", 2)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getVideosOfChannelShouldReturnOkWithVideosForChannel() throws Exception {
        mockMvc.perform(
                        get("/api/channels/{channelId}/videos", 1)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].title",
                        Matchers.containsInAnyOrder("Next.js 13-The Basics", "Time is Relative, even in JS")))
                .andDo(print());
    }

//    @Test
//    public void deleteChannelIsNotAllowedIfNotSignedIn() throws Exception {
//        mockMvc.perform(
//                        delete("/api/channels/{channelId}", createdChannelId)
//                                .with(csrf()))
//                .andExpect(status().isUnauthorized());
//    }

    @Test
    @WithUserDetails("anna")
    public void deleteChannelIsAllowedIfAdmin() throws Exception {
        mockMvc.perform(
                        delete("/api/channels/{channelId}", createdChannelId)
                                .with(csrf()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/channels/{channelId}", createdChannelId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}