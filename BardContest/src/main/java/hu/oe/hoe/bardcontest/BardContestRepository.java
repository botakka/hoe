package hu.oe.hoe.bardcontest;

import hu.oe.hoe.model.BardContest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BardContestRepository extends JpaRepository<BardContest, Long> {

  // @Query("select s from Bard s where s.id = :id")
  BardContest findById(@Param("id") long pId);

  // @Query("select s from Bard s where s.name = :name")
  BardContest findByContestName(String pContestName);

  @Query("select s from BardContest s where s.closed = :closed")
  List<BardContest> findByStatus(@Param("closed") Boolean pClosed);

}
