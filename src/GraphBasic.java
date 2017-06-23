import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marne on 11/1/2016.
 */

class VertexB<T>{

    int id;
    T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<VertexB<T>> adjVertices = new ArrayList<>();

    VertexB(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

    public void addAdjVertices(Edge<T> e,VertexB<T> v){
        adjVertices.add(v);
        edges.add(e);
    }


    public List<VertexB<T>> getAdjVertices(){
        return adjVertices;
    }

    public String toString(){
        return String.valueOf(id);
    }

    public List<Edge<T>> getEdges(){
        return edges;
    }

    public int getDegree(){
        return edges.size();
    }


}

class Edge<T>{
    private boolean isDirected = false;
    private VertexB<T> vertex1;
    private VertexB<T> vertex2;
    private int weight;

    Edge(VertexB<T> v1, VertexB<T> v2){
        this.vertex1 = v1;
        vertex2 = v2;

    }

    Edge(VertexB<T> v1, VertexB<T> v2,int weight, boolean isDirected){
        vertex1 = v1;
        vertex2= v2;
        this.weight = weight;
        this.isDirected = isDirected;
    }

    Edge(VertexB<T> vertex1, VertexB<T> vertex2,boolean isDirected){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    VertexB<T> getVertex1(){
        return vertex1;
    }

    VertexB<T> getVertex2(){
        return vertex2;
    }

    int getWeight(){
        return this.weight;
    }

    boolean isDirected(){
        return  isDirected;
    }




}
public class GraphBasic<T> { // basic graph with edges and weights associated wit hthe edges


    private List<Edge<T>> allEdges;
    private Map<Integer,VertexB<T>> allVertices;
    boolean isDirected;

    public GraphBasic(boolean isDirected){
        allEdges = new ArrayList<Edge<T>>();
        allVertices = new HashMap<Integer,VertexB<T>>();
        this.isDirected = isDirected;
    }





}
