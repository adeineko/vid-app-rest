package kdg.be.prog5_app.controllers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void getVideosOfIssueShouldReturnNoContentIfNoVideosForChannel() throws Exception {
        mockMvc.perform(
                        get("/api/channels/{channelId}/videos", 2)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getVideosOfIssueShouldReturnOkWithVideosForChannel() throws Exception {
        mockMvc.perform(
                        get("/api/channels/{channelId}/videos", 1)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].title",
                        Matchers.containsInAnyOrder("Next.js 13-The Basics", "Time is Relative, even in JS")))
                .andDo(print());
    }

}