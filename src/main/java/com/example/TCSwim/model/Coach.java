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
@Entity(name = "COACH")
public class Coach implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COACH")
    private Long idCoach;
    @Column(name = "COACH_NAME")
    private String coachName;
    @Column(name = "COACH_ROLE")
    private Long coachRole;
    @Column(name = "IS_HEAD_COACH")
    private Boolean isHeadCoach;
    @Column(name = "ID_CLUB")
    private Long idClub;




    //DB Control
    @Column(name = "DATE_INCLUSION_COACH")
    private Date inclusionDate;
    @Column(name = "RESPONSIBLE_INCLUSION_COACH")
    private String responsibleInclusion;
    @Column(name = "DATE_EXCLUSION_COACH")
    private Date exclusionDate;
    @Column(name = "RESPONSIBLE_EXCLUSION_COACH")
    private String responsibleExclusion;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "CATEGORIA_BP")
//    private Categoria categoria;

    @PrePersist
    public void beforeInsert(){
        this.inclusionDate = new Date();
    }
}
