package me.gabagool.pico.collections;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("unused")
public class CheckedArrayList<T> extends ArrayList<T> {
    private final Class<T> chClass;
    public CheckedArrayList(Class<T> chClass) {
        this.chClass = chClass;
    }
    @SuppressWarnings("unchecked")
    public void addAllMixed(Object... objects){
        for (Object obj: objects) {
            if (obj.getClass().isArray()) { for (Object elem : (Object[]) obj) if (elem.getClass().isInstance(chClass)) this.add((T) elem); }
            else if (obj instanceof Collection<?>) { for (Object elem : ((Collection<?>) obj)) if (elem.getClass().isInstance(chClass)) this.add((T) elem); }
            else if (obj.getClass().isInstance(chClass)) { this.add((T) obj); }
            else throw new RuntimeException("Bad object type: " + obj.getClass());
        }
    }
}
