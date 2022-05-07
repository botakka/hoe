package hu.oe.hoe.bards;

import hu.oe.hoe.model.EpicSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SongRepository extends JpaRepository<EpicSong, Long> {

    Collection<EpicSong> findByBardId(Long bardId);
}
