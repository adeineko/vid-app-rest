package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Video;
import kdg.be.prog5_app.domain.VideoGenre;
import kdg.be.prog5_app.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> getVideos() {
        return videoRepository.showAllVideos();
    }

    @Transactional
    public Video addVideo(String title, int views, String link, VideoGenre videoGenre) {
        var video = new Video(title, views, link, videoGenre);
        return videoRepository.save(video);
    }

    public void deleteVideo(long id) {
        videoRepository.deleteById(id);
    }

    public Video deleteVideoFromChannelVideo(long videoId) {
        return videoRepository.deleteByIdFromChannelVideo(videoId);
    }

    public Optional<Video> getVideo(long id) {
        return videoRepository.findById(id);
    }
}
