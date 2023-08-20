package collection.useStack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

// 进阶练习2：
//
// 请把带变量的中缀表达式编译为后缀表达式，执行后缀表达式时，传入变量的值并获得计算结果：
public class Test3 {
    public static void main(String[] args) {
        String exp = "x + 2 * (y - 5)";
        SuffixExpressions se = compile(exp);
        Map<String, Integer> env = Map.of("x", 1, "y", 9);
        int result = se.execute(env);
        System.out.println(exp + " = " + result + " " + (result == 1 + 2 * (9 - 5) ? "✓" : "✗"));
    }

    static SuffixExpressions compile(String exp) {
        // TODO:
        // 存储后缀表达式
        StringBuilder postfix = new StringBuilder();
        // 运算符栈
        Deque<Character> operatorStack = new LinkedList<>();
        char[] chars = exp.toCharArray();

        for (char c : chars) {
            if (Character.isDigit(c) || Character.isLetter(c)) {
                // 如果是操作数或变量，存入后缀表达式
                postfix.append(c);
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.pop(); // 弹出 (
            } else if (Test2.isOperator(c)) {
                // 如果是运算符，与栈顶运算符比较优先级，如果栈顶运算符优先级高，则出栈
                while (!operatorStack.isEmpty() && Test2.precedence(operatorStack.peek()) >= Test2.precedence(c)) {
                    postfix.append(operatorStack.pop());
                }
                // 将运算符压入栈
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return new SuffixExpressions(postfix.toString());
    }
}

class SuffixExpressions {
    private String postfixExp;

    public SuffixExpressions(String postfixExp) {
        this.postfixExp = postfixExp;
    }

    int execute(Map<String, Integer> env) {
        // TODO:
        Deque<Integer> operandStack = new LinkedList<>();
        for (char c : postfixExp.toCharArray()) {
            if (Character.isDigit(c)) {
                // 如果是操作数，压入栈中
                operandStack.push(Character.getNumericValue(c));
            } else if (Character.isLetter(c)) {
                // 如果是变量，从Map中获取对应的值
                operandStack.push(env.get(Character.toString(c)));
            } else if (Test2.isOperator(c)) {
                // 如果是运算符，进行计算
                int operand2 = operandStack.pop();
                int operand1 = operandStack.pop();
                int result =  performOperation(operand1, operand2, c);
                operandStack.push(result);
            }
        }

        return operandStack.pop();
    }

    // 运算
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