package OA;

import java.util.*;

import OA.OABaseClass;

public class OA4 extends OABaseClass {

    public static void main(final String[] args) {
        OA4 testProfile = new OA4();

        int[] arr = {1,2,3,4,5};
        int n = 3;
        // [[[5],[1,4],[2,3]]
        testProfile.printOA(testProfile.aggregateIntArrInt(arr, n), 
                            testProfile.partitionIntoNSubsetWithBalancedSum(arr, n),
                            null);

        String S = "1A 2F 1C";
        int N = 2;
        testProfile.printOA(testProfile.aggregateTwoParameter(S, N),
                            testProfile.assignSeat(N, S), 
                            2);

        String s = "mamad";
        testProfile.printOA(s, testProfile.minAdjSwapsMakePalindrome(s), 3);
        s = "asflkj";
        testProfile.printOA(s, testProfile.minAdjSwapsMakePalindrome(s), -1);
        s = "aabb";
        testProfile.printOA(s, testProfile.minAdjSwapsMakePalindrome(s), 2);
        s = "ntiin";
        testProfile.printOA(s, testProfile.minAdjSwapsMakePalindrome(s), 1);
    
        String d = "Wed";
        int k = 2;
        testProfile.printOA(d, testProfile.dayOfWeek(d, k), "Fri");

        d = "Sat";
        k = 23;
        testProfile.printOA(d, testProfile.dayOfWeek(d, k), "Mon");

    }

    public OA4() {
        super();
    }

    // https://leetcode.com/discuss/interview-question/451422/
    public int fairIndex(int[] A, int[] B) {
        printFunction();
        int sumA = 0;
        int sumB = 0;
        int currA = 0;
        int currB = 0;
        int indexCount = 0;
        for(int i=0; i<A.length; ++i) {
            sumA += A[i];
            sumB += B[i];
        }

        for(int i=0; i<A.length-1; ++i) {
            currA += A[i];
            currB += B[i];
            indexCount += (currA == currB && currA * 2 == sumA && currB * 2 == sumB)? 1:0;
        }
        return indexCount;
    }

    // https://leetcode.com/discuss/interview-question/398047/
    public String dayOfWeek(String S, int K) {
        printFunction();

        final String[] DAYS = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int dayIndex = Arrays.asList(DAYS).indexOf(S);
        dayIndex += K;
        dayIndex %= 7;
        return DAYS[dayIndex];
    }

    // https://leetcode.com/discuss/interview-question/351783/
    public int minAdjSwapsMakePalindrome(String s) {
        printFunction();

        return minswaps(s.toCharArray());
    }

    private boolean isPalindrome(char[] s1) {
        HashSet<Character> hs = new HashSet<>();
        for(char c: s1) {
            if(hs.contains(c)) {
                hs.remove(c);				
            }				
            else {
                hs.add(c);
            }				
        }
        return hs.size() < 2;
    }
    
    private void swap(char[] s2, int i, int j) {
        char tmp = s2[i];
        s2[i] = s2[j];
        s2[j] = tmp;
    }
    
    /*
    0. check palindrome
    Three pointers
    i is left, j is right, k is j
    1. find the character match i by moving k toward left
    2. then move k to j

    1. if not able to find the match character
    2. move i rightward to the middle
    */
    public int minswaps(char[] s1) {
        int result = 0;
        if(!isPalindrome(s1))
            return -1;
        int i = 0;
        int j = s1.length - 1;
        int k = j;
        while(i < j) {
            k = j;
            while(s1[i] != s1[k] && k > i)
                k--;
            if(s1[i] == s1[k] && i != k) {
                while(k < j) {
                    swap(s1,k,k+1);
                    k++;
                    result++;
                }
                i++;
                j--;
            }
            else {
                swap(s1,i,i+1);
                result++;
            }
            
        }
        return result;
    }

    // https://leetcode.com/discuss/interview-question/430981/
    public List<List<Integer>> partitionIntoNSubsetWithBalancedSum(final int[] arr, final int n) {
        printFunction();
        final List<List<Integer>> res = new ArrayList<>();
        printDebug(res);
        return res;
    }

    // https://leetcode.com/discuss/interview-question/492652/
    public int assignSeat(int N, String S) {
        printFunction();
        
        int availableOption = 0;
        String[] occupiedSeats = S.split(" ");
        int[][] seats = new int[N]['K' - 'A' + 1];

        for(String occupiedSeat : occupiedSeats) {
            int row = Integer.parseInt(occupiedSeat.substring(0, occupiedSeat.length() - 1)) - 1;
            int col = occupiedSeat.charAt(occupiedSeat.length()-1) - 'A';
            printDebug("row+col: " + row + " " + col);
            seats[row][col] = 1;
        }
        for(int r = 0; r < N; ++r) {
            printDebug("row: "+r);
            boolean isBtoEAvail = isGroupAvailable(1 ,4 , r, seats);
            boolean isDtoGAvail = isGroupAvailable(3, 6, r, seats);
            boolean isFtoJAvail = isGroupAvailable(5, 8, r, seats);
            printDebug("isBtoEAvail: " + isBtoEAvail);
            printDebug("isDtoGAvail: " + isDtoGAvail);
            printDebug("isFtoJAvail: " + isFtoJAvail);
            if(isBtoEAvail && isFtoJAvail) {
                availableOption += 2;
            }
            else if(isBtoEAvail || isDtoGAvail || isFtoJAvail) {
                availableOption++;
            }
        }

        return availableOption;
    }

    public boolean isGroupAvailable(int left, int right, int row, int[][] seats) {
        for(int col = left; col < right; ++col) {
            if(seats[row][col] == 1)
                return false;
        }
        return true;
    }
}