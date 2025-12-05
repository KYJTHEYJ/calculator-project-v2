package com.nbcamp.calculatorV2.step3;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();

        while (true) {
            double[] resultNumberArr = calc.inputNumbersWithCheckNegative(sc);
            char resultOperator = calc.inputOperator(sc);
            calc.processingCal(resultNumberArr, resultOperator, sc);

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
