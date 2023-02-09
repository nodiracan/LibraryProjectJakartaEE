package com.library.domain;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private Long id;
    private String author;
    private String description;
    private String title;
    private double cost;

    private int categoryId;
    private int publishedYear;
    private Date createdAt;
    private Date updatedAt;
}
