package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Video;
import kdg.be.prog5_app.domain.VideoGenre;
import kdg.be.prog5_app.repositories.ChannelVideoRepository;
import kdg.be.prog5_app.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final ChannelVideoRepository channelVideoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository, ChannelVideoRepository channelVideoRepository) {
        this.videoRepository = videoRepository;
        this.channelVideoRepository = channelVideoRepository;
    }

    public List<Video> getVideos() {
        return videoRepository.showAllVideos();
    }

    @Transactional
    @CacheEvict(value = "search-videos", allEntries = true)
    public Video addVideo(String title, int views, String link, VideoGenre videoGenre) {
        var video = new Video(title, views, link, videoGenre);
        return videoRepository.save(video);
    }

    @Transactional
    @CacheEvict(value = "search-videos", allEntries = true)
    public boolean removeVideo(long videoId) {
        var video = videoRepository.findByIdWithChannels(videoId);
        if (video.isEmpty()) {
            return false;
        }
        channelVideoRepository.deleteAll(video.get().getChannels());
        videoRepository.deleteById(videoId);
        return true;
    }

    @Cacheable("search-videos")
    public List<Video> searchVideosByName(
            String searchTerm) {
        return videoRepository
                .getVideosByTitleLikeIgnoreCase(
                        "%" + searchTerm + "%");
    }

    public Optional<Video> getVideo(long id) {
        return videoRepository.findById(id);
    }
}
