package com.example.TCSwim.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AthleteDto(@NotBlank String athleteName,
                         @NotNull String athleteHeight,
                         @NotBlank Long idClub,
                         @NotNull String responsibleCoach,
                         Date inclusionDate,
                         String responsibleInclusion,
                         Date exclusionDate,
                         String responsibleExclusion
                         ) {

}
