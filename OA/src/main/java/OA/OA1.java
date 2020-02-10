package OA;

import java.util.Arrays;

import OA.OABaseClass;

public class OA1 extends OABaseClass{

    public static void main(String[] args) {

        OA1 testProfile = new OA1();

        String s1 = "aAbBzZ";
        testProfile.printOA(s1, testProfile.getLargestAlphabet(s1), 'Z');

        String s2 = "baaabbabbb";
        testProfile.printOA(s2, testProfile.getLongestNonRepeatSubString(s2), 7);
        s2 = "";
        testProfile.printOA(s2, testProfile.getLongestNonRepeatSubString(s2), 0);
        s2 = "a";
        testProfile.printOA(s2, testProfile.getLongestNonRepeatSubString(s2), 1);
        s2 = "asdfdadsss";
        testProfile.printOA(s2, testProfile.getLongestNonRepeatSubString(s2), 9);

        String s3 = "abc";
        testProfile.printOA(s3, testProfile.removeSmallestCharacter(s3), "ab");

        int[] n1 = {4,1,5,4};
        testProfile.printOA(testProfile.toIntList(n1), testProfile.widestPath(n1,n1), 3);
        
        int[] n2 = {6,10,1,4,3};
        testProfile.printOA(testProfile.toIntList(n2), testProfile.widestPath(n2,n2), 4);

        int[] n3 = {5,5,5,7,7,7};
        testProfile.printOA(testProfile.toIntList(n3), testProfile.widestPath(n3,n3), 2);
    }

    public OA1() {
        super();
    }

    public int widestPath(int[] X, int[] Y) {
        Arrays.sort(X);
        int maxWidth = 0;

        for(int i=0; i<X.length-1; ++i) {
            maxWidth = Math.max(maxWidth, X[i+1]-X[i]);    
        }
        return maxWidth;
    }

    public String removeSmallestCharacter(String s) {
        printFunction();
        for(int i=0; i<s.length()-1; ++i) {
            printDebug((int)s.charAt(i+1));
             printDebug((int)s.charAt(i));
            if((int)s.charAt(i+1) < (int)s.charAt(i)) {
                return s.substring(0, i) + s.substring(i, s.length());
            }
        }
        return s.substring(0, s.length()-1);
    }
    
    /*
            baaabbabbbb
             |  |
    max = right - left > max ? right -left : max;
    count: 1

    right != right-1

    ++left != left-1
    */
    public int getLongestNonRepeatSubString(String s) {
        printFunction();
        if(s == null || s.length() == 0)
            return 0;
            
        int max = 1;
        int left = 0;
        int right = 1;

        int count = 2;

        while(right < s.length()) {
            if(s.charAt(right-1) == s.charAt(right)) {
                --count;
            }
            else {
                count = 2;
            }
            if(count == 0) {
                left = right - 1;
                ++count;
            }
            printDebug(s.substring(left, right+1));
            max = Math.max(max, right - left + 1);
            ++right;
        }

        return max;
    }

    public int getLongestNonRepeatSubStringWrong(String s) {
        printFunction();
        if(s == null || s.length() == 0)
            return 0;
        if(s.length() == 1)
            return 1;

        int max = 0;
        int left = 0;
        int right = 0;
        int count = 2;
        while(++right < s.length()) {
            if(s.charAt(right) != s.charAt(right-1)) {
                --count;
            }
            if(count == 0) {
                do {
                    ++left;
                }
                while(s.charAt(left-1) == s.charAt(left));

                ++count;
            }
            printDebug(s.substring(left, right+1));
            max = Math.max(max, right - left + 1);
        }
        return Math.max(max, right - left + 1);
    }

    /*
    "aAbBzZ" return "Z"
    "aAbBb" return "B"
    */
    public char getLargestAlphabet(String s) {
        printFunction();
        char res = ' ';
        int max = Integer.MIN_VALUE;
        boolean[] lower = new boolean[26];

        for(int i=0; i<s.length(); ++i) {
            char ch = s.charAt(i);
            if(Character.isLowerCase(ch)) {
                lower[ch - 'a'] = true;
            } 
        }
        for(int i=0; i<s.length(); ++i) {
            char ch = s.charAt(i);
            if(Character.isUpperCase(ch)) {
                if(lower[ch - 'A'] && (int)(ch - 'A') > max) {
                    max = (int)(ch - 'A');
                    res = ch;
                }
            }
        }
        
        return res;
    }
}