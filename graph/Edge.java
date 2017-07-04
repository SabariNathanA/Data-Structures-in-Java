package graph;

public class Edge {
	public int vertex;
	public int weight;
	public Edge next = null; 
	public Edge(int v,int w){
		vertex = v;
		weight = w;
	}
}