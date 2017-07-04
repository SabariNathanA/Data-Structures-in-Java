package graph;

import java.util.Scanner;


public class AdjMat {

	int[][] adjMat;
	
	int getInputs(){
		System.out.println("Enter the number of vertices");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		this.adjMat=new int[n][n];
/* Start of edge  inputs*/
		int i,v,w,u;
		System.out.println("Enter "+n+" if no more edges");
		for(i=0;i<n;i++){
			u=i;
			System.out.println("Enter the edges for "+i+"th vertex as vertex<space>weight");
			v=scan.nextInt();
			while(v!=n){
				w = scan.nextInt();//weight
				this.adjMat[u][v] = w;
				System.out.println("Next edge as v <space> w");
				v=scan.nextInt();
			}
		}
		scan.close();
		return n;
	}
	
	void printAdjMat(int n){
		int i,j;
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				System.out.print(this.adjMat[i][j]+" | ");
			}
		System.out.println();
		}
	}
	
	public static void main(String[] args) {
		AdjMat am =new AdjMat();
		int n = (am.getInputs());
		am.printAdjMat(n);
	}

}
