package Collection.UseStack;

import java.util.Deque;
import java.util.LinkedList;

// 进阶练习
//
// 请利用Stack把字符串中缀表达式编译为后缀表达式，然后再利用栈执行后缀表达式获得计算结果：
public class Test2 {
    public static void main(String[] args) {
        String exp = "1 + 2 * (9 - 5)";
        SuffixExpression se = compile(exp);
        int result = se.execute();
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));
    }

    static SuffixExpression compile(String exp) {
        // TODO:
        // 存储后缀表达式
        StringBuilder postfix = new StringBuilder();
        // 运算符栈
        Deque<Character> operatorStack = new LinkedList<>();
        char[] chars = exp.toCharArray();

        for (char c : chars) {
            if (Character.isDigit(c)) {
                // 如果是操作数，存入后缀表达式
                postfix.append(c);
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.pop(); // 弹出 (
            } else if (isOperator(c)) {
                // 如果是运算符，与栈顶运算符比较优先级，如果栈顶运算符优先级高，则出栈
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    postfix.append(operatorStack.pop());
                }
                // 将运算符压入栈
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return new SuffixExpression(postfix.toString());
    }

    // 判断是否运算符
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // 定义运算符优先级
    static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            return 0; // 匹配 (
        }
    }
}

class SuffixExpression {
    private String postfixExp;

    public SuffixExpression(String postfixExp) {
        this.postfixExp = postfixExp;
    }

    int execute() {
        // TODO:
        Deque<Integer> operandStack = new LinkedList<>();
        for(char c : postfixExp.toCharArray()) {
            if (Character.isDigit(c)) {
                // 如果是操作数，压入栈中
                operandStack.push(Character.getNumericValue(c));
            } else if (Test2.isOperator(c)) {
                // 如果是运算符，进行计算
                int operand2 = operandStack.pop();
                int operand1 = operandStack.pop();
                int result = performOperation(operand1, operand2, c);
                operandStack.push(result);
            }
        }

        return operandStack.pop();
    }

    int performOperation(int operand1, int operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("无效的运算符：" + operator);
        }
    }
}
