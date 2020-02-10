package OA;

import java.util.Arrays; 
import java.util.Collections; 

import OA.OABaseClass;

public class OA3 extends OABaseClass {

    public static void main(String[] args) {
        OA3 testProfile = new OA3();

        int[] arr = {4,2,3,0,3,1,2};
        int start = 5;
        testProfile.printOA( testProfile.aggregateIntArrInt(arr, start)
                           , testProfile.canReach(arr, start)
                           , true);

        int[] A = {1,2,3,3};
        int[] B = {2,3,1,4};
        int N = 4;
        testProfile.printOA( testProfile.aggregateIntArrIntArrInt(A, B, N)
                           , testProfile.findMaxNetworkRank(A, B, N)
                           , 4);

        int M = 439;
        testProfile.printOA( M, testProfile.getMaxPossibleValue(M), 5439);
        M = 945;
        testProfile.printOA( M, testProfile.getMaxPossibleValue(M), 9545);
        M = -439;
        testProfile.printOA( M, testProfile.getMaxPossibleValue(M), -4359);
        M = -945;
        testProfile.printOA( M, testProfile.getMaxPossibleValue(M), -5945);
        M = 0;
        testProfile.printOA( M, testProfile.getMaxPossibleValue(M), 50);

        
        Integer[] n1 = new Integer[]{5,2,1};
        testProfile.printOA( n1, testProfile.minStep(n1), 3);

        Integer[] n2 = new Integer[]{4,5,5,4,2};
        testProfile.printOA( n2, testProfile.minStep(n2), 6);
    }

    public OA3() {
        super();
    }

    // https://leetcode.com/discuss/interview-question/364618/
    public int minStep(Integer[] p) {
        printFunction();

        if(p == null || p.length == 0)
            return 0;
        
        int res = 0;
        Arrays.sort(p, Collections.reverseOrder()); 

        for(int i=0; i<p.length-1; ++i) {
            if(p[i+1] < p[i])
                res += i+1;
        }

        return res;
    }

    public int getMaxPossibleValue(int N) {
        printFunction();
        
        boolean positive = N >= 0;
        StringBuilder sb = new StringBuilder(String.valueOf(N));
        
        if(positive) {
            int insertPoint = -1;
            for(int i=0; i<sb.length(); ++i) {
                if((sb.charAt(i) - '0') < 5) {
                    printDebug("sb.charAt(i): " + sb.charAt(i));
                    printDebug("sb.charAt(i): " + sb.charAt(i));
                    insertPoint = i;
                    break;
                }
            }
            printDebug("InsertPoint: "+insertPoint);
            if(insertPoint == -1) {
                return 5 * 10 * sb.length() + N;
            }
            else {
                return Integer.parseInt(sb.substring(0, insertPoint)+"5"+sb.substring(insertPoint, sb.length()));
            }
        }
        else {
            int insertPoint = 1;
            for(int i=1; i<sb.length(); ++i) {
                if((sb.charAt(i) - '0') > 5) {
                    insertPoint = i;
                    break;
                }
            }

            return Integer.parseInt(sb.substring(0, insertPoint)+"5"+sb.substring(insertPoint, sb.length()));
        }
    }

    /* Todo
    Leetcode 1306
    */
    public boolean canReach(int[] arr, int start) {
        printFunction();
        return canReachUtil(arr, start);
    }

    public boolean canReachUtil(int[] arr, int start) {
        
        if( start>=0 && start<arr.length && arr[start]<arr.length ) {
            int jump = arr[start];
            arr[start] += jump;
            return jump == 0 || canReachUtil(arr, start + jump) || canReachUtil(arr, start - jump);
        }

        return false;
    }

    public int findMaxNetworkRank(int[] A, int[] B, int N) {
        printFunction();
        
        int maxRank = 0;
        int len = A.length;

        int[] edgesCount = new int[N+1];

        for(int i=0; i<len; ++i) {
            edgesCount[A[i]]++;
            edgesCount[B[i]]++;
        }

        for(int i=0; i<len; ++i) {
            int localMax = edgesCount[A[i]] + edgesCount[B[i]] - 1;
            maxRank = Math.max(localMax, maxRank);
        }

        return maxRank;
    }
}