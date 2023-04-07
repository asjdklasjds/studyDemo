package com.study.stack;

import java.util.*;

/**
 * 逆波兰表达式 + - * /
 */
public class ReversePolandExpression {
    public static void main(String[] args) {

        String expression = "2+((2+3)*10)-19/11+9+8+1*100/10-100";
        List<String> expressionList = toFixExpressionList(expression);
        System.out.println(expressionList);
        System.out.println();
        List<String> suffixList = prefixToSuffixList(expressionList);
        System.out.println(suffixList);
        System.out.println("====>  " + (2+((2+3)*10)-19/11+9+8+1*100/10-100));
        System.out.println("---->  " + operation(suffixList));

        /**
        // 2 + 3 * 39 * 9 - 10 / 3 转逆波兰表达式=> 3 39 * 9 * 2 - 10 3 /
        String expression = "3 39 * 9 * 2 + 10 3 / -";
        int operation = operation(toList(expression));
        System.out.println(2 + 3 * 39 * 9 - 10 / 3);
        System.out.println("通过逆波兰计算的结果 = " + operation);
         */
    }


    /**
     * 拆分中缀表达式为List
     * @param expression 中缀表达式
     * @return 拆分的中缀表达式 List
     */
    public static List<String> toFixExpressionList(String expression){
        List<String> list = new ArrayList<>();
        String replace = expression.replace(" ", "");
        System.out.println(replace);
        char c;
        String append;
        for (int i = 0; i < replace.length();) {
             c = replace.substring(i, i + 1).charAt(0);
             // 如果不是数字则直接报错到list中
             if (c < 48 || c > 57){
                list.add(""+c);
                i++;
             } else {
                 // 否则继续遍历后面的字符直到不是数字（多位数获取）
                 append = "";
                 do {
                    append += c;
                    i++;
                    if (i >= replace.length()) break;
                    c = replace.substring(i, i + 1).charAt(0);
                 } while (c > 47 && c < 58);
                 list.add(append);
             }
        }
        return list;
    }

    /**
     * 中缀表达式转后缀表达式（逆波兰表达式）
     * @param expression 转换成List的中缀表达式
     * @return 后缀表达式（逆波兰表达式）
     */
    public static List<String> prefixToSuffixList(List<String> expression){
        Stack<String> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        for (String item : expression) {
            // 判断如果是数字 或者是 ( 则直接添加到 list 中
            if (item.matches("\\d+"))
                list.add(item);
            else if (Objects.equals(item,"("))
                stack.push(item);
            else if (Objects.equals(item,")")){
                // 如果是 ) 的话就读取 stack 里面的运算符  直到 （ 为止
                while (stack.size() > 0 && !Objects.equals(stack.peek(),"("))
                    list.add(stack.pop());
                // 找到 （ 因为 循环使用的是 peek(只是查看 不是出栈) 因此 在下面将 （ 出栈
                stack.pop();
            } else {
                // item 当前运算符 小于等于 栈里面的运算符 就取出 添加到 list中 重复此操作 直到栈无元素或者 栈中运算符 小于 当前运算符为止
                while (stack.size() > 0 && comparisonOperator(item) <= comparisonOperator(stack.peek()))
                    list.add(stack.pop());
                // 将当前运算符压入栈
                stack.push(item);
            }
        }
        // 将所有的栈中的元素添加到 list 中
        while (stack.size() > 0) list.add(stack.pop());
        return list;
    }


    public static int comparisonOperator(String operator){
        if (Objects.equals(operator,"+") || Objects.equals(operator,"-")){
            return 0;
        } else if (Objects.equals(operator,"*") || Objects.equals(operator,"/")){
            return 1;
        } else {
            System.out.println("不存在此运算符" + operator);
            return -1;
        }
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
                // list中只会有数字和运算符 出现运算符则取出栈中的两个数据进行计算
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
