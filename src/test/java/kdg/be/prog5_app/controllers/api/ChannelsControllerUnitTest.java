package kdg.be.prog5_app.controllers.api;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.UserRole;
import kdg.be.prog5_app.security.CustomUserDetails;
import kdg.be.prog5_app.services.ChannelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ChannelsControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelService;

    private long channelId;
    private CustomUserDetails userDetails;

    @BeforeEach
    public void setupEach() {
        channelId = 7777L;
        long userId = 8888L;
        userDetails = new CustomUserDetails("Ross", "pwd",
                List.of(new SimpleGrantedAuthority(UserRole.ADMIN.getCode())),
                userId);
    }
    @AfterEach
    void tearDown() {
        reset(channelService);
    }
    @Test
    public void deleteChannelShouldBeUnauthorizedIfNotSignedIn() throws Exception {
        mockMvc.perform(delete("/api/channels/{id}", 7777L)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());

        verify(channelService, never()).removeChannel(7777L);
    }


    @Test
    public void deleteChannelShouldBeAllowedIfAdmin() throws Exception {
        given(channelService.removeChannel(channelId)).willReturn(true);

        mockMvc.perform(delete("/api/channels/{id}", channelId)
                        .with(user(userDetails))
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(channelService).removeChannel(channelId);
    }
//TODO: check this test
    @Test
    public void deleteChannelShouldBeNotFoundIfChannelDoesntExist() throws Exception {
        given(channelService.removeChannel(channelId)).willReturn(false);

        mockMvc.perform(delete("/api/channels/{id}", channelId)
                        .with(user(userDetails))
                        .with(csrf()))
                .andExpect(status().isNotFound());

        verify(channelService).removeChannel(channelId);
    }
}