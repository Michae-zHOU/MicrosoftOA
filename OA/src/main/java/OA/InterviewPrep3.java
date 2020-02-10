package OA;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class InterviewPrep3 extends OABaseClass {

    public InterviewPrep3() {
    }

    public boolean courseSchedule(List<int[]> prerequisites, int numCourses) {
        
        // Initialize Map
        Vertex[] vertexes = new Vertex[numCourses];
        Arrays.setAll(vertexes, label -> new Vertex(label));

        // Add Edge
        for(int[] prerequisite : prerequisites) {
            int preCourse = prerequisite[0];
            int postCourse = prerequisite[1];
            vertexes[preCourse].postCourses.add(postCourse);
        }

        for(int index = 0; index < numCourses; ++index) {
            if(hasCycle(vertexes[index], vertexes))
                return false;
        }

        return true;
    }

    private boolean hasCycle(Vertex preCourse, Vertex[] vertexes) {
        if(preCourse.state == State.Visited) {
            return false;
        }
        else if(preCourse.state == State.Visiting) {
            return true;
        }

        preCourse.state = State.Visiting;
        for(int postCourse : preCourse.postCourses) {
            if(hasCycle(vertexes[postCourse], vertexes)) {
                return true;
            }
        }

        preCourse.state = State.Visited;
        return false;
    }

    private enum State {
        Nonvisited,
        Visiting,
        Visited
    }

    private class Vertex {
        public int label;
        public List<Integer> postCourses;
        public State state;

        public Vertex(int label) {
            this.label = label;
            this.postCourses = new ArrayList<>();
            this.state = State.Nonvisited;
        }
    }
}