package com.example.TCSwim.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record ClubDto(@NotBlank String nameClub,
                      @NotBlank String ufClub,
                      @NotBlank String credentialClub,

                      @NotBlank String qtAthletesActives) {

}
