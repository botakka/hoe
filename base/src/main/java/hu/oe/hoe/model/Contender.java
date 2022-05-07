package hu.oe.hoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "contender")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contender {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column private Long bardId;

  @ManyToOne
  @JoinColumns({@JoinColumn(name = "bard_contest_id", referencedColumnName = "id")})
  @JsonIgnore
  private BardContest bardContest;

  @Builder
  Contender(Long bardId, BardContest bardContest) {
    this.bardId = bardId;
    this.bardContest = bardContest;
  }
}
