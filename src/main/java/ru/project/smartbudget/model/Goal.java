package ru.project.smartbudget.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Goal {
    private String id;
    private String name;
    private Long target;
    private Long currentAmount;
}
