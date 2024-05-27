package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.repositories.ChannelRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChannelServiceTest {
    @Autowired
    private ChannelService channelService;

    @Autowired
    private ChannelRepository channelRepository;

    private long testChannelsId;

    @BeforeAll
    void setup() {
        var testIssue = channelRepository.save(new Channel("Beyond Fireship", LocalDate.parse("2022-09-04"), 345266));
        testChannelsId = testIssue.getId();
    }

    @AfterAll
    void tearDown() {
        channelRepository.deleteById(testChannelsId);
    }

    @Test
    void changeChannelAndSubscribersNameShouldReturnTrueForExistingIssueAndUpdateSaidIssue() {
        // Arrange
        var createdChannel = channelRepository.save(new Channel("Beyond Fireship", LocalDate.parse("2022-09-04"), 345266));

        // Act
        var result = channelService.changeChannelNameAndSubscribers(
                createdChannel.getId(), "Name", 78);

        // Assert
        assertTrue(result);
        assertEquals("Name",
                channelRepository.findById(createdChannel.getId()).get().getName());

        // (cleanup)
        channelRepository.deleteById(createdChannel.getId());
    }
}