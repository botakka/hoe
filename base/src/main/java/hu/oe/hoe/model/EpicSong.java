package hu.oe.hoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.BooleanFlag;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.Transient;

@Entity
@Table(name = "epicsong")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EpicSong {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "songName")
  @NotNull
  @NotEmpty
  private String songName;

  @Column(name = "protector")
  //    @ApiModelProperty("hős neve")
  @NotNull
  @NotEmpty
  private String protectorName;

  @Column(name = "attacker")
  //    @ApiModelProperty("hős neve")
  @NotNull
  @NotEmpty
  private String attackerName;

  @NotNull @NotEmpty private String winner; // attacker vagy protector

  private String text;

  @ManyToOne
  @JoinColumns({@JoinColumn(name = "bard_id", referencedColumnName = "id")})
  @JsonIgnore
  private Bard bard;

  @Builder
  public EpicSong(
      Long id,
      Bard bard,
      String songName,
      String protectorName,
      String attackerName,
      String winner) {
    this.id = id;
    this.bard = bard;
    this.songName = songName;
    this.protectorName = protectorName;
    this.attackerName = attackerName;
    this.winner = winner;
  }
}
