package com.nbcamp.calculatorV2.step2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();

        while (true) {
            double[] resultNumberArr = calc.inputNumbersWithCheckNegative(sc);
            char resultOperator = calc.inputOperator(sc);
            calc.processingCal(resultNumberArr, resultOperator, sc);

            // Calculator 클래스 내부에서 계산 과정도 저장하는 것으로 구현을 해놓았지만..
            // App 클래스 내부에서 처리하여 수식 결과를 저장한다면
            // calc.getResultArrList().add("수식 결과들"); 또는 이 App 내부에 ArrayList 생성 후 setter 활용

            if (calc.exitCalculator(sc)) {
                break;
            }
        }
    }
}
