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

    List<Channel> findByName(String name);

    Channel findById(long id);
    @Query("""
           select channel from Channel channel
           left join fetch channel.videos
           where channel.id = :channelId
           """)
    Optional<Channel> findByIdWithAssignments(long channelId);

    Channel save(Channel channel);

    Channel deleteById(long id);

    @Query("SELECT cv.video FROM ChannelVideo cv WHERE cv.channel.id = :channelId")
    List<Video> findVideosByChannelId(@Param("channelId") Long channelId);
}
