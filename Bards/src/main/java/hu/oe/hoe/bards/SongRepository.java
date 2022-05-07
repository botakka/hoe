package hu.oe.hoe.bards;

import hu.oe.hoe.model.EpicSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<EpicSong, Long> {

  List<EpicSong> findByBardIdIn(List<Long> bardIds);
}
