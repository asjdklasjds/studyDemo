package com.study.stack;

public class Counter {
    public static void main(String[] args) {
        String str = "50+8*5+10+9*5"; // 5+40+1+45
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        int index = 0;
        String keepNum = "";
        while (index < str.length()){
            char c = str.substring(index, index + 1).charAt(0);
            if (isOperator(c)){
                // 是运算符
                if (!operStack.isEmpty()){
                    if (priority(c) <= priority((char) lookOver(operStack))){
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int operation = operation(num1, num2, (char) operStack.pop());
                        numStack.push(operation);
                        operStack.push(c);
                    }else {
                        operStack.push(c);
                    }
                }else {
                    operStack.push(c);
                }
            }else {
                // 不是运算符
                // 处理多位数
                keepNum += c;
                if (str.length() == (index + 1)) {
                    numStack.push(Integer.valueOf(keepNum));
                }else {
                    if (isOperator(str.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.valueOf(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
        }

        while (!operStack.isEmpty()){
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int operation = operation(num1, num2, (char) operStack.pop());
            numStack.push(operation);
        }
        System.out.println(numStack.pop());

    }

    /**
     * 判断是否为运算符
     * @param c
     * @return
     */
    public static boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * 查看stack中栈顶的元素
     * @param stack
     * @return
     */
    public static int lookOver(ArrayStack stack){
        return stack.getStack()[stack.getTop()];
    }

    /**
     * 返回运算符的优先级
     * @param c
     * @return
     */
    public static int priority(char c){
        if (c == '*' || c == '/'){
            return 1;
        }else if (c == '+' || c == '-') {
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 获取运算结果
     * @param num1
     * @param num2
     * @param c 运算符
     * @return 结果
     */
    public static int operation(int num1, int num2, char c){
        int res = 0;
        switch (c){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return  res;
    }
}

