package kdg.be.prog5_app.repositories;

import kdg.be.prog5_app.domain.Channel;
import kdg.be.prog5_app.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("SELECT channels FROM Channel channels")
    List<Channel> showAllChannels();

    @Query("""
            select channel from Channel channel
            left join fetch channel.videos videos
            left join fetch videos.video
            where channel.id = :channelId
            """)
    Optional<Channel> findByIdWithVideos(long channelId);

//    Channel save(Channel channel);

    @Query("SELECT cv.video FROM ChannelVideo cv WHERE cv.channel.id = :channelId")
    List<Video> findVideosByChannelId(@Param("channelId") Long channelId);

    List<Channel> getChannelsByNameLike( String searchTerm);
}
