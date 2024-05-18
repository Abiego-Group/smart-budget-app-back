package ru.project.smartbudget.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SmartBudgetException extends RuntimeException {
    public SmartBudgetException(String message) {
        super(message);
    }
}
