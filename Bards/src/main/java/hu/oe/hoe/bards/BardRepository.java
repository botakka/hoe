package hu.oe.hoe.bards;

import hu.oe.hoe.model.Bard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BardRepository extends JpaRepository<Bard, Long> {

  // @Query("select s from Bard s where s.id = :id")
  Bard findById(@Param("id") long pId);

  // @Query("select s from Bard s where s.name = :name")
  Bard findByName(@Param("name") String pName);

}
