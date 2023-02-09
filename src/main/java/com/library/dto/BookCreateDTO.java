package com.library.dto;

import java.util.Date;

public record BookCreateDTO(String author,String description,  String title, double cost, int categoryId, int publishedYear) {

}
