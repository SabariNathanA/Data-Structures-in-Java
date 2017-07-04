package graph;
/* Our adj list starts with index 0, be careful to send corresponding arguments
 * while object creation send number Of vertices and directed Graph or not boolean
*/
public class AdjList {
	
	
	
	public Edge[] list;
	public boolean directed;
//	Constructor
	public AdjList(int numberOfVertices, boolean dir){
		list = new Edge[numberOfVertices];
		if(dir) directed = true;
		else directed = false;
	}
	
	public void addEdge(int u, int v, int w){
		Edge toAdd = new Edge(v,w);
		Edge currentHeadOfList = this.list[u];
		this.list[u] = toAdd;
		toAdd.next = currentHeadOfList;
		if(this.directed != true){
//	Undirected graph add the edge in both nodes
			currentHeadOfList = this.list[v];
			toAdd = new Edge(u,w);
			this.list[v] = toAdd;
			toAdd.next = currentHeadOfList;
		}
	}
	
	public void deleteEdge(int u, int v){
		Edge currentHeadOfList = this.list[u], prev = null;
		while(currentHeadOfList.vertex != v){
			prev = currentHeadOfList;
			currentHeadOfList = currentHeadOfList.next;
		}
		Edge nextNode = currentHeadOfList.next;
		if(prev == null){
//			if there is only one neighbour who is also being deleted
			this.list[u] = null;
		}
		else{
			prev.next = nextNode;
		}
		if(directed != true){
			currentHeadOfList = this.list[v]; prev = null;
			while(currentHeadOfList.vertex != u){
				prev = currentHeadOfList;
				currentHeadOfList = currentHeadOfList.next;
			}
			nextNode = currentHeadOfList.next;
			if(prev == null){
				this.list[v] = null;
			}
			else{
				prev.next = nextNode;
			}
		}
	}
	
	public void printAdjList(){
		int i,length;
		Edge head;
		length = this.list.length;
		for(i=0;i<length;i++){
			head = list[i];
			System.out.print(i);
			while(head != null){
				System.out.print(" - ( "+head.vertex+","+head.weight+" )");
				head = head.next;
			}
			System.out.println();
		}
	}
	
/*	public static void main(String[] args) {
		AdjList a = new AdjList(5, true);
		a.addEdge(0, 1, 5);
		a.addEdge(0, 2, 15);
		a.addEdge(3, 4, 9);
		a.printAdjList();
		a.deleteEdge(0,1);
		a.printAdjList();
		a.deleteEdge(3, 4);
		a.printAdjList();
	}
*/
}
