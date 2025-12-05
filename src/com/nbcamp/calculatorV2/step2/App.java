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

            if (calc.exitCalculator(sc)) {
                break;
            }
        }
    }
}
