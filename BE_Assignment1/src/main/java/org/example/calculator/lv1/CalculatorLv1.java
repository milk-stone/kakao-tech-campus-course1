package org.example.calculator.lv1;

import java.util.Scanner;

public class CalculatorLv1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int a = sc.nextInt();
            System.out.print("두 번째 숫자를 입력하세요: ");
            int b = sc.nextInt();
            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator = sc.next().charAt(0);

            switch (operator){
                case '+':
                    System.out.print("연산 결과 : " + a + " " + operator + " " + b + " = " + (a + b));
                    break;
                case '-':
                    System.out.print("연산 결과 : " + a + " " + operator + " " + b + " = " + (a - b));
                    break;
                case '*':
                    System.out.print("연산 결과 : " + a + " " + operator + " " + b + " = " + (a * b));
                    break;
                case '/':
                    if (b == 0) {
                        System.out.println("0으로 나눌 수 없습니다. (Error : Division by zero)");
                        break;
                    }
                    System.out.println("연산 결과 : " + a + " " + operator + " " + b + " = " + ((float) a) / ((float) b));
                    break;
                default:
                    System.out.println("잘못된 사칙연산 기호입니다.");
                    break;
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
