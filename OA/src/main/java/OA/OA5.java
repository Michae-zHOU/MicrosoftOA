package OA;

import java.util.HashSet;
import java.util.Set;

public class OA5 extends OABaseClass {

    public static void main(String[] args) {
        
        OA5 testProfile = new OA5();
        
        String s = "WRRWWRW";
        testProfile.printOA(s, testProfile.minSwapsToGroupRedBalls(s), 2);

        s = "WWRWWWRWR";
        testProfile.printOA(s, testProfile.minSwapsToGroupRedBalls(s), 4);

        s = "WWW";
        testProfile.printOA(s, testProfile.minSwapsToGroupRedBalls(s), 0);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<100000; ++i) {
            sb.append(s);
        }
        testProfile.printOA("RW...", testProfile.minSwapsToGroupRedBalls(sb.toString()), -1);

        int[] n1 = {1,2,3,-4};
        testProfile.printOA(testProfile.toIntList(n1), testProfile.largestNum(n1), 0);
    
        int[] n2 = {3,2,-2,5,-3};
        testProfile.printOA(testProfile.toIntList(n2), testProfile.largestNum(n2), 3);

        s = "abczd";
        testProfile.printOA(s, testProfile.lexicographicalSmallestString(s), "abcd");
    
        int[] n3 = {-1,1,3,3,3,2,3,2,1,0};
        testProfile.printOA(testProfile.toIntList(n3), testProfile.particleVelocity(n3), 5);
    }

    public OA5() {
        super();
    }

    public int particleVelocity(int[] n) {
        printFunction();

        if(n.length < 3) 
            return 0;
        int res = 0;
        for(int i=0; i<n.length-2; ++i) {
            if(n[i+2]-n[i+1] == n[i+1]-n[i]) {
                ++res;
                for(int j=i+2; j<n.length-1; ++j) {
                    if(n[j+1]-n[j] == n[i+1]-n[i])
                        ++res;
                }
            }
        }
        
        return res;
    }

    public int minSwapsToGroupRedBalls(String s) {
        printFunction();

        int cnt = 0;
        for(int i=0; i<s.length(); ++i) {
            if(s.charAt(i) == 'R')
                ++cnt;
        }
        int left = 0;
        int right = s.length() - 1;
        int swapCount = 0;
        while(left < right) {
            //printDebug("s.charAt(left): "+s.charAt(left));
            //printDebug("s.charAt(right): "+s.charAt(right));
            if(s.charAt(left) == 'R' && s.charAt(right) == 'R') {
                cnt -= 2;
                //printDebug("swapCount: "+swapCount);
                swapCount += (right - left - 1 - cnt);
                ++left;
                --right;
            }
            else if(s.charAt(left) != 'R') {
                ++left;
            }
            else {
                --right;
            }
        }

        return swapCount;
    }

    public String lexicographicalSmallestString(String s) {
        int remove = s.length() - 1;
        for(int i=1; i<s.length(); ++i) {
            if(s.charAt(i-1) > s.charAt(i)) {
                remove = i-1;
                break;
            }
        }
        return s.substring(0, remove) + s.substring(remove + 1, s.length()); 
    }

    public int largestNum(int[] nums) {
        printFunction();

        int max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; ++i) {
            if(set.contains(nums[i])) {
                max = Math.max(Math.abs(nums[i]), max);
            }
            else {
                set.add(-nums[i]);
            }
        }

        return max == Integer.MIN_VALUE? 0 : max;
    }
}