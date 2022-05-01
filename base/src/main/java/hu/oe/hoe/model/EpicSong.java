package hu.oe.hoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

  @Column(name = "protectorId")
  //    @ApiModelProperty("hős neve")
  @NotNull
  @NotEmpty
  private Long protectorId;

  @Column(name = "attackerId")
  //    @ApiModelProperty("hős neve")
  @NotNull
  @NotEmpty
  private Long attackerId;

  @ManyToOne
  @JoinColumns({
          @JoinColumn(name = "bardId", referencedColumnName = "id")
  })
  @JsonIgnore
  private Bard bard;

  @Builder
  public EpicSong(Long id, Bard bard, String songName, Long protectorId, Long attackerId) {
    this.id = id;
    this.bard = bard;
    this.songName = songName;
    this.protectorId = protectorId;
    this.attackerId = attackerId;
  }
}
