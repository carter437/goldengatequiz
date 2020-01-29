package cisco.java.challenge.traversal;

import cisco.java.challenge.GNode;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class GNodeOps {
    public static ArrayList<GNode> walkGraph(GNode node) {
        return BreadFirstSearch.instance.search(node);
    }

    public static ArrayList<ArrayList<GNode>> paths(GNode node) {
        ArrayList<ArrayList<GNode>> result = new ArrayList<>();
        hlpr(node, ImmutableList.of(), result);
        return result;
    }

    private static void hlpr(GNode node, ImmutableList<GNode> currPath, ArrayList<ArrayList<GNode>> result) {
        //detect cycle
        if (currPath.contains(node)) {
            result.add(finalPath(currPath, Optional.empty()));
        } else if (node.getChildren().length == 0) {
            result.add(finalPath(currPath, Optional.of(node)));
        } else {
            ImmutableList<GNode> updPath = ImmutableList.<GNode>builder().addAll(currPath).add(node).build();
            Arrays.stream(node.getChildren()).forEach(child -> {
                hlpr(child, updPath, result);
            });
        }

    }

    private static ArrayList<GNode> finalPath(ImmutableList<GNode> currPath, Optional<GNode> optNode) {
        ArrayList<GNode> fp = new ArrayList<>(currPath.size());
        fp.addAll(currPath);
        optNode.ifPresent(fp::add);
        return fp;
    }
}
