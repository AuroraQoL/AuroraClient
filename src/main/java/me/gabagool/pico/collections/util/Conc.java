package me.gabagool.pico.collections.util;

import java.util.Set;
import static java.util.concurrent.ConcurrentHashMap.newKeySet;

@SuppressWarnings("unused")
public class Conc {
    public static <F> Set<F> newConcSet (int capacity) {
        return newKeySet(capacity);
    }
    public static <F> Set<F> newConcSet () {
        return newKeySet();
    }
}
