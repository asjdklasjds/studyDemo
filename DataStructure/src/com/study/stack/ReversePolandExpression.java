package com.study.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式 + - * /
 */
public class ReversePolandExpression {
    public static void main(String[] args) {
        // 2 + 3 * 39 * 9 - 10 / 3 转逆波兰表达式=> 3 39 * 9 * 2 - 10 3 /
        String expression = "3 39 * 9 * 2 + 10 3 / -";
        int operation = operation(toList(expression));
        System.out.println(2 + 3 * 39 * 9 - 10 / 3);
        System.out.println("通过逆波兰计算的结果 = " + operation);
    }

    public static List<String> toList(String expression){
         return Arrays.asList(expression.split(" "));
    }

    // 通过逆波兰表达式运算表达式的结果
    public static int operation(List<String> list){
        Stack<String> stack = new Stack<>();
        for (String ele:list){
            // 匹配多个数字
            if (ele.matches("\\d+")){
                stack.push(ele);
            }else {
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int res = 0;
                if ("+".equals(ele)){
                    res = num1 + num2;
                } else if ("-".equals(ele)){
                    res = num1 - num2;
                } else if ("*".equals(ele)){
                    res = num1 * num2;
                } else if ("/".equals(ele)){
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("不支持此运算符"+ele);
                }
                stack.push(res+"");
            }
        }
        return Integer.valueOf(stack.pop());
    }
}
