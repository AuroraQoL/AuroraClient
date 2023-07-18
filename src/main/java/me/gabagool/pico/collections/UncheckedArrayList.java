package me.gabagool.pico.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("unused")

public class UncheckedArrayList<T> extends ArrayList<T> {
    @SuppressWarnings("unchecked")
    public void addAllMixed(Object... objects) {
        for (Object obj : objects) {
            if (obj.getClass().isArray()) this.addAll(Arrays.asList((T[]) obj));
            else if (obj instanceof Collection<?>) {
                for (Object elem : (Collection<?>) obj) this.add((T) elem);
            } else this.add((T) obj);
        }
    }
}
