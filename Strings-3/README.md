# Strings-3

## Problem1 
 Integer to English Words (https://leetcode.com/problems/integer-to-english-words/)
 
 Approached Solution: In this approach we will break the input number into groups of three digits and process each group recursively and then we will convert it into English words. First we will define an array to store english representations of number below 20, tens multiples, and large denominations like thousands, millions, and billions. We will start with least significant group of three digits and recursively convert it using the helper function. We will use the helper function in which, for numbers < 20 we will directly map to the below_20 array. Then for numbers < 100, we will use the hundreds array for tens and recursively call the helper for the remainder. For numbers >= 100 we will use the hundreds array for tens and recursively call the helper for the remainder. After combining all these results for each group with the corresponding denomination from the thousands array, we will trim trailing spaces before returning the result.

 Time Complexity: O(N) where N is the number digits of an input which will be at max 10 so still we can consider it as O(1).

 Space Complexity: O(1) as we are not using any auxillary space here.

 Approached Code:

 class Solution {

    String[] below_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

        String[] hundreds = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        String[] thousands = {"", "Thousand", "Million", "Billion"};
 
    public String numberToWords(int num) {

        if(num == 0) {
            return "Zero";
        }

        String word = "";

        int i = 0;

        while(num > 0) {
            if(num % 1000 != 0) {
                word = helper(num % 1000) + thousands[i] + " " + word;
            }

            num = num / 1000;
            i++;
        }

        return word.trim();

    }

    public String helper(int num) {
        if(num == 0) {
            return "";
        } else if(num < 20) {
            return below_20[num%20] + " ";
        } else if (num < 100) {
            return hundreds[num/10] + " " + helper(num%10);
        } else {
            return below_20[num/100] + " Hundred " + helper(num%100);
        }
    }
}



## Problem2 

Basic Calculator || (https://leetcode.com/problems/basic-calculator-ii/)

Time Complexity - O(n) where n is length of the string

Space Complexity - O(1) as we are not using any extra spaces

Explaination - This algorithm handles operator precedence correctly by using tail variable (handling mainly multiplication and division). First we initialized num to 0 last sign to + cal to 0 and tail to 0. Then we are checking if current character is number or operator.

number - 

num = prev_num * 10 + curr_num

operator - 

+ -> total + num and tail will be changed to num

- -> total - num and tail will be changed to -num

* -> total - tail + tail * num and tail will be changed to tail * num

/ -> total - tail + tail / num and tail will be changed to tail / num

at the end of this will update num to 0 and last sign to last taken character from string. But this does not take last digit after last operator into account. So, for that we simply added a condition.

Code - 

class Solution {
    public int calculate(String s) {
        
        int num = 0;
        char lastSign = '+';

        s = s.trim();
        int n = s.length();

        int cal = 0;
        int tail = 0;

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                num = num*10 + c - '0';
            } 
            
            if((!Character.isDigit(c) && c != ' ') || i == n-1) {
                if(lastSign == '+') {
                    cal = cal + num;
                    tail = num;
                } else if(lastSign == '-') {
                    cal = cal - num;
                    tail = -num;
                } else if(lastSign == '*') {
                    cal = cal - tail + tail*num;
                    tail = tail*num;
                } else {
                    cal = cal - tail + tail/num;
                    tail = tail/num;
                }

                num = 0;
                lastSign = c;
            }
            
        }
        
        return cal;
    }
}



## Problem 3

Basic Calculator | (https://leetcode.com/problems/basic-calculator/)

Approached Solution: In this approach we are evaluating a mathematical expression given as a string s that may include +, -, parenthesis (), and integers. We will use stack to handle nested parentheses and evaluate expressions in a Last-In-First-Out manner. lastsign will track the last operator + or - and num accumulates numbers as digits are read. On encountering open parenthesis it will store the corrent context. On encountering a closing parenthesis it will evaluate the expression within the parentheses and will apply the context. If the character is digit it will update num and it it's an operator it will process the accumulated num and will reset for the next number. At the end, all value in the stack will be summed to compute the result.

Time Complexity: O(n) as string is processed once and stack operations are O(1).

Space Complexity: O(n) as in worst case scenario stack will store all the numbers.

Approached Code:

class Solution {
    public int calculate(String s) {
        
        s = s.trim();
        int n = s.length();

        int num = 0;
        char lastSign = '+';

        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {

                num = num * 10 + c - '0';

            } else if(c == '(') {

                if(lastSign == '+') {
                    st.push(1);
                } else {
                    st.push(-1);
                }
                st.push(Integer.MAX_VALUE);
                
                num = 0;
                lastSign = '+';

            } else if(c == ')') {

                if(lastSign == '+') {
                    st.push(num);
                } else {
                    st.push(-num);
                }

                int inter = 0;

                while(st.peek() != Integer.MAX_VALUE) {
                    inter = inter + st.pop();
                }

                st.pop();
                int popped = st.pop();
                inter = inter * popped;
                st.push(inter);

                num = 0;
                lastSign = '+';

            } else if (!Character.isDigit(c) && c != ' ') {
                if(lastSign == '+') {
                    st.push(num);
                } else {
                    st.push(-num);
                }

                num = 0; 
                lastSign = c;
            }
        }

        int calc = 0;

        if(s.charAt(n-1) != ')') {

            if(lastSign == '+') {
                st.push(num);
            } else {
                st.push(-num);
            }

        }

        while(!st.isEmpty()) {
            calc = calc + st.pop();
        }

        return calc;

    }
}


