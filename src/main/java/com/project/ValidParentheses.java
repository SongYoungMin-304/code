package com.project;

// 문제: 유효한 괄호 (Valid Parentheses)
// 괄호 문자열 s가 주어졌을 때, 올바르게 열고 닫혔는지 확인하라.
// - 열린 괄호는 반드시 같은 종류의 닫힌 괄호로 닫혀야 한다.
// - 열린 괄호는 올바른 순서로 닫혀야 한다.
// 예) "()" → true, "()[]{}" → true, "(]" → false

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()){
            if(c == '(' || c == '{' || c == '['){
                stack.add(c);
            }else{
                Character top = stack.pop();

                if(c == ')' && top != '(') return false;
                if(c == '}' && top != '{') return false;
                if(c == ']' && top != '[') return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();

        String[] testCases = {"()", "()[]{}", "(]", "([)]", "{[]}", ""};
        boolean[] expected  = {true,  true,    false, false,  true,   true};

        for (int i = 0; i < testCases.length; i++) {
            boolean result = vp.isValid(testCases[i]);
            String status = result == expected[i] ? "PASS" : "FAIL";
            System.out.printf("[%s] isValid(\"%s\") = %b%n", status, testCases[i], result);
        }
    }
}
