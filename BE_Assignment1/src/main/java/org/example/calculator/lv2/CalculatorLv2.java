package org.example.calculator.lv2;

import java.util.List;
import java.util.Scanner;

public class CalculatorLv2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();
        while (true) {
            System.out.println("---------------------------------------------");
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int a = sc.nextInt();
            System.out.print("두 번째 숫자를 입력하세요: ");
            int b = sc.nextInt();
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator = sc.next().charAt(0);

            Double result = calc.calculate(a, b, operator);
            if (result != null) System.out.println("연산 결과 : " + a + " " + operator + " " + b + " = " + result);

            System.out.print("Calculator 클래스의 저장 기록중 첫 번째 원소를 삭제하시겠습니까? (1 입력 시 삭제): ");
            int testGetterAndSetter = sc.nextInt();
            if (testGetterAndSetter == 1) {
                System.out.println("간접 접근을 통해 Calculator의 history 필드를 받아왔습니다.");
                List<Double> calcHistory = calc.getHistory();
                System.out.println("history 필드 : " + calcHistory);
                System.out.println("간접 접근을 통해 history 필드의 첫 번째 값을 삭제해보겠습니다.");
                calc.removeFirstDouble();
                System.out.println("첫 원소가 삭제된 후의 history 필드 : " + calc.getHistory());
            }

            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료) : ");
            String flag = sc.next();
            if (flag.equals("exit")) {
                break;
            }
        }
        sc.close();
    }
}