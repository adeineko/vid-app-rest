package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.Video;
import kdg.be.prog5_app.repositories.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    //    public Channel getChannel(String name) {
//        return channelRepository.showAllChannels().stream().filter(c -> c.getName()
//                        .equals(name))
//                .findFirst()
//                .orElse(null);
//    }
    public List<Channel> showChannels() {
        return channelRepository.showAllChannels();
    }

    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Optional<Channel> getChannel(Long id) {
        return channelRepository.findById(id);
    }

    public Channel deleteChannel(long id) {
        return channelRepository.deleteById(id);
    }

    public List<Channel> getChannels() {
        return channelRepository.showAllChannels();
    }

    //    public Channel updateChannel(Channel channel) {
//        return channelRepository.updateChannel(channel);
//    }
    public List<Video> findVideosByChannelId(Long channelId) {
        return channelRepository.findVideosByChannelId(channelId);
    }
}
