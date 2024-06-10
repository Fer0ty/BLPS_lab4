package com.example.testlab.schemas.petition;

import com.example.testlab.schemas.user.UserRead;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class PetitionRead extends PetitionBase {
    private Long id;

    private UserRead owner;
    private String country;
    private Timestamp creationDate;
}
