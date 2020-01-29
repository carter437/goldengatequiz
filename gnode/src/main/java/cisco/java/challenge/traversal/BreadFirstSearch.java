package cisco.java.challenge.traversal;

import cisco.java.challenge.GNode;
import java.util.*;

public class BreadFirstSearch implements GraphTraversal {
    public static final BreadFirstSearch instance = new BreadFirstSearch();

    public ArrayList<GNode> search(GNode node) {
        Queue<GNode> queue = new LinkedList<>();
        queue.add(node);

        ArrayList<GNode> result = new ArrayList<>();

        while(!queue.isEmpty()){
            GNode currentNode = queue.remove();
            //detect cycles
            if(result.contains(currentNode)) continue;

            result.add(currentNode);

            //no children
            if(currentNode.getChildren().length == 0) continue;

            queue.addAll(Arrays.asList(currentNode.getChildren()));
        }

        return result;
    }

}
