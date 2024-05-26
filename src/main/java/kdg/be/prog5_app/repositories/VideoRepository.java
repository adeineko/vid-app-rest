package kdg.be.prog5_app.repositories;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("SELECT videos FROM Video videos")
    List<Video> showAllVideos();

    Optional<Video> findById(long videoId);

    @Query("""
            select video from Video video
            left join fetch video.channels channels
            left join fetch channels.channel
            where video.id = :videoId
            """)
    Optional<Video> findByIdWithChannels(long videoId);
    List<Video> getVideosByTitleLike(String searchTerm);

}
