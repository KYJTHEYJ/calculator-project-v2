package com.nbcamp.calculatorV2.step3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

// 올바른 사칙연산 기호 입력을 위한 Enum
// Enum 각각에 연산 결과 포맷팅 메서드 부분 추가 - 본문과 연결은 하지 않음, 이런 식으로 하는게 더 보기 좋다 라고 연습 용도..
enum OperatorType {
    PLUS('+') {
        @Override
        public Number calculate(Number[] numberArr) {
            BigDecimal number1 = new BigDecimal(numberArr[0].toString());
            BigDecimal number2 = new BigDecimal(numberArr[1].toString());
            return number1.add(number2);
        }
    }
    , MINUS('-') {
        @Override
        public Number calculate(Number[] numberArr) {
            BigDecimal number1 = new BigDecimal(numberArr[0].toString());
            BigDecimal number2 = new BigDecimal(numberArr[1].toString());
            return number1.subtract(number2);
        }
    }
    , MULTIPLY('*') {
        @Override
        public Number calculate(Number[] numberArr) {
            BigDecimal number1 = new BigDecimal(numberArr[0].toString());
            BigDecimal number2 = new BigDecimal(numberArr[1].toString());
            return number1.multiply(number2);
        }
    }
    , DIVIDE('/') {
        @Override
        public Number calculate(Number[] numberArr) {
            BigDecimal number1 = new BigDecimal(numberArr[0].toString());
            BigDecimal number2 = new BigDecimal(numberArr[1].toString());

            if(numberArr[1].doubleValue() == 0) {
                // 그냥 throw new Exception은 Check Exception 이라 throws 를 추상에 강제하던 해야함
                throw new IllegalArgumentException("0 으로 나누는 건 불가능 해요!");
            }

            return number1.divide(number2, RoundingMode.HALF_UP);
        }
    };

    public final char operator;

    OperatorType(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }

    // Enum에 추가적으로 계산 메서드 추가하여 분리
    public abstract Number calculate(Number[] numberArr);

    private String formattingNumber(Number number) {
        // 포맷팅하여 출력하도록 조치
        // . 이 있다면 우선 소수점으로 생각 -> double 로 형 변환
        // . 이 없다면 -> 정수로 생각
        if (number.toString().contains(".")) {
            // 10.0 같은 입력값은 사실상 정수로 보는게 맞음
            if (number.doubleValue() == (long) number.doubleValue()) {
                return String.valueOf(number.longValue());
            } else {
                return String.valueOf(number.doubleValue());
            }
        } else {
            return String.valueOf(number.longValue());
        }
    }

    private void processingCal(Number[] numberArr, ArrayList<String> resultArrList) {
        StringBuilder formattedExpression = new StringBuilder();

        for (int index = 0, numberArrLength = numberArr.length; index < numberArrLength; index++) {
            Number number = numberArr[index];

            if (index == 0) {
                formattedExpression.append(formattingNumber(number));
                formattedExpression.append(" ").append(operator).append(" ");
            } else {
                formattedExpression.append(formattingNumber(number));
                formattedExpression.append(" = ").append(formattingNumber(calculate(numberArr)));
            }
        }

        resultArrList.add(String.format("%d. %s", resultArrList.size() + 1, formattedExpression + "\n"));
        System.out.println(formattedExpression);
    }
}
