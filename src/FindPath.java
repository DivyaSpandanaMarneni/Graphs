import java.util.*;

/**
 * Created by marne on 2/16/2017.
 */
public class FindPath {

    public List<Integer> getPath(GraphG graph,Integer start, Integer end){
        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> pathMap = new HashMap<>();
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        pathMap.put(start,-1);
        while (!stack.isEmpty() ){
            Integer popped = stack.pop();
            visited[popped] = true;
            List<VertexV> adjList = graph.getAdjVertices(popped);
            for (VertexV vert :
                    adjList) {
                if(vert.getVertex() == end){
                    pathMap.put(vert.getVertex(),popped);
                    stack.removeAllElements();
                    break;
                }
                else {
                    stack.push(vert.getVertex());
                    visited[vert.getVertex()] = true;
                    pathMap.put(vert.getVertex(), popped);
                }
            }


        }

        Integer mapStart = end;
        result.add(mapStart);
        for(int i=0;i<pathMap.size();i++){
            if(mapStart == -1 || mapStart == start)
                break;
            mapStart = pathMap.get(mapStart);
            result.add(mapStart);
        }

        return result;
    }

    public static void main(String[] args){
        GraphG graph = new GraphG(6);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(3,1);
        graph.addEdge(2,4);
        graph.addEdge(4,5);
        graph.addEdge(5,3);
        //graph.addEdge(4,3);
        FindPath findPath = new FindPath();
        List<Integer> path = findPath.getPath(graph,2,3);
        for(int i=path.size()-1;i>=0;i--) {
            System.out.print(path.get(i)+"->");
        }

        System.out.println();

    }
}
