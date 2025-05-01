package org.example.calculator.lv3;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator {
    private List<Double> history = new ArrayList<>();

    private void store(double result){
        history.add(result);
    }

    public <T extends Number> Double calculate(T a, T b, OperationType op){
        double left = a.doubleValue();
        double right = b.doubleValue();

        double result = 0.0;
        switch (op){
            case PLUS:
                result = left + right;
                store(result);
                break;
            case MINUS:
                result = left - right;
                store(result);
                break;
            case TIMES:
                result = left * right;
                store(result);
                break;
            case DIVIDE:
                if (right == 0.0) {
                    System.out.println("0으로 나눌 수 없습니다. (Error : Division by zero)");
                    return null;
                }
                result = left / right;
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

    public <T extends Number> List<Double> findHistoryGreaterThan(T pivot) {
        double doubleValue = pivot.doubleValue();
        return history.stream()
                .filter(r -> r > doubleValue)
                .toList();
    }
}
