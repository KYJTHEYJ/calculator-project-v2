package com.nbcamp.calculatorV2.step3;

public enum OperatorType {
    PLUS('+')
    , MINUS('-')
    , MULTIPLY('*')
    , DIVIDE('/');

    public final char operator;

    OperatorType(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }
}
