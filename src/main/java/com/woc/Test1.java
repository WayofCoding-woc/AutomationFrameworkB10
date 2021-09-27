package com.woc;

public class Test1 {
    public static void main(String[] args) {
        int a = 5;
        int b = 6;
        int sum = getSumOfTwoNumber(a, b);
        System.out.println(sum);
    }

    private static int getSumOfTwoNumber(int a, int b) {
        int sum = a + b;
        return sum;
    }

}
