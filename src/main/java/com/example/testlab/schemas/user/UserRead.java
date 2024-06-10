package com.example.testlab.schemas.user;

import com.example.testlab.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRead extends UserBase {
    private Long id;
    private Role role;
}
