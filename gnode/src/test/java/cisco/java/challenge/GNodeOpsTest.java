package cisco.java.challenge;

import cisco.java.challenge.traversal.GNodeOps;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GNodeOpsTest {
    @Test
    public void walkGraphShouldReturnNodesInNonCyclicalGraph(){
        GNodeV<String> node = GNodeV.fromValue("A").insert("B").insert("C");
        Set<String> actual = GNodeOps.walkGraph(node).stream().map(GNode::getName).collect(Collectors.toSet());
        Set<String> expected = Set.of("A","B","C");
        assertEquals(expected,actual);
    }

    @Test
    public void walkGraphShouldReturnNodesInCyclicalGraph(){
        GNodeV<String> node = GNodeV.fromValue("A").insert("B").insert("C");
        ((GNodeV<String>)node.getChildren()[0]).insert(node);
        Set<String> actual = GNodeOps.walkGraph(node).stream().map(GNode::getName).collect(Collectors.toSet());
        Set<String> expected = Set.of("A","B","C");
        assertEquals(expected,actual);
    }

    @Test
    public void pathsShouldReturnCorrectPathsInNonCyclicalGraph(){
        GNodeV<String> b = GNodeV.fromValue("B").insert("E").insert("F");
        GNodeV<String> c = GNodeV.fromValue("C").insert("G").insert("H").insert("I");
        GNodeV<String> a = GNodeV.fromValue("A").insert(b).insert(c).insert("D");

        List<List<String>> expected = List.of(
                List.of("A", "B", "E"),
                List.of("A","B","F"),
                List.of("A", "C", "G"),
                List.of("A", "C", "H"),
                List.of("A","C","I"),
                List.of("A","D")
        );

        List<List<String>> actual = GNodeOps.paths(a).stream().map(path -> path.stream().map(GNode::getName).collect(Collectors.toList())).collect(Collectors.toList());

        assertEquals(expected,actual);
    }

    @Test
    public void pathsShouldReturnCorrectPathsInCyclicalGraph(){
        GNodeV<String> g = GNodeV.fromValue("G");
        GNodeV<String> b = GNodeV.fromValue("B").insert("E").insert("F");
        GNodeV<String> c = GNodeV.fromValue("C").insert(g).insert("H").insert("I");
        GNodeV<String> a = GNodeV.fromValue("A").insert(b).insert(c).insert("D");

        //create cycle
        g.insert(c);

        List<List<String>> expected = List.of(
                List.of("A", "B", "E"),
                List.of("A","B","F"),
                List.of("A", "C", "G"),
                List.of("A", "C", "H"),
                List.of("A","C","I"),
                List.of("A","D")
        );

        List<List<String>> actual = GNodeOps.paths(a).stream().map(path -> path.stream().map(GNode::getName).collect(Collectors.toList())).collect(Collectors.toList());

        assertEquals(expected,actual);
    }
}
