package com.project.BookStore.models;

import com.project.BookStore.models.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorInput {
    private String username;
    private String password;
    private Role role;
}
