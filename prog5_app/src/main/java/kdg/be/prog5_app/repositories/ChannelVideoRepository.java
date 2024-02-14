package kdg.be.prog5_app.repositories;

import kdg.be.prog5_app.domain.ChannelVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelVideoRepository extends JpaRepository<ChannelVideo, Long> {
//    ChannelVideo
}
