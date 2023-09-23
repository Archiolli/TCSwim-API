package com.example.TCSwim.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLUB")
public class Club implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLUB")
    private Long idClub;
    @Column(name = "CLUB_NAME")
    private String nameClub;
    @Column(name = "UF_CLUB")
    private String ufClub;
    @Column(name = "CNPJ_CLUB")
    private String cnpjClub;



}
