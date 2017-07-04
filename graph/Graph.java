package graph;
import java.util.*;
import java.util.LinkedList;

class Graph
{
    private int V;   
    private LinkedList<Integer> adj[]; 
    private static int numberOfCCs=0;
    private int[] color;
    
    Graph(int v)
    {
        V = v;
        color=new int[v];
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
   
    void addEdge(int v, int w)  { 
    	adj[v].add(w); 
    }
   
    void DFSUtil(int v,boolean visited[],int presentColor)
    {
        
        visited[v] = true;
        this.color[v] = presentColor;
        System.out.print(v + " "+this.color[v]+" - ");
 
        int n;
        Iterator<Integer> i =adj[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited,presentColor);
        }
    }
 
    Graph getTranspose()
    {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++)
        {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i =adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }
 
    void fillOrder(int v, boolean visited[], Stack stack)
    {
        visited[v] = true;
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
                fillOrder(n, visited, stack);
        }
        stack.push(new Integer(v));
//        System.out.println(v);
        }
 
    void printSCCs()
    {
        Stack stack = new Stack();
        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;
//        System.out.println("The order of stacking");
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);
        Graph gr = getTranspose();
        for (int i = 0; i < V; i++)
            visited[i] = false;
        //int col=0;
        while (stack.empty() == false)
        {
            int v = (int)stack.pop();
 
            if (visited[v] == false)
            {
                gr.DFSUtil(v, visited,numberOfCCs);
                System.out.println();
                numberOfCCs++;
                //col++;//end of 1component color
            }
        }
        System.out.println("the maximum number of edges that may be needed is "+(2*(numberOfCCs-1)));
        gr.neededEdges();
    }
    
    void neededEdges(){
    	boolean[][] edgeMatrix = new boolean[numberOfCCs][numberOfCCs];
    	int i,minNeeded=(2*(numberOfCCs -1)),heremin=0;
    	for(i=0;i<numberOfCCs;i++)
    		edgeMatrix[i][i]=true;
    	int icol,jcol;
    	for(i=0;i<V;i++){
    		icol = this.color[i];
    		Iterator<Integer> head = adj[i].iterator();
    		while(head.hasNext()){
    			int j = head.next();
    			jcol = this.color[j];
    			edgeMatrix[icol][jcol]=true;
    		}
    	}
    	int k;
    	for(i=0;i<numberOfCCs;i++){
    		heremin=0;
    		for(k=0;k<numberOfCCs;k++){
    			if(edgeMatrix[i][k]==false || edgeMatrix[k][i]==false ){
    				if(edgeMatrix[i][k]==false && edgeMatrix[k][i]==false){
    					heremin+=2;
    				}
    				else
    					heremin++;
    			}
    		
    		}
    		if (heremin<minNeeded)
    			minNeeded=heremin;
    	}
    	System.out.println("Min needed Edges "+minNeeded);
    }
    /*if(edgeMatrix[i][j]==false ||edgeMatrix[j][i]==false ){
		if(edgeMatrix[i][j]==false && edgeMatrix[j][i]==false){
			heremin+=2;
		}
		else
			heremin++;
	}*/
    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        int t=scan.nextInt();
        while(t>0){
        	int n = scan.nextInt();
	    	Graph g = new Graph(n);
	    	int m=scan.nextInt(),a,b,directed;
	    	while(m>0){
	    		a=scan.nextInt();
	    		b=scan.nextInt();
	    		directed=scan.nextInt();
	    		g.addEdge(a, b);
	    		if(directed == 0){
	    			g.addEdge(b, a);
	    		}
	    		m--;
	    	}
	        System.out.println("Following are strongly connected components "+
	                           "in given graph ");
	        g.printSCCs();
	//        System.out.println(numberOfCCs);
	        t--;
        }
    scan.close();
    }
}