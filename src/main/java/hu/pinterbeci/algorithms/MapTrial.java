package main.java.hu.pinterbeci.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTrial {
    public void mapTrial() {

        List<String> stringLists = new ArrayList<>();
        stringLists.add("aa");
        stringLists.add("bab");
        ThreadTrial threadTrial = new ThreadTrial();
        System.out.println(threadTrial.className);
        final Map<String, ArrayList<String>> objectObjectHashMap = new HashMap<>();
        // List<String> stringLists -ként van deklarálva, viszont a Map ArrayList<String> típusú referenciát(!!!!!) és nem a referencia
        // milyen impelentációra mutat, a referencia típusa lehet esetén megmutatkozhat a polimorfizmus

        //objectObjectHashMap.put("data", stringLists);

        final Map<String, ? extends List<String>> objectObjectHashMap2 = new HashMap<>();
        //? extends List<String> egy read-only mapet hoz létre, mivel nincs konkretizálva, hogy melyik subtype-ot tudjuk megadni
        //kusza lehet az egész, bedobhatok egy arraylistet, vagy akár egy Vectort is mivel, subtype-ja a Listnek

        //objectObjectHashMap2.put("data", stringLists);

        var objectObjectHashMap3 = new HashMap<>();
        //var kvázi kibekkel mindent, JS megoldás hülye biztos emiatt nem type-safe
        objectObjectHashMap3.put("data", stringLists);

        // final Map<String, List<String>> objectObjectHashMap4 = new HashMap<String, ArrayList<String>>();
        //a Java generic esetén figyelni kell az állandóságra, változatlanságra --> 'invariant'
        //Map<String, List<String>> nem ugyanaz, mint  HashMap<String, ArrayList<String>>
        //List<Object> list = new ArrayList<String>(); --> nem fordul le, mivel invariant

        //objectObjectHashMap4.put("data", stringLists);

        final Map<String, List<String>> objectObjectHashMap5 = new HashMap<>();
        //konkretizálva, hogy a map mmit fogad el, mint value, ami teljesen megegyezik a referenciánkkal
        objectObjectHashMap5.put("data", stringLists);


    }
}
