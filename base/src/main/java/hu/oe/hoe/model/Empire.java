package hu.oe.hoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "empire")
@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empire {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;    
    
    @Column(name="name")
    private String name;
    
    @Column(name="description")
    @Lob
    private String description;
       
    @JsonIgnore
    @Column(name="userid")
    private String userid;
    
    @OneToOne(cascade = CascadeType.ALL)
    private SecurityGuard protect;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    private Bard bard;
}
