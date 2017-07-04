package heap;

import java.io.*;

public class SmallestKElementsUsingHeap {

	static void print (float[] a){
		int i=0;
		for(;i<a.length;i++)
			System.out.printf("%.0f\n",a[i]);
	}
	
	static void swap(float[] a,int i,int j){
		float temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
	static int parent(int x){
		return (x-1)/2;
	}
	
	static int leftChild(int x){
		return ((2 * x) + 1);
	}
	
	static int rightChild(int x){
		return ((2 * x) + 2);
	}
	
	
	public static void buildMaxHeap(float[] a){
		int n=a.length;
		int i=(n/2);
		for(;i>=0;i--){
			topDownHeapify(a,i,n);
		}
	}
	
	public static void compare(float[] a,float temp){
		if(temp<a[0]){
			a[0]=temp;
			topDownHeapify(a, 0, a.length);
		}
	}
	
	public static void topDownHeapify(float[] a, int index, int endIndex){
		int leftChild,rightChild,maxChild;
		while(leftChild(index)<endIndex){
			leftChild=leftChild(index);
			rightChild=rightChild(index);
			if( (rightChild<endIndex) && (a[leftChild]<a[rightChild]) ){
				maxChild=rightChild;
			}
			else
				maxChild=leftChild;
			if(a[index]<a[maxChild]){
				swap(a,index,maxChild);
				index=maxChild;
			}
			else 
				index=endIndex;
		}
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		int i,k=10000;
		float[] a=new float[k];
		float nextReadValue;
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try{
			fis = new FileInputStream("output.txt");
			dis = new DataInputStream(fis);
			for(i=0;i<k;i++){
				a[i]=dis.readFloat();
			}
			buildMaxHeap(a);
			while(dis.available()>0){
				nextReadValue=dis.readFloat();
				compare(a,nextReadValue);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			if(fis != null){
				fis.close();
			}
			if(dis != null){
				dis.close();
			}
		}
		long endTime = System.currentTimeMillis();
		print(a);
		
		System.out.println("Took "+(endTime - startTime) + " ms");
	}
}

/*
Statistics
Time taken = 30.5s
Input size = 10,000,000.
*/