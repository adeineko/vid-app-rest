package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.repositories.ChannelRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
class ChannelServiceUnitTest {
    @Autowired
    private ChannelService channelService;
    @MockBean
    private ChannelRepository channelRepository;

    @Test
    void updateChannelNameAndSubscribersFailsWhenChannelDoesntExist() {
        // Arrange
        given(channelRepository.findById(7777L)).willReturn(Optional.empty());

        // Act
        var updateSucceeded = channelService.changeChannelNameAndSubscribers(7777L, "test name", 34);

        // Assert
        assertFalse(updateSucceeded);
        verify(channelRepository, never()).save(any());
    }

    @Test
    void updateChannelNameAndSubscribersSucceedsWhenChannelExists() {
        // Arrange
        var channel = new Channel("name", LocalDate.of(2022, 12, 5), 56);
        channel.setId(7777);
        given(channelRepository.findById(7777L)).willReturn(Optional.of(channel));

        // Act
        var updateSucceeded = channelService.changeChannelNameAndSubscribers(7777L, "test name", 34);

        // Assert
        assertTrue(updateSucceeded);
        ArgumentCaptor<Channel> channelCaptor = ArgumentCaptor.forClass(Channel.class);
        verify(channelRepository).save(channelCaptor.capture());
        assertEquals("test name", channelCaptor.getValue().getName());
        assertEquals(34, channelCaptor.getValue().getSubscribers());
    }

    @Test
    void removeChannelFailsWhenChannelDoesntExist() {
        // Arrange
        given(channelRepository.findById(7777L)).willReturn(Optional.empty());

        // Act
        var deleteSucceeded = channelService.removeChannel(7777L);

        // Assert
        assertFalse(deleteSucceeded);
        verify(channelRepository, never()).save(any());
    }

//    @Test
//    void deleteChannelSucceedsWhenChannelExists() {
//        // Arrange
//        var channel = new Channel("name", LocalDate.of(2022, 12, 5), 56);
//        channel.setId(7777);
//        given(channelRepository.findById(7777L)).willReturn(Optional.of(channel));
//
//        // Act
//        var deleteSucceeded = channelService.removeChannel(7777L);
//        // Assert
//        assertTrue(deleteSucceeded);
//        ArgumentCaptor<Channel> channelCaptor = ArgumentCaptor.forClass(Channel.class);
//        verify(channelRepository).save(channelCaptor.capture());
//
//    }
}