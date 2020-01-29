package cisco.java.challenge;

import java.util.ArrayList;
import java.util.List;

/**
 *  cisco.java.challenge.GNode where each node contains a value
 */
public class GNodeV<T> implements GNode {
        private final T value;
        private final List<GNodeV<T>> children;
        GNodeV(T value, List<GNodeV<T>> children) {
            this.value = value;
            this.children = children;
    }

    public static <T> GNodeV<T> fromValue(T value){
            return new GNodeV<T>(value, new ArrayList<>());
    }

    public GNodeV<T> insert(GNodeV<T> other){
            children.add(other);
            return this;
    }

    public GNodeV<T> insert(T value) {
            GNodeV<T> node = new GNodeV<>(value, new ArrayList<>());
            this.children.add(node);
            return this;
    }

    public String getName() {
        //default the name is the value
        return value.toString();
    }

    public GNode[] getChildren() {
        return children.toArray(new GNode[0]);
    }
}
