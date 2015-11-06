package Logic;

import java.util.Date;

/**
 * Created by lucasbocanegra on 27/10/15.
 */
public class Subject {

    private String name;
    private float  mean; /* media geral da disciplina */
    private Date date;

    public Subject() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMean() {
        return mean;
    }

    public void setMean(float mean) {
        this.mean = mean;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
