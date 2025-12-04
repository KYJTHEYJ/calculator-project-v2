package com.nbcamp.calculatorV2.step1;

import java.util.Scanner;

/*
 * TODO List
 *  Step1 require
 *  1. 타 클래스 없이 기본적 연산 수행
 *  2. 양의 정수를 입력 받기
 *  3. 사칙연산 기호 입력받기
 *  4. 위에서 입력받은 양의 정수 2개, 사칙연산 기호를 사용하여 결과값 출력하기
 *   4-1. 입력 받은 연산자의 구분은 제어문으로 처리할 것
 *   4-2. 연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제 ex) 0으로 나누기 등
 *  5. 반복문을 사용하되, 반복의 종료를 알려주는 "exit" 입력 전 까진 무한 계산 가능 하도록
 */
public class Calculator {
    // 계산할 숫자를 받아오기
    // 계산 받을 숫자의 갯수 지정, 0 포함한 양의 정수인지 입력 검사 수행, 잘못되면 단계별 다시 입력 받도록 조치
    private static int[] inputNumbersWithCheckNegative(Scanner sc) {
        // 계산 받을 숫자의 갯수 지정, 배열의 크기로 제어
        int[] inputNumberArr = new int[2];

        // 숫자 입력 받기 (0 이상일 경우만 받도록)
        for (int index = 0; index < inputNumberArr.length; index++) {
            while (true) {
                System.out.printf("%d 번째 숫자를 입력하세요 (0 포함 양의 정수) : ", index+1);
                int inputNumber = sc.nextInt();
                sc.nextLine();

                if (inputNumber < 0) {
                    System.out.println("(0 포함 양의 정수)를 입력해주세요!");
                    continue;
                } else {
                    inputNumberArr[index] = inputNumber;
                }

                break;
            }
        }

        return inputNumberArr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] resultNumberArr = inputNumbersWithCheckNegative(sc);
        System.out.println("resultNumberArr1: " + resultNumberArr[0]);
        System.out.println("resultNumberArr2: " + resultNumberArr[1]);
    }
}
