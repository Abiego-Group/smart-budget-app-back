package ru.project.smartbudget.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GoalDto {
    private String name;
    private Long target;
    private Long currentAmount;
}
