package hu.oe.hoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "bard")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Bard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @Column(name="userid")
  private String userid;

  @Transient private Long empireid;

  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private Empire empire;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bard", cascade = CascadeType.REMOVE)
  private Collection<EpicSong> epicSongs = new ArrayList<>();
}
