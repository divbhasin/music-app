package ddra.com.musicapp;

/**
 *Object class holding all specs from DATABASE relevant to options selected by user
 */
public class Theory {

    String name;
    String abbre;
    int step[];

    public Theory (String name, String abbre, int step[]){
        this.name = name;
        this.abbre = abbre;
        this.step = step;
    }

    //public String getName () {return name;}
    //public String getAbbre () {return abbre;}
    //public int getStep () {return step;}
}
