package Logic;

import java.util.ArrayList;

/**
 * Created by lucasbocanegra on 27/10/15.
 */
public class Course {

    private String name;
    private String color;
    private String local;
    private ArrayList<Disciplina> disciplinas;

    public Course(){
        disciplinas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
