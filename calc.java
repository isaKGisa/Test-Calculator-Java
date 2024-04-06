import java.util.Arrays;
import java.util.Scanner;

public class calc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();

        ExpressionParser parser = new ExpressionParser();
        Calculation calculation = new Calculation();

        Expression expression = parser.parseExpression(input);
        int result = calculation.calculate(expression);

        System.out.println(result);
    }
}

class Expression {
    private int[] operands;
    private String[] operators;

    private int startIndex;
    public Expression(int[] operands, String[] operators, int startIndex) {
        this.operands = operands;
        this.operators = operators;
        this.startIndex = startIndex;
    }

    public int[] getOperands() {
        return operands;
    }

    public String[] getOperators() {
        return operators;
    }
    public int getStartIndex(){
        return startIndex;
    }
}

class ExpressionParser {
    public Expression parseExpression(String input) {
        String[] operands = input.split("[+\\-*/]");
        String[] operators = input.replaceAll("[\\d]", "").split("");

        int startIndex = 0;
        if (operators.length > 2) {
            startIndex = 1;
            operands = Arrays.copyOfRange(operands, 1, operands.length);
        }

        int[] parsedOperands = new int[operands.length];
        for (int i = 0; i < operands.length; i++) {
            parsedOperands[i] = Integer.parseInt(operands[i]);
        }
        if (operators.length>2){

            parsedOperands[0] = -parsedOperands[0];


        }

        return new Expression(parsedOperands, operators, startIndex);
    }
}

class Calculation {
    public int calculate(Expression expression) {
        int[] operands = expression.getOperands();
        String[] operators = expression.getOperators();
        int startIndex = expression.getStartIndex();

        int result = operands[0];
        int operandIndex = 1;

        for (int i = startIndex; i < operators.length; i++) {
            int nextOperand = operands[operandIndex++];

            switch (operators[i]) {
                case "+":
                    result += nextOperand;
                    break;
                case "-":
                    result -= nextOperand;
                    break;
                case "*":
                    result *= nextOperand;
                    break;
                case "/":
                    if (nextOperand == 0) {

                        return 0;
                    }
                    result /= nextOperand;
                    break;
                default:

                    return 0;
            }
        }

        return result;
    }
}


