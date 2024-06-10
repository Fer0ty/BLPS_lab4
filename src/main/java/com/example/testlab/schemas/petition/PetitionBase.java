package com.example.testlab.schemas.petition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PetitionBase {
    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 512)
    private String description;

}
