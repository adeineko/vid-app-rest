package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.repositories.ChannelRepository;
import kdg.be.prog5_app.repositories.ChannelVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static org.hibernate.sql.results.LoadingLogger.LOGGER;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelVideoRepository channelVideoRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository, ChannelVideoRepository channelVideoRepository) {
        this.channelRepository = channelRepository;
        this.channelVideoRepository = channelVideoRepository;
    }

    public Channel getChannel(Long id) {
        return channelRepository.findById(id).orElse(null);
    }


    @Transactional
    @CacheEvict(value = "search-channel", allEntries = true)
    public boolean removeChannel(long channelId) {
        var channel = channelRepository.findByIdWithVideos(channelId);
        if (channel.isEmpty()) {
            return false;
        }
        channelVideoRepository.deleteAll(channel.get().getVideos());
        channelRepository.deleteById(channelId);
        return true;
    }

    public List<Channel> getChannels() {
        return channelRepository.showAllChannels();
    }

    public Channel getChannelWithVideos(long channelId) {
        return channelRepository.findByIdWithVideos(channelId).orElse(null);
    }

    @CacheEvict(value = "search-channel", allEntries = true)
    public Channel addChannel(String name, LocalDate date, int subscribers) {
        var channel = new Channel(name, date, subscribers);
        return channelRepository.save(channel);
    }

    @CacheEvict(value = "search-channel", allEntries = true)
    public boolean changeChannelNameAndSubscribers(long channelId, String newName, int newSubscribers) {
        var channel = channelRepository.findById(channelId).orElse(null);
        if (channel == null) {
            return false;
        }
        channel.setName(newName);
        channel.setSubscribers(newSubscribers);
        channelRepository.save(channel);
        return true;
    }

    @Cacheable("search-channels")
    public List<Channel> searchChannelsByName(
            String searchTerm) {
        return channelRepository
                .getChannelsByNameLikeIgnoreCase(
                        "%" + searchTerm + "%");
    }


    @Async
    @CacheEvict(value = "search-channel", allEntries = true)
    public void processChannelsCsv(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        try {
            // Thread.sleep(1000);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                var columns = line.split(",");
                Channel channel = new Channel();
                channel.setName(columns[0].trim());
                channel.setDate(LocalDate.parse(columns[1].trim()));
                channel.setSubscribers(Integer.parseInt(columns[2].trim()));
                channelRepository.save(channel);
            }
        } catch (Exception e) {
            LOGGER.error("Error processing line: " + e);
        }
    }
}
