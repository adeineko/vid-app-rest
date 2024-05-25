package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.repositories.ChannelRepository;
import kdg.be.prog5_app.repositories.ChannelVideoRepository;
import kdg.be.prog5_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    public ChannelService(ChannelRepository channelRepository, ChannelVideoRepository channelVideoRepository, UserRepository userRepository) {
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

    public Channel addChannel(String name, LocalDate date, int subscribers) {
        var channel = new Channel(name, date, subscribers);
        return channelRepository.save(channel);
    }

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

//    @Cacheable()
    public List<Channel> searchChannelsByName(
            String searchTerm) {
        return channelRepository
                .getChannelsByNameLike(
                        "%" + searchTerm + "%");
    }


    @Async
    @CacheEvict(value = "search-channel", allEntries = true)
    public void processChannelsCsv(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        String line = scanner.nextLine();
        //var columns = line.split(","); repo create channel ...
        LOGGER.info(line);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }
}
