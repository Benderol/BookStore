package com.project.BookStore.models;

import com.project.BookStore.models.enums.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInput {
    private String title;
    private Category category;
}
