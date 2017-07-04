package heap;

public class MaxHeap {

	static void swap(int[]a,int i,int j){
		int temp=a[i];
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
	
	public static void bottomUpHeapify(int[] a,int index,int endIndex){
		int i=index;
		while( (i>0) && (a[parent(i)]<a[i]) ){
			swap(a,i,parent(i));
			i=parent(i);
		}
	}
	
	public static void topDownHeapify(int[] a, int index, int endIndex){
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
	/*buildHeap starts from level l-1 so, we need Top down at every node*/
	public static void buildMaxHeap(int[] a){
		int n=a.length;
		int i=(n/2);
		for(;i>=0;i--){
			topDownHeapify(a,i,n);
		}
	}
	
	
	static void updateKey(int[] a,int indexToBeUpdated, int newValue){
		int n=a.length;
		if(a[indexToBeUpdated]==newValue)
			return;
		else if(a[indexToBeUpdated]<newValue){
			a[indexToBeUpdated]=newValue;
			bottomUpHeapify(a,indexToBeUpdated,n);
		}
		else{
			a[indexToBeUpdated]=newValue;
			topDownHeapify(a,indexToBeUpdated,n);
		}
	}
		
	public static void main(String[] args) {
		int[] a={100,6,5,8,2,1,12,3,9,7,4,110,102,104,105,109,201,200,0};
//		int[] a={1,2,3};
		buildMaxHeap(a);
//		topDownHeapify(a, 0, a.length);
		int i=0;
		for(;i<a.length;i++)
			System.out.println(a[i] + "    " + i);
		System.out.println();
		updateKey(a, 0, 10);
		i=0;
		for(;i<a.length;i++)
			System.out.println(a[i] + "    " + i);
	}

}
