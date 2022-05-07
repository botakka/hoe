package hu.oe.hoe.bardcontest;

import hu.oe.hoe.model.Contender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContenderRepository extends JpaRepository<Contender, Long> {

    List<Contender> findByBardContestId(Long bardContestId);
}
