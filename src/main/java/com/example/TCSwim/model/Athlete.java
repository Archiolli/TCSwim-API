package com.example.TCSwim.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ATHLETE")
public class Athlete implements Serializable {

    //Athlete Character
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATHLETE")
    private Long idAthlete;
    @Column(name = "ATHLETE_NAME")
    private String athleteName;
    @Column(name = "ATHLETE_HEIGHT")
    private String athleteHeight;
    @Column(name = "ID_CLUB")
    private Long idClub;

    
    //DB Control
    @Column(name = "RESPONSIBLE_COACH")
    private String responsibleCoach;
    @Column(name = "DATE_INCLUSION_ATHLETE")
    private Date inclusionDate;
    @Column(name = "RESPONSIBLE_INCLUSION_ATHLETE")
    private String responsibleInclusion;
    @Column(name = "DATE_EXCLUSION_ATHLETE")
    private Date exclusionDate;
    @Column(name = "RESPONSIBLE_EXCLUSION_ATHLETE")
    private String responsibleExclusion;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "CATEGORIA_BP")
//    private Categoria categoria;

    @PrePersist
    public void beforeInsert(){
        this.inclusionDate = new Date();
    }
}
