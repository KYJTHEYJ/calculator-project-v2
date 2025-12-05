package com.nbcamp.calculatorV2.step3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * TODO List
 *  1. Enum 타입을 활용하여 연산자 타입에 대한 정보를 관리하고 이를 사칙연산 계산기 Calculator 클래스에 활용
 *  2. 제네릭 활용
 *  3. 저장된 연산 결과를 Scanner로 입력받은 값보다 큰 결과를 출력 (스트림, 람다 사용할 것)
 */

// Number 상한선 지정 -> 실수 처리를 위해서
class Calculator<T extends Number> {
    // 연산의 결과값으로는 수식과 결과값 전체를 저장함
    private ArrayList<String> resultArrList = new ArrayList<>();

    public ArrayList<String> getResultArrList() {
        return resultArrList;
    }

    // 사칙연산 기호(+,-,*,/) 입력받기
    // 사칙연산 기호 입력 외에 입력시 다시 입력 받도록 조치
    protected char inputOperator(Scanner sc) {
        while (true) {
            System.out.print("계산할 사칙연산 기호를 입력해주세요 : ");
            char inputOperator = sc.nextLine().charAt(0);
            boolean operatorExists = false;

            // Enum 클래스인 OperatorType 활용하여 체크 변경
            OperatorType[] operatorValues = OperatorType.values();
            for (OperatorType operator : operatorValues) {
                if (operator.getOperator() == inputOperator) {
                    operatorExists = true;
                    break;
                }
            }

            if (operatorExists) {
                return inputOperator;
            } else {
                System.out.println("+,-,*,/ 만 입력해주세요!");
            }
        }
    }

    // 동적 포맷팅을 위해 메서드 생성
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

    // 계산부분 분리
    private Number calculatingNumbers(T[] numberArr, char operator) {
        // 자릿수를 두고 싶지 않은데 소수점 계산이 너무 흐트러져
        // 부동 소수점 정확성 보정 계산으로 계산 방식 변경
        BigDecimal number1 = new BigDecimal(numberArr[0].toString());
        BigDecimal number2 = new BigDecimal(numberArr[1].toString());
        Number result = null;

        if (operator == '+') {
            result = number1.add(number2);
        } else if (operator == '-') {
            result = number1.subtract(number2);
        } else if (operator == '*') {
            result = number1.multiply(number2);
        } else if (operator == '/') {
            result = number1.divide(number2, RoundingMode.HALF_UP);
        }

        return result;
    }

    // 입력 받은 수와 사칙연산 기호를 사용하여 연산 진행 후 결과를 받아 저장 하고 출력하는 부분
    protected void processingCal(T[] numberArr, char operator) {
        StringBuilder formattedExpression = new StringBuilder();

        for (int index = 0, numberArrLength = numberArr.length; index < numberArrLength; index++) {
            T number = numberArr[index];

            if (index == 0) {
                formattedExpression.append(formattingNumber(number));
                formattedExpression.append(" ").append(operator).append(" ");
            } else {
                formattedExpression.append(formattingNumber(number));
                formattedExpression.append(" = ").append(formattingNumber(calculatingNumbers(numberArr, operator)));
            }
        }

        resultArrList.add(String.format("%d. %s", resultArrList.size() + 1, formattedExpression + "\n"));
        System.out.println(formattedExpression);
    }

    // 결과 수식 저장 초기화
    protected void deleteCalcResults() {
        resultArrList.clear();
    }

    // 결과 수식의 결과값 중 입력값보다 큰 결과 수식들을 보여줌
    // 람다, 스트림 사용 요건
    protected List<String> findMoreBiggerCase(T inputValue) {
        // 현재 수식이 저장되고 있음
        return resultArrList.stream()
                .filter(value -> Double.parseDouble(value.split("=")[1].trim()) > inputValue.doubleValue())
                .collect(Collectors.toList());

    }

    // 결과 수식의 결과값 중 입력값보다 작은 수식들을 보여주기
    // 중간 filter 람다식 구현
    // Predicate 인터페이스 참조
    protected List<String> findMoreSmallerCase(T inputValue) {

        Predicate<String> predicate = (value)
                -> Double.parseDouble(value.split("=")[1].trim()) < inputValue.doubleValue();

        return resultArrList.stream().filter(predicate).collect(Collectors.toList());
    }

    // 부등호를 직접 입력해서 구현
    // 중간 filter 람다식 구현
    // Predicate 인터페이스 참조
    protected List<String> findYourCase(String boolState, T inputValue) {
        Predicate<String> predicate1 = (value)
                -> Double.parseDouble(value.split("=")[1].trim()) > inputValue.doubleValue();
        Predicate<String> predicate2 = (value)
                -> Double.parseDouble(value.split("=")[1].trim()) < inputValue.doubleValue();
        Predicate<String> predicate3 = (value)
                -> Double.parseDouble(value.split("=")[1].trim()) == inputValue.doubleValue();
        Predicate<String> predicate4 = (value)
                -> Double.parseDouble(value.split("=")[1].trim()) >= inputValue.doubleValue();
        Predicate<String> predicate5 = (value)
                -> Double.parseDouble(value.split("=")[1].trim()) <= inputValue.doubleValue();

        switch (boolState) {
            case ">" -> {
                return resultArrList.stream().filter(predicate1).collect(Collectors.toList());
            }
            case "<" -> {
                return resultArrList.stream().filter(predicate2).collect(Collectors.toList());
            }
            case "==" -> {
                return resultArrList.stream().filter(predicate3).collect(Collectors.toList());
            }
            case ">=" -> {
                return resultArrList.stream().filter(predicate4).collect(Collectors.toList());
            }
            case "<=" -> {
                return resultArrList.stream().filter(predicate5).collect(Collectors.toList());
            }
            default -> {
                return null;
            }
        }
    }

    // 결과 이후 "exit" 입력시 프로그램 종료
    // , "history 입력시 계산 결과 목록 출력
    // , "delete" 입력시 계산 결과 목록 삭제 되도록 키워드 반환 (App main에서 활용)
    protected String exitOrCalResult(Scanner sc) {
        String command;

        System.out.print("계속 계산하시겠어요?\n" +
                         "( history 입력시 그 동안 계산 결과 출력\n" +
                         "  / delete 입력시 그 동안 계산 결과 삭제\n" +
                         "  / find 입력시 값을 입력받아 그 값보다 큰 결과들을 출력\n" +
                         "  / find2 입력시 값을 입력받아 그 값보다 작은 결과들을 출력\n" +
                         "  / find3 입력시 부등호와 값을 입력 받아 결과들을 출력\n" +
                         "  / exit 입력시 종료\n" +
                         "  / 상기한 키워드 외 아무 키워드 입력시 계속 계산\n" +
                         ") : ");
        command = sc.nextLine();

        return command;
    }
}
