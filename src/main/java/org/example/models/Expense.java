package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expense {
    private String id;
    private String description;
    private Float amount;
    private String category;
    private Long createdAt;
    private Long updatedAt;
}
