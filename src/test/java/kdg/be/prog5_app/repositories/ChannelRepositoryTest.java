package kdg.be.prog5_app.repositories;

import jakarta.validation.ConstraintViolationException;
import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ChannelRepositoryTest {

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void findByIdWithVideosShouldFetchRelatedData() {
        // Arrange

        // Act
        var channelOptional = channelRepository.findByIdWithVideos(1);

        // Assert
        assertTrue(channelOptional.isPresent());
        var channel = channelOptional.get();
        assertEquals(1, channel.getId());
        assertEquals("Beyond Fireship", channel.getName());
        assertEquals(LocalDate.of(2022, 9, 4),
                channel.getDate());
        assertEquals(329000, channel.getSubscribers());
    }

    @Test
    public void channelNameShouldNotBeBlank() {
        // Arrange

        // Act
        Executable executable = () -> channelRepository.save(new Channel(
                "", LocalDate.of(2022, 9, 4), 10
        ));

        // Assert
        assertThrows(ConstraintViolationException.class, executable);
    }

}