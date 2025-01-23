package main.java.hu.pinterbeci.algorithms;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadTrial {

    protected  String className = ThreadTrial.class.getName();

    public static void threadTrial() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        //thread-safe miatt, míg egyik thread a fent definialt adatstrukturan dolgozik, azt a többi bevárja
// Writing in one thread
        new Thread(() -> {
            list.add("D");
            System.out.println("Element added: D");
        }).start();

// Reading in another thread
        new Thread(() -> {
            for (String element : list) {
                System.out.println("Read element: " + element);
            }
        }).start();

    }
}
