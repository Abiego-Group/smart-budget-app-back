package ru.project.smartbudget.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Goal {
    private String id;
    private String name;
    private Long target;
    private Long currentAmount;
}
