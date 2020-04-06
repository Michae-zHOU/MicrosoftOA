package OA;

import java.util.HashMap;
import java.util.Map;

public class InterviewPrep5 extends OABaseClass {

    public InterviewPrep5() {
        
    }
 
    public static Pair findTwoNonOverlappingSubarraysSumEqualsK(int[] nums, int k) {
        int presum = 0;
        Interval min = null; 
        // scan from left and min is the interval equals k with minimum length
		// key is presum, value is its corresponding latest index (we override the same key since we need the minimum)

        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        // left[i] is the minimum interval scanning from left ending in index i
        Interval[] left = new Interval[n];
        map.put(0, -1);

        for(int i=0; i<n; i++) {
            presum += nums[i];
            Integer index = map.get(presum - k);
            if (index != null) {
                // interval: nums[index - 1] ~ nums[i]
                if (min == null || min.length()>i-index) {
                    min = new Interval(index + 1, i);
                }
            }
            left[i] = min;
            map.put(presum, i);
        }

        if(min == null) {
            return null;
        }

        presum = 0;
        min = null; // Scan from right
        Pair res = null;
        map = new HashMap<>();
        map.put(0, n);
        for(int i=n-1; i>0; i--) {
            presum += nums[i];
            Integer index = map.get(presum - k);
            if(index != null) {
                // interval: nums[i] ~ nums[index - 1]
                if(min == null || min.length() > index-i) {
                    min = new Interval(i, index-1);
                    if(left[i-1]!=null && (res==null || left[i-1].length() + min.length() < res.length()))
                        res = new Pair(left[i-1], min);
                }
            }
            map.put(presum, i);
        }
        return res;
    }

    public static class Interval {
        public int start;
        public int end;

        public Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
		public String toString() {
			return "[start=" + start + ", end=" + end + "]";
        }
        
        public int length() {
            return this.end - this.start + 1;
        }
    }

    public static class Pair {
        Interval first;
        Interval second;

        public Pair(Interval first, Interval second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair [first=" + first + ", second=" + second + "]";
        }

        public int length() {
            return this.first.length() + this.second.length();
        }
    }
}