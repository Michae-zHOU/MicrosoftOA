package OA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

abstract public class OAUtility {
    
    public char charConvert(int i) {
        return (char)i;
    }

    public char charAdder(int i) {
        return (char)((int)'a' + i);
    }

    public List<Integer> toIntList(int[] n) {
        return Arrays.stream(n).boxed().collect(Collectors.toList());
    }

    public List<String> toStringList(String[] n) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, n);
        return list;
    }

    public <T> String aggregateTwoParameter(T a, T b) {
        return a.toString() + " " + b.toString();
    }

    public <T> String aggregateThreeParameter(T a, T b, T c) {
        return a.toString() + " " + b.toString() + " " + c.toString();
    }

    public String aggregateIntArrInt(int[] arr, int n) {
        return Arrays.toString(arr) + " " + n;
    }

    public String aggregateIntArrIntArrInt(int[] arr1, int[] arr2, int n) {
        return Arrays.toString(arr1) + " " + Arrays.toString(arr2) + " " + n;
    }
}