package hu.oe.hoe.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bardcontest")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BardContest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column private String contestName;

  // ha a dalnok verseny megtörtént, akkor true
  @Column(columnDefinition = "boolean default false")
  private Boolean closed;
  // @Transient private Long empireid;

  //    @OneToOne(cascade = CascadeType.ALL)
  //    @JsonIgnore
  //    private Empire empire;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bardContest", cascade = CascadeType.REMOVE)
  private List<Contender> contenders = new ArrayList<>();
}
