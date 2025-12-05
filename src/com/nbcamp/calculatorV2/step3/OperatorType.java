package com.nbcamp.calculatorV2.step3;

// 올바른 사칙연산 기호 입력을 위한 Enum
enum OperatorType {
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
