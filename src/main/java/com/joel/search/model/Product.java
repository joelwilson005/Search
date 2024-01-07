package com.joel.search.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Product {

    private Long objectID;
    private String name;
    private String description;
    private List<String> tags = new ArrayList<>();
}
