package kdg.be.prog5_app.services;

import kdg.be.prog5_app.controllers.api.dto.VideoDto;
import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.ChannelVideo;
import kdg.be.prog5_app.domain.Video;
import kdg.be.prog5_app.repositories.ChannelRepository;
import kdg.be.prog5_app.repositories.ChannelVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelVideoRepository channelVideoRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository, ChannelVideoRepository channelVideoRepository) {
        this.channelRepository = channelRepository;
        this.channelVideoRepository = channelVideoRepository;
    }

//    public List<Channel> showChannels() {
//        return channelRepository.showAllChannels();
//    }

    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel getChannel(Long id) {
        return channelRepository.findById(id).orElse(null);
    }

    public Channel deleteChannel(long id) {
        return channelRepository.deleteById(id);
    }

    @Transactional
    public boolean removeChannel(long channelId) {
        var channel = channelRepository.findByIdWithAssignments(channelId);
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

    public List<Video> findVideosByChannelId(Long channelId) {
        return channelRepository.findVideosByChannelId(channelId);
    }

    public List<Video> getVideoOfChannel(long channelId) {
        return channelRepository.findVideosByChannelId(channelId);
    }

    public Channel addChannel(String name, LocalDate date, int subscribers) {
        var channel = new Channel(name, date, subscribers);
        return channelRepository.save(channel);
    }
}
