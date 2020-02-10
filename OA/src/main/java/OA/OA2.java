package OA;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import OA.OABaseClass;

public class OA2 extends OABaseClass {

    public static void main(String[] args) {

        OA2 testProfile = new OA2();

        String s1 = "a?b";
        s1 = "asd????fd?b";
        //s1 = "a???????????b";
        s1 = "??????????";
        testProfile.printOA(s1, testProfile.questionMarkConverter(s1), null);

        int n = 3;
        testProfile.printOA(n, testProfile.toIntList(testProfile.sumZero(n)), null);

        n = 1;
        testProfile.printOA(n, testProfile.toIntList(testProfile.sumZero(n)), null);

        n = 5;
        testProfile.printOA(n, testProfile.toIntList(testProfile.sumZero(n)), null);

        String s = "BAAABAB";
        testProfile.printOA(s, testProfile.minDeletion(s), 2);

        s = "BBABAA";
        testProfile.printOA(s, testProfile.minDeletion(s), 3);

        s = "AABBBB";
        testProfile.printOA(s, testProfile.minDeletion(s), 0);

        String[] S1  = {"un","iq","ue"};
        testProfile.printOA(testProfile.toStringList(S1), testProfile.maxLengh(testProfile.toStringList(S1)), 4);

        String[] S2 = {"cha","r","act","ers"};
        testProfile.printOA(testProfile.toStringList(S2), testProfile.maxLengh(testProfile.toStringList(S2)), 6);

        String[] S3 = {"abcdefghijklmnopqrstuvwxyz"};
        testProfile.printOA(testProfile.toStringList(S3), testProfile.maxLengh(testProfile.toStringList(S3)), 26);
    }

    int result;
    public int maxLengh(List<String> arr) {
        printFunction();
        result = 0;
        if(arr == null)
            return 0;
        dfs(arr, "", 0);
        return result;
    }

    public void dfs(List<String> arr, String path, int idx) {
        boolean isUnique = uniqueCharacter(path);

        if(isUnique)
            result = Math.max(path.length(), result);

        if(!isUnique || idx == arr.size())
            return;

        for(int i=idx; i<arr.size(); ++i) {
            dfs(arr, path+arr.get(i), i+1);
        }
    }

    public boolean uniqueCharacter(String sb) {
        Set<Character> set = new HashSet<>();
        for(int i=0; i<sb.length(); ++i) {
            if(set.contains(sb.charAt(i)))
                return false;
            set.add(sb.charAt(i));
        }
        return true;
    }

    /*
    1. del A
    2. del B
    3. partially delete A if B is more
    4. if not meeting A, delete B
    https://leetcode.com/discuss/interview-question/421975/
    */
    public int minDeletion(String s) {
        printFunction();
        int delA = 0;
        int delB = 0;
        int delAorB = 0;
        for(int i=0; i<s.length(); ++i) {
            if(s.charAt(i) == 'A') {
                ++delA;
                delAorB += (delB > delAorB)? 1:0;
            }
            else {
                ++delB;
                delAorB += (delA == 0)? 1:0;
            }
        }
        return Math.min(delAorB, Math.min(delA, delB));
    }

    public int[] sumZero(int n) {
        printFunction();

        int[] arr = new int[n];
        int left = 0;
        int right = n-1;
        for(int i = n/2; i>0; i--) {
            arr[left++] = -i;
            arr[right--] = i;
        }
        
        return arr;
    }

    public String questionMarkConverter(String s) {
        printFunction();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); ++i) {
            if(Character.isLetter(s.charAt(i)))
                sb.append(s.charAt(i));
            else {
                int prev = -1;
                int next = 26;
                if( i > 0) {
                    prev = sb.charAt(i-1) - 'a';
                } 
                if( i < s.length() - 1 && s.charAt(i+1) != '?') {
                    next = s.charAt(i+1) - 'a';
                }
                int guess = guessNext1(prev, next);
                sb.append((char)((int)('a') + guess));
            }
        }
        return sb.toString();
    }

    public int guessNext2(int prev, int next) {
        int guess = 0;
        for(; guess<26; ++guess) {
            if(guess != prev && guess != next) {
                break;
            }
        }
        return guess;
    }

    public int guessNext1(int prev, int next) {
        Random random = new Random();

        int guess;
        do {
            guess = random.nextInt(26);
            printDebug(charAdder(guess));
        }
        while(guess == prev || guess == next);

        return guess;
    }
}