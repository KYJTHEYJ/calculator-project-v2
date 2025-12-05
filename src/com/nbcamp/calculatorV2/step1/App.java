package com.nbcamp.calculatorV2.step1;

import java.util.Scanner;

public class App {
    // 계산할 숫자를 받아오기
    // 계산 받을 숫자의 갯수 지정, 0 포함한 양의 정수인지 입력 검사 수행, 잘못되면 단계별 다시 입력 받도록 조치
    private static double[] inputNumbersWithCheckNegative(Scanner sc) {
        // 계산 받을 숫자의 갯수 지정, 배열의 크기로 제어
        double[] inputNumberArr = new double[2];

        // 숫자 입력 받기 (0 이상일 경우만 받도록)
        for (int index = 0; index < inputNumberArr.length; index++) {
            while (true) {
                int inputNumber;

                System.out.printf("%d 번째 숫자를 입력하세요 (0 포함 양의 정수) : ", index + 1);

                try {
                    inputNumber = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("잘못된 입력을 하셨어요!");
                    sc.nextLine();
                    continue;
                }

                if (inputNumber >= 0) {
                    inputNumberArr[index] = inputNumber;
                } else {
                    System.out.println("0 포함 양의 정수를 입력해주세요!");
                    sc.nextLine();
                    continue;
                }

                sc.nextLine();
                break;
            }
        }

        return inputNumberArr;
    }

    // 사칙연산 기호(+,-,*,/) 입력받기
    // 사칙연산 기호 입력 외에 입력시 다시 입력 받도록 조치
    private static char inputOperator(Scanner sc) {
        while (true) {
            System.out.print("계산할 사칙연산 기호를 입력해주세요 : ");
            char inputOperator = sc.nextLine().charAt(0);

            if (inputOperator == '+'
                    || inputOperator == '-'
                    || inputOperator == '*'
                    || inputOperator == '/') {
                return inputOperator;
            } else {
                System.out.println("+,-,*,/ 만 입력해주세요!");
            }
        }
    }

    // 입력 받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산 진행하기
    // 0으로 나눠 질 경우 불가능하다는 결과 출력 후
    // 두번째 인수를 다시 받아 정상적으로 결과를 출력하도록 수정
    private static void processingCal(double[] numberArr, char operator, Scanner sc) {
        switch (operator) {
            case '+' -> System.out.printf("%.0f + %.0f = %.0f\n", numberArr[0], numberArr[1], numberArr[0] + numberArr[1]);
            case '-' -> System.out.printf("%.0f - %.0f = %.0f\n", numberArr[0], numberArr[1], numberArr[0] - numberArr[1]);
            case '*' -> System.out.printf("%.0f * %.0f = %.0f\n", numberArr[0], numberArr[1], numberArr[0] * numberArr[1]);
            case '/' -> {
                if (numberArr[1] == 0) {
                    System.out.println("0 으로 나누는 건 불가능해요!");

                    while (true) {
                        double inputNumber;

                        System.out.printf("%d 번째로 입력한 숫자를 바꿔주세요 (0 초과 양의 정수) : ", numberArr.length);

                        try {
                            inputNumber = Integer.parseInt(sc.nextLine());
                        } catch (Exception e) {
                            System.out.println("잘못된 입력을 하셨어요!");
                            continue;
                        }

                        if (inputNumber > 0) {
                            System.out.printf("%.0f / %.0f = %.2f\n", numberArr[0], inputNumber, numberArr[0] / inputNumber);
                            break;
                        } else {
                            System.out.println("0 초과 양의 정수를 입력해주세요!");
                        }
                    }
                } else {
                    System.out.printf("%.0f / %.0f = %.2f\n", numberArr[0], numberArr[1], numberArr[0] / numberArr[1]);
                }
            }
        }
    }

    // 결과 이후 "exit" 입력시 프로그램 종료, 아무 키워드 입력시엔 계속
    private static boolean exitCalculator(Scanner sc) {
        String command;
        System.out.print("계속 계산하시겠어요? ( 아무 키워드 입력시 계속 / exit 입력시 종료 ) : ");
        command = sc.nextLine();
        return command.equals("exit");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            double[] resultNumberArr = inputNumbersWithCheckNegative(sc);
            char resultOperator = inputOperator(sc);
            processingCal(resultNumberArr, resultOperator, sc);

            if (exitCalculator(sc)) {
                break;
            }
        }
    }
}
