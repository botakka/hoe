package hu.oe.hoe.bards;

import hu.oe.hoe.model.Bard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BardRepository extends JpaRepository<Bard, Long> {

  Bard findById(@Param("id") long pId);

  Bard findByName(@Param("name") String pName);

  Collection<Bard> findByEmpireIdOrderByNameAsc(Long pId);
}
