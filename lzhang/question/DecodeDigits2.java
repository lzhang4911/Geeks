package lzhang.question;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

// 07/31/2018: VMware phone interview

//We encode a string, s, by performing the following sequence of actions:
//Replace each character with its ASCII value representation.
//Reverse the string.
//
//For example, the table below shows the conversion from the string "Go VMWare" to the ASCII string "711113286778797114101":
//
//Character   G  o        V   M   W   a   r    e
//ASCII Value 71 111  32  86  77  87  97  114  101
//
//We then reverse the ASCII string to get the encoded string 101411797877682311117.
//
//For reference, the characters in s are ASCII characters within the range 10 - 126 which include special characters.
//
//Complete the decode function in the editor below. It has one parameter: 
//  encoded - A reversed ASCII string denoting an encoded string s.
//  
//The function must decode the encoded string and return the list of ways in which s can be decoded.

public class DecodeDigits2 extends BaseUtil {
    public static void test() {
        DecodeDigits2 p = new DecodeDigits2();
        
        // original encoded string
        String encoded = "711113286778797114101";
        
        // reverse it before decoding
//        encoded = reverse(encoded);
        
        // then decode
        Collection<String> list = p.decode(encoded);
        
        print( "After decoding: " + Arrays.toString( list.toArray(new String[0])) );
    }

    private Collection<String> decode(String encoded) {
        if(encoded == null) return null;
        
        Collection<String> list = new HashSet<String>();
        
        decodeHelper(encoded.toCharArray(), 0, encoded.length(), "", list);
        
        return list;
    }
    
    private void decodeHelper(char[] arr, int index, int len, String result, Collection<String> list) {
        if(arr == null || len < 2) return;
        
        if(index == len) {
            // got one result
            list.add(result);
            return;
        }
        
        for(int i = index; i < len-2; i++) {
            if(i+2 <= len) {
                int v = toIntValue(arr[i], arr[i+1]);
                if(isValid(v)) {
                    // decoding here
                    char r = lookup(v);
                    
                    decodeHelper(arr, i+2, len, result + r, list);
                }
            }
                
            if(i+3 <= len) {
                int v = toIntValue(arr[i], arr[i+1], arr[i+2]);
                if(isValid(v)) {
                    // decoding here
                    char r = lookup(v);
                    
                    decodeHelper(arr, i+3, len, result + r, list);
                }
            }
        }
    }

    /*
     * There can be up to 3 chars, each is a single digit. Convert
     * them into an integer value
     */
    private int toIntValue(char ... dd) {
        int value = 0;
        
        int len = dd.length;
        if(len != 2 && len != 3) {
            // invalid
            return -1;
        }
        
        for(int i = 0; i < len; i++) {
            value = (byte)(10 * value + (dd[i] - '0'));
        }
        
        return value;
    }
    
    /**
     * characters will be readable if value >= 32!
     * 
     * @param value
     * @return
     */
    private boolean isValid(int value) {
        return (value >= 10 && value <= 126);
    }

    private char lookup(int v) {
        return (char)v;
    }
}
