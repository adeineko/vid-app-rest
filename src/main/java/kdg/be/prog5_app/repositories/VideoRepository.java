package kdg.be.prog5_app.repositories;

import jakarta.transaction.Transactional;
import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("SELECT videos FROM Video videos")
    List<Video> showAllVideos();
    @Query("""
            select video from Video video
            left join fetch video.revenues channels
            left join fetch channels.channel
            where video.id = :videoId
            """)
    Optional<Channel> findByIdWithChannels(long videoId);
    Optional<Video> findById(long videoId);
    Video save(Video video);

    //    @Query("DELETE FROM Video videos WHERE videos.id = :videoId")
//    Video deleteById(long videoId);

    @Query("DELETE FROM ChannelVideo cv WHERE cv.video.id = :videoId")
    Video deleteByIdFromChannelVideo(@Param("videoId") long videoId);
}
