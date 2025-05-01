package org.example.calculator.lv3;

import java.util.Optional;

public enum OperationType {
    PLUS('+'),
    MINUS('-'),
    TIMES('*'),
    DIVIDE('/');

    private final char symbol;

    OperationType(char symbol) {
        this.symbol = symbol;
    }

    public static Optional<OperationType> from(char symbol) {
        for (OperationType op : values()) {
            if (op.symbol == symbol) {
                return Optional.of(op);
            }
        }
        return Optional.empty();
    }
}
