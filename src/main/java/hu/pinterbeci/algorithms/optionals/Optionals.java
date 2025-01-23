package main.java.hu.pinterbeci.algorithms.optionals;

import java.util.Optional;

public class Optionals {

    public static void tryOptionals() {
        String name = null;

        System.out.println("if the added value to optional is null:");
        String s = Optional.ofNullable(name).orElse(getMyDefault());
        System.out.println(s);
        name = "amla";

        System.out.println("if the added value to optional is not null:");
        String s1 = Optional.ofNullable(name).orElse(getMyDefault());
        System.out.println(s1);

        //konkluzio, mindenképpen végrehatjásra kerül az orElse, viszont akkor kapja meg az optional ezt az orElse értéket
        //ha null, ha nem null, akkor nem kap értéket az orElse-ből
    }

    private static String getMyDefault() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }
}
