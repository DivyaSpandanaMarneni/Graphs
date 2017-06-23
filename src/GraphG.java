import java.util.*;

/**
 * Created by marne on 2/15/2017.
 */
//Main Graph class to use for all the functionailites
public class GraphG {

    public int vertices;
    public int[][] graphMatrix ;
    public List<VertexV>[] graphAdj;

    public GraphG(int vertices){
        this.vertices = vertices;
        graphMatrix = new int[vertices][vertices];

        graphAdj = new LinkedList[vertices];
        for(int i=0;i<vertices;i++){
            graphAdj[i] = new LinkedList<>();
        }
    }

    public List<VertexV>[] getGraphAdj() {
        return graphAdj;
    }

    public void setGraphAdj(List<VertexV>[] graphAdj) {
        this.graphAdj = graphAdj;
    }

    public int[][] getGraphMatrix() {
        return graphMatrix;
    }

    public void setGraphMatrix(int[][] graphMatrix) {
        this.graphMatrix = graphMatrix;
    }





    public int size(){
        return vertices;
    }

    public Set<VertexV> getVertexSet(GraphG graph){
        Set<VertexV> vertices = new HashSet<>();

        for(int i=0;i<graph.size();i++){
            for (VertexV ver :
                    graph.getAdjVertices(i)) {
                vertices.add(ver);
            }
        }


        return vertices;
    }

    public void addEdge(int start, int end){
        if(start<0 || end>=vertices){
            System.out.println("out of range. cant add");
            return;
        }

        graphMatrix[start][end] = 1;
        graphAdj[start].add(new VertexV(end));


        return;
    }

    public void removeEdge(int start,int end){
        if(start<0 || end>=vertices){
            System.out.println("out of range. cant remove");
            return;
        }

        graphMatrix[start][end] = 0;
        graphAdj[start].remove(new VertexV(end));


        return;
    }


    public List<VertexV> getAdjVertices(int vertex){
        List<VertexV> adjList = graphAdj[vertex];
        return adjList;
    }

    public Map<Integer,List<Integer>> getAllEdges(GraphG graphG){
        Map<Integer,List<Integer>> edgeMap = new HashMap<>();
        for(int i=0;i<graphG.size();i++){
            List<VertexV> adjList = graphG.getAdjVertices(i);
            List<Integer> intList = new LinkedList<>();
            for (VertexV v :
                    adjList) {
                intList.add(v.getVertex());

            }
            edgeMap.put(i,intList);
        }
        return edgeMap;
    }

    public List<VertexV>[] reverseEdges(GraphG graphG){
        List<VertexV>[] adjListRev = new List[graphG.size()];
        Map<Integer,List<Integer>> allEdges = getAllEdges(graphG);
        Map<Integer,List<VertexV>> reversed = new HashMap<>();
        for (Integer key :
                allEdges.keySet()) {
            for (Integer vertex :
                    allEdges.get(key)) {
                List<VertexV> innerList = reversed.get(vertex);
                if( innerList == null) {
                    List<VertexV> listAdj = new LinkedList<>();
                    listAdj.add(new VertexV(key));
                    reversed.put(vertex, listAdj);
                }
                else
                    reversed.get(vertex).add(new VertexV(key));

            }
        }

        for(int i=0;i<reversed.size();i++){
            adjListRev[i] = reversed.get(i);
        }


        return adjListRev;
    }
}
