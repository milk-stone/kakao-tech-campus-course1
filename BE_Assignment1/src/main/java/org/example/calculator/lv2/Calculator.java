package org.example.calculator.lv2;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<Double> history = new ArrayList<>();

    private void store(double result){
        history.add(result);
    }

    public Double calculate(int a, int b, char operator){
        double result = 0.0;
        switch (operator){
            case '+':
                result = (double) (a + b);
                store(result);
                break;
            case '-':
                result = (double) (a - b);
                store(result);
                break;
            case '*':
                result = (double) (a * b);
                store(result);
                break;
            case '/':
                if (b == 0) {
                    System.out.println("0으로 나눌 수 없습니다. (Error : Division by zero)");
                    return null;
                }
                result = (double) a / (double) b;
                store(result);
                break;
            default:
                System.out.println("잘못된 사칙연산 기호입니다.");
                return null;
        }
        return result;
    }

    public List<Double> getHistory() {
        return history;
    }

    // setter 설정, 그러나 사용하지 않았음.
    public void setHistory(List<Double> history) {
        this.history = history;
    }

    public void removeFirstDouble(){
        history.remove(0);
    }

}
