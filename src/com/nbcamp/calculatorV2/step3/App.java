package com.nbcamp.calculatorV2.step3;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator<Number> calc = new Calculator<>();
        Number[] inputNumberArr = new Number[2];
        char operator;

        // 제네릭 클래스 외부인 main 클래스에서 받고
        // 계산은 ProcessCalculate 클래스에서 진행하여 요소들을 Calculator 클래스로 넘겨주기로 변경
        // 다양한 타입을 받는데 이에 따라 포맷을 지정하는 부분 추가
        // 2개의 숫자와 부호 입력받기
        while (true) {
            for (int index = 0; index < inputNumberArr.length; index++) {
                while (true) {
                    double inputNumber;

                    System.out.printf("%d 번째 숫자를 입력하세요 : ", index + 1);

                    try {
                        inputNumber = sc.nextDouble();
                    } catch (Exception e) {
                        System.out.println("잘못된 입력을 하셨어요!");
                        sc.nextLine();
                        continue;
                    }

                    inputNumberArr[index] = inputNumber;
                    sc.nextLine();
                    break;
                }
            }

            operator = calc.inputOperator(sc);

            // 나누기일 경우 0으로 나뉠 수 없기에 두번째 요소 재처리 요청
            if (operator == '/') {
                if(inputNumberArr[1].doubleValue() == 0) {
                    System.out.println("0 으로 나누는 건 불가능해요!");
                    Number newNumber;

                    while (true) {
                        System.out.print("나누는 수로 입력한 숫자를 바꿔주세요 (0 이 아닌 수) : ");

                        try {
                            newNumber = sc.nextDouble();
                        } catch (Exception e) {
                            System.out.println("잘못된 입력을 하셨어요!");
                            sc.nextLine();
                            continue;
                        }

                        inputNumberArr[1] = newNumber;
                        sc.nextLine();
                        break;
                    }
                }
            }

            // 값을 출력하고 저장
            calc.processingCal(inputNumberArr, operator);

            // 그 동안 계산 결과를 보거나 삭제, 종료 가능
            // App main 메서드에서 활용 요건으로 main 에서 구현하였음
            boolean resultMenuRun = true;
            while (resultMenuRun) {
                switch (calc.exitOrCalResult(sc)) {
                    case "history" -> {
                        if (!calc.getResultArrList().isEmpty()) {
                            System.out.println("==그 동안 계산한 수식들==");

                            int index = 0;
                            for (String result : calc.getResultArrList()) {
                                if (index == 0) System.out.print(result);
                                else if (calc.getResultArrList().size() == 1)
                                    System.out.println(result);
                                else System.out.println(result);
                                index++;
                            }
                        } else {
                            System.out.println("계산한 결과가 없어요!");
                        }
                    }
                    case "delete" -> calc.deleteCalcResults();
                    case "exit" -> {
                        return;
                    }
                    default -> resultMenuRun = false;
                }
            }
        }
    }
}
