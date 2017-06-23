import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by marne on 2/15/2017.
 */
public class DetectCycleDirectedGraph {

    //Set<Integer> wSet = new ConcurrentSkipListSet<>();
    Set<Integer> whiteSet = new HashSet<>();
    Set<Integer> whiteSetCopy = new HashSet<>();
    Set<Integer> graySet =new HashSet<>();
    Set<Integer> blackSet = new HashSet<>();

//    public boolean hasCycle(GraphG graphG){
//        //whiteSet.add(new VertexV(0));
//        for(int i=0;i<graphG.size();i++){
//            for (VertexV vert :
//                    graphG.getGraphAdj()[i]) {
//
//                whiteSet.add(vert);
//            }
//        }
//
//        while(whiteSet.size() > 0){
//            VertexV current = whiteSet.iterator().next();
//            if(dfs(graphG,current,whiteSet,graySet,blackSet))
//                return true;
//        }
//        return false;
//    }

//    public boolean dfs(GraphG graph,VertexV current, Set<VertexV> whiteSet, Set<VertexV> graySet, Set<VertexV> blackSet){
//
//            whiteSet.remove(current);
//            graySet.add(current);
//
//        for (VertexV vertex :
//                graph.getAdjVertices(current.getVertex())) {
//            if(blackSet.contains(vertex))
//                continue;
//            else if(graySet.contains(vertex)){
//                return true;
//            }
//            else{
//                return dfs(graph,vertex,whiteSet,graySet,blackSet);
//            }
//
//        }
//        graySet.remove(current);
//        blackSet.add(current);
//        return false;
//
//    }

    //if all the vertices are moved to the black set this means that there is not cycle in this graph
    public boolean detectCycle(GraphG graph) {

        Stack<Integer> stack = new Stack<>();
        List<VertexV> adj = new LinkedList<>();
        for (int i=0;i<graph.size();i++) {
            whiteSet.add(i);
        }

        whiteSetCopy.addAll(whiteSet);

        //Integer verId;
        Iterator<Integer> iterator = whiteSet.iterator();
        //synchronized (whiteSet){
        for (Integer verId :
                whiteSet) {

            whiteSetCopy.removeIf(v->v.equals(verId));
            //verId = iterator.next();
            //iterator.remove();
            graySet.add(verId);
            stack.add(verId);
            Integer popped = null;
            while (!stack.isEmpty()) {
                popped = stack.pop();
                adj = graph.getAdjVertices(popped);
                Iterator<VertexV> iter = adj.iterator();
                while (iter.hasNext()){
                    VertexV v = iter.next();
                    if (whiteSetCopy.contains(v.getVertex())) {
                        //whiteSet.iterator().remove();
                        whiteSetCopy.removeIf(s->s.equals(v.getVertex()));
                        graySet.add(v.getVertex());
                        stack.push(v.getVertex());
                    } else if (graySet.contains(v.getVertex())) {
                        return true;
                    }
                }

                graySet.remove(popped);
                blackSet.add(popped);
            }


        }

        whiteSet.clear();

        return false;
    }

    public static void main(String[] args){
        GraphG graph = new GraphG(6);


        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(3,1);
        graph.addEdge(2,4);
        graph.addEdge(4,5);
        //graph.addEdge(5,3);
        //graph.addEdge(4,3);



        DetectCycleDirectedGraph detectCycleDirectedGraph = new DetectCycleDirectedGraph();
        System.out.println("With secnd method using iterators");
        System.out.println("Cycle exits : "+detectCycleDirectedGraph.detectCycle(graph));

        //System.out.println("Cycle exits : "+detectCycleDirectedGraph.hasCycle(graph));

        System.out.println();
        //bfs.breadthFirstSearch(graph,2);
        System.out.println();

        System.out.println("BFS in undirected"); // edges will be repeated in the adjacency List

        GraphG graphUn = new GraphG(6);
        graphUn.addEdge(0,1);
        graphUn.addEdge(0,2);
        graphUn.addEdge(1,2);
        graphUn.addEdge(1,3);
        graphUn.addEdge(2,4);
        graphUn.addEdge(4,5);
        graphUn.addEdge(5,3);
        graphUn.addEdge(1,0);
        graphUn.addEdge(2,0);
        graphUn.addEdge(2,1);
        graphUn.addEdge(3,1);
        graphUn.addEdge(4,2);
        graphUn.addEdge(5,4);
        graphUn.addEdge(3,5);


    }

}
