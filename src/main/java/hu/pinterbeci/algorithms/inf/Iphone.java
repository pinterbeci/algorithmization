package main.java.hu.pinterbeci.algorithms.inf;

public class Iphone implements Phone {
    boolean ringing;

    //interfész metódusok default public-ok, így azok implementációja során, vagy azonos vagy
    //nagyobb access modifier-rel override-olunk,
    //mivel default public, aminél nagyobb access modifier nincs, így az implementációt is public-kal kell megjelölni
    public boolean ring() {
        ringing = !ringing;
        return ringing;
    }
}
