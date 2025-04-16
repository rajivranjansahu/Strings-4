// TC: O(n)
// SC: O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        if(s == null || s.length() == 0) return 0;
        char sign = '+';
        if(s.charAt(0) == '-') sign = '-';
        if(s.charAt(0) != '+' && s.charAt(0) != '-' && !Character.isDigit(s.charAt(0))) return 0; //  determine if string starts with valid char for forming the number
        int num = 0;
        int limit = Integer.MAX_VALUE / 10; // 2147483647 / 10 = 214748364
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                if(sign == '+') {
                    if((num > limit) || (num == limit && c >= '7')) {
                        return Integer.MAX_VALUE;
                    }
                }
                else {
                    if((num > limit) || (num == limit && c >= '8')) {
                        return Integer.MIN_VALUE;
                    }
                }
                num = num * 10 + ( c - '0'); // Before multiplying num by 10 & adding next digit, check whether doing so would  exceed maximum allowed value
            }
            else if(i != 0) { // non-digit is encountered after the first character
                break;
            }
        }
        return sign == '-' ? -num : num;
    }
}