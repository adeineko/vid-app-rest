package kdg.be.prog5_app.services;

import kdg.be.prog5_app.domain.Video;
import kdg.be.prog5_app.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    private VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> showVideos() {
        return videoRepository.showAllVideos();
    }

    public Video addVideo(Video video) {
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
