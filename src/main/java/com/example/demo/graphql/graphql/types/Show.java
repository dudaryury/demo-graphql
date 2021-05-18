package com.example.demo.graphql.graphql.types;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Show {
    private String title;
    private List<Review> reviews;
}
