package Logic;

import java.util.ArrayList;

/**
 * Created by lucasbocanegra on 27/10/15.
 */
public class Course {

    private String name;
    private ArrayList<Subject> subjects;

    Course(){
        subjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
