package OA;

import java.util.Map;
import java.util.List;
import java.util.Set;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

public class InterviewPrep2 extends OABaseClass {
    private Map<V, List<V>> map;
    private List<V> list;

    public InterviewPrep2() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean containsCycle(V curr, Set<V> visited) {
        if(!visited.contains(curr)) {
            return true;
        }
        visited.add(curr);
        for(V next : getNeighbor(curr)) {
            if(containsCycle(next, visited)) {
                return true;
            }
        }
        visited.remove(curr);
        return false;
    }

    public void addVertex(V v) {
        if(!list.contains(v))
            list.add(v);
        map.put(v, new ArrayList<>());
    }

    public void addEdge(V v1, V v2) {
        map.getOrDefault(v1, new ArrayList<>()).add(v2);
    }

    private List<V> getNeighbor(V v) {
        return map.getOrDefault(v, new ArrayList<>());
    }

    private class V {
        public int val;

        public V(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("%d",val);
        }
    }
}