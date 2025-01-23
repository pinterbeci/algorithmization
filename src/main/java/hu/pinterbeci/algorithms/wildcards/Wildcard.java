package main.java.hu.pinterbeci.algorithms.wildcards;

import java.util.ArrayList;
import java.util.List;

public class Wildcard<T extends Extendable> {

    public void useOfWildcards() {
        //extends read only !!!!!!!!! during the generic collections, és pont ez miatt nem tudok elemet hozzáadni
        //final List<? extends Extendable> subParents = new ArrayList<>();

        //ezen esetben a generikus referencia meghatározásra került, hogy egy
        // Extendable-t vár el, polimorfizmus miatt a Child, Parent is
        final List<Extendable> subParents = new ArrayList<>();


        //konkretizalva van, hogy azon osztályok melyeknek super osztálya a 'Parent' (beleértve önmagát)
        //igénybe tudják venni
        final List<? super Parent> childOfParent = new ArrayList<>();

        final Extendable extendable = new Extendable();
        final Child child = new Child();
        final Parent parent = new Parent();

        List<? super Parent> list = new ArrayList<>();
        list.add(parent);
        list.add(child);

        subParents.add(parent);
        subParents.add(extendable);
        subParents.add(child);

        childOfParent.add(parent);
        childOfParent.add(child);
    }


}
