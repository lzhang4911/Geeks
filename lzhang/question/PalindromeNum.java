package lzhang.question;

/**
 * Check if a give number is a palindrome number, such as 121, 1221
 * @author lzhang
 *
 */
public class PalindromeNum {
    public static boolean isPalindrome(int num) {
        return num == reverse(num);
    }

    /**
     * Assume positive number
     * Input 123, output shall be 321
     * @param num
     * @return
     */
    private static int reverse(int num) {
        int res = 0;
        
        while(num > 0) {
            res = 10 * res + num % 10;
            num = (int) (num/10);
        }
        
        return res;
    }
}
