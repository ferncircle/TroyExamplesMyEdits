package com.troy.dynamic.gotit;

import java.util.HashSet;
import java.util.Set;

/**
 * Date 01/07/2016
 * @author Tushar Roy
 *
 * Given an expression with +, -, * and / operators. Tell if expression can evaluate to given result with 
 * different
 * parenthesis combination.
 * e.g expresson is 2*3-1 and result is 4, function should return true since 2*(3-1) evaluate to 4.
 *
 * Time complexity is O(n^5)
 * Space complexity is O(n^3)
 * 
 * Solution: Similar to break sentence or cut rod problem. Here T[i][j] holds all possible expression values between
 * 			operands i to j.
 * 			T[i][j]=All values at T[i][k] X all values at T[k+1][j]
 */
public class ExpressionEvaluation {

    public boolean evaluate(char[] expression, int result) {
        int operands[] = new int[expression.length/2 + 1];
        char operators[] = new char[expression.length/2];

        int index1 = 0;
        int index2 = 0;
        operands[index1++] = expression[0] - '0';
        for (int i = 1; i < expression.length; i += 2 ) {
            operators[index2++] = expression[i];
            operands[index1++] = expression[i+1] - '0';
        }

        Holder T[][] = new Holder[operands.length][operands.length];

        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T.length; j++) {
                T[i][j] = new Holder();
            }
        }

        for (int i = 0; i < operands.length; i++) {
            T[i][i].add(operands[i]);
        }

        for (int size = 2; size <= T.length; size++) {
            for (int start = 0; start <= T.length - size; start++) {
                int end = start + size - 1;
                for (int k = start; k < end; k++) {
                	Set<Integer> leftValues=T[start][k].values();
                	Set<Integer> rightValues=T[k + 1][end].values();
                    for (int x : leftValues) {
                        for (int y : rightValues) {
                            if (operators[k] == '/' && y == 0) {
                                continue;
                            }
                            T[start][end].add(operate(operators[k], x, y));
                        }
                    }
                }
            }
        }

        T[0][T.length-1].values().forEach((i -> System.out.print(i + " ")));

        for (int i : T[0][T.length - 1].values()) {
            if ( i == result) {
                return true;
            }
        }

        return false;
    }

    private int operate(char operator, int x, int y) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x*y;
            case '/':
                return x/y;
            default:
                throw new IllegalArgumentException();
        }
    }
    static class Holder {
        private Set<Integer> valueHolder = new HashSet<>();
        void add(Integer ch) {
            valueHolder.add(ch);
        }
        Set<Integer> values() {
            return valueHolder;
        }
    }

    public static void main(String args[]) {
        ExpressionEvaluation ee = new ExpressionEvaluation();
        System.out.println(ee.evaluate("9*3+1/7".toCharArray(), 0));
    }
}


