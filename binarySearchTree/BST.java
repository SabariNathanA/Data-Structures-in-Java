package binarySearchTree;

public class BST {

	public static Node insert(Node head, int data){
		Node thead=head,prev=null;
		Node temp=new Node();
		temp.data=data;
		temp.right=temp.left=temp.parent=null;
		if(head==null){
			thead=temp;
			return thead;
		}
		else{
			while(thead!=null){
				prev=thead;
				if(temp.data>thead.data)
					thead=thead.right;
				else
					thead=thead.left;
			}
			temp.parent=prev;
			if(temp.data>prev.data)
				prev.right=temp;
			else
				prev.left=temp;
			return head;
		}
	}
	
	public static Node swapNode(Node head, Node u, Node v){
		if(u.parent==null) 
			head=v;
		if(u==u.parent.left) 
			u.parent.left=v;
		else 
			u.parent.right=v;
		if(v!=null)
			v.parent=u.parent;
		return head;
	}
	
	public static Node minimum(Node n){
		while(n.left!=null)
			n=n.left;
		return n;
	}
	
	public static Node maximum(Node n){
		while(n.right!=null)
			n=n.right;
		return n;
	}
	
	public static Node predecessor(Node n){
		if(n.left!=null){
			return maximum(n.left);
		}
		else{
			Node p=n.parent;
			while(p!=null && n==p.left){
				n=p;
				p=n.parent;
			}
			return p;
		}
	}
	
	public static Node sucesssor(Node n){
		if(n.right!=null){
			return minimum(n.right);
		}
		else{
			Node p=n.parent;
			while(p!=null && n==p.right){
				n=p;
				p=n.parent;
			}
			return p;
		}
	}
	
	
	public static Node delete(Node head, int data){
		Node n=new Node();
		return n;
	}
	
	public static boolean search(Node head, int data){
	return true;	
	}
	
	public static void main(String[] args) {
		Node head=null;
		head=insert(head,5);
	}

}
