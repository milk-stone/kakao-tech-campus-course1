package org.example.calculator.lv3;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CalculatorLv3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArithmeticCalculator calc = new ArithmeticCalculator();

        while (true) {
            System.out.println("---------------------------------------------");
            System.out.print("첫 번째 숫자를 입력하세요: ");
            Number a = readNumber(sc);
            System.out.print("두 번째 숫자를 입력하세요: ");
            Number b = readNumber(sc);

            if (a == null || b == null) {
                System.out.println("숫자가 입력되지 않았습니다. 다시 시도해주세요.");
                continue;
            }

            System.out.print("사칙연산 기호를 입력하세요 (+, -, *, /): ");
            char operator = sc.next().charAt(0);

            Optional<OperationType> op = OperationType.from(operator);
            if (op.isEmpty()) {
                System.out.println("잘못된 연산 기호입니다.");
                continue;
            }
            OperationType opType = op.get();

            Double result = calc.calculate(a, b, opType);
            if (result != null) System.out.println("연산 결과 : " + a + " " + operator + " " + b + " = " + result);

            System.out.print("저장 기록 중 N 보다 큰 수를 불러옵니다. N을 입력해주세요: ");
            Number n = readNumber(sc);
            List<Double> greaterList = calc.findHistoryGreaterThan(n);
            System.out.println("저장 기록 중 N 보다 큰 수: " + greaterList);

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

    private static Number readNumber(Scanner sc) {
        if (sc.hasNextInt())      return sc.nextInt();
        if (sc.hasNextDouble())   return sc.nextDouble();
        String trash = sc.next();
        return null;
    }
}