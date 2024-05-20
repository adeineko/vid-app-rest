package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.ChannelVideo;
import kdg.be.prog5_app.domain.Video;
import kdg.be.prog5_app.repositories.ChannelRepository;
import kdg.be.prog5_app.repositories.ChannelVideoRepository;
import kdg.be.prog5_app.repositories.UserRepository;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    private final ChannelVideoRepository channelVideoRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository, ChannelVideoRepository channelVideoRepository, UserRepository userRepository) {
        this.channelRepository = channelRepository;
        this.channelVideoRepository = channelVideoRepository;
        this.userRepository = userRepository;
    }

//    public Channel addChannel(Channel channel) {
//        return channelRepository.save(channel);
//    }

    public Channel getChannel(Long id) {
        return channelRepository.findById(id).orElse(null);
    }


    @Transactional
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

    public List<Video> findVideosByChannelId(Long channelId) {
        return channelRepository.findVideosByChannelId(channelId);
    }

    public List<Video> getVideoOfChannel(long channelId) {
        return channelRepository.findVideosByChannelId(channelId);
    }

    public Channel addChannel(String name, LocalDate date, int subscribers) {
       /* var user = userRepository.findById(userId).orElse(null);*/
        var channel = new Channel(name, date, subscribers);
        return channelRepository.save(channel);
    }

    public boolean changeChannelDescription(long channelId, String newName, int newSubscribers) {
        var channel = channelRepository.findById(channelId).orElse(null);
        if (channel == null) {
            return false;
        }
        channel.setName(newName);
        channel.setSubscribers(newSubscribers);
        channelRepository.save(channel);
        return true;
    }
}
