package rbTrees;
import rbTrees.RBNode.typeOfNode;

public class RBT {
	RBNode root = new RBNode();
	
	RBNode searchPosition(int keyToSearch, RBNode root){
		RBNode foundParent = null, temp = root;
		while(temp!=null){
			if(temp.data == keyToSearch){
				foundParent = null;	// Just to check in the insert function if the key is already present
				temp = null;	//to stop the loop if the key is already present
			}
			else if(temp.data > keyToSearch){
				foundParent = temp;
				temp = temp.leftChild;
			}
			else{
				foundParent = temp;
				temp = temp.rightChild;
			}
		}
		return foundParent;
	}
	
	RBNode searchValue(int keyToSearch, RBNode root){
		RBNode foundNode = null, temp = root;
		while(temp!=null){
			if(temp.data == keyToSearch){
				foundNode = temp;	// return Object ID
				temp = null;	//to stop the loop if the key is present
			}
			else if(temp.data > keyToSearch){
				temp = temp.leftChild;
			}
			else{
				temp = temp.rightChild;
			}
		}
		return foundNode;
	}
	
	void insert(int keyToSearch, RBNode root){
		RBNode newNode = new RBNode();
		newNode.data = keyToSearch;
		newNode.color = typeOfNode.red;	//insert always a red node
		
		/*finding position to insert. The parent of the new node position is obtained*/
		RBNode foundPos = this.searchPosition(keyToSearch, root); 
		
		if (foundPos == null){
			System.out.println("Key already present in the Tree");
			return;
		}
		
		newNode.parent = foundPos;
		if (foundPos.data > keyToSearch) {
			foundPos.leftChild = newNode;
		}
		else 
			foundPos.rightChild = newNode;
		/*If we are lucky enough to add red node to black parent, don't go any further*/
		if(foundPos.color == typeOfNode.black){	
			//fondPos is the parent
			return;
		}
		/* We aren't lucky enough! */
		this.doubleRed(newNode);	//remember we pass the son's handle
		
	}
	
	
	private void delete(int toBeDeleted){
		RBNode nodeToBeDeleted = this.searchValue(toBeDeleted, this.root);
		if(nodeToBeDeleted == null){
			System.out.println("Node not present! Check your input, you dumb human!");
			return;
		}/* no need else condition here due to return in the above IF block */
		
		if((nodeToBeDeleted.leftChild != null) &&(nodeToBeDeleted.rightChild != null)){
//			To make the the inorder predecessor the node to be deleted.
			RBNode inOrderpred = inOrderPredecessor(nodeToBeDeleted);
			nodeToBeDeleted.data = inOrderpred.data;
//			this.doubleBlack(inp);
			/*Delete the inorder predecessor it wil have either LC only*/
			nodeToBeDeleted = inOrderpred;
		}
		
		if ((nodeToBeDeleted.leftChild == null) &&(nodeToBeDeleted.rightChild == null)){
//			No children
			RBNode parent = nodeToBeDeleted.parent;
			if(parent != null){
				if(parent.leftChild == nodeToBeDeleted) 
					parent.leftChild = null;
				else 
					parent.rightChild = null;
				
				if(nodeToBeDeleted.color == typeOfNode.black){
					if(parent.color == typeOfNode.black){
						this.doubleBlack(parent);
					}
				}
			}
			else 
//				root node deletion
				this.root = null;
		}
		else if((nodeToBeDeleted.leftChild == null) &&(nodeToBeDeleted.rightChild != null)){
			RBNode parent = nodeToBeDeleted.parent;
/*We need to handle single child recoloring problem We shall change the IDs
 * then call recolour function specific to single child problems*/
			if(parent == null){
				this.root = nodeToBeDeleted.rightChild;
				this.root.parent = null;
				this.root.color=typeOfNode.black;
			}
			else{
				RBNode child = nodeToBeDeleted.rightChild;
				if(parent.leftChild == nodeToBeDeleted) {
					parent.leftChild = child;
//					child.color = nodeToBeDeleted.color;
					child.parent = parent;
				}
				else {
					parent.rightChild = child;
					child.parent = parent;
				}
				/*Colouring*/
				if(nodeToBeDeleted.color == typeOfNode.black){
					if(child.color == typeOfNode.red){
						child.color = typeOfNode.black;
					}
					else{
						this.doubleBlack(parent);
					}
				}
			}
		}
		else if((nodeToBeDeleted.leftChild != null) &&(nodeToBeDeleted.rightChild == null)){
			RBNode parent = nodeToBeDeleted.parent;
			if(parent != null){
				RBNode child = nodeToBeDeleted.leftChild;
				if(parent.leftChild == nodeToBeDeleted) {
					parent.leftChild = child;
					child.parent = parent;
				}
				else {
					parent.rightChild = child;
					child.parent = parent;
				}
				/*Colouring*/
				if(nodeToBeDeleted.color == typeOfNode.black){
					if(child.color == typeOfNode.red){
						child.color = typeOfNode.black;
					}
					else{
						this.doubleBlack(parent);
					}
				}
			}
			else{
				this.root = nodeToBeDeleted.leftChild;
				this.root.color = typeOfNode.black;
			}
		}/*End of single parent and single child parent cases*/
	}
	
	
	
	private void doubleBlack(RBNode inp) {
		RBNode current=inp, parent = null, sibling = null,naughtyChild = null;
		parent = current.parent;
		byte siblingParentRelation; 
		if(parent == null){
			this.root = current;
			return;
		}
		else{
			sibling = (parent.rightChild == current)? parent.leftChild : parent.rightChild;
			siblingParentRelation = (byte) ((sibling == parent.leftChild)? 1: 0);
			if(parent.color != typeOfNode.black){
				return;
			}
		}
		
		/* Case 1 sibling red */
		if(sibling.color == typeOfNode.red){
			sibling.parent = parent.parent;
			if(parent.parent.leftChild == parent)
				parent.parent.leftChild = sibling;
			else 
				parent.parent.rightChild = sibling;
			
			if(siblingParentRelation == (byte) 0){
				parent.rightChild = sibling.leftChild;
				sibling.leftChild = parent;
			}
			else{
				parent.leftChild = sibling.rightChild;
				sibling.rightChild = parent;
			}
			parent.parent = sibling;
//			recolouring no need to colour the current
			parent.color = typeOfNode.red;
			sibling.color = typeOfNode.black;
		}
		/* Case 1 end */
		/*Case 2 Sibling black and niece are black*/
		else if((sibling.leftChild.color == typeOfNode.black) && (sibling.rightChild.color == typeOfNode.black)){
			parent.color = typeOfNode.black;
			sibling.color = typeOfNode.red;
			current.color = typeOfNode.black;
		}
//		end of case 2
		else{
			/*
			 * Case 3 niece one is red
			 * Check if the opposite child is red and setting up the naughty child and siblings */
			if((siblingParentRelation == 0) && (sibling.leftChild.color == typeOfNode.red) && (sibling.rightChild.color != typeOfNode.red)){
				naughtyChild = sibling.leftChild;
				parent.rightChild = naughtyChild;
				naughtyChild.parent = parent;
				sibling.parent = naughtyChild;
				sibling.leftChild = naughtyChild.rightChild;
				naughtyChild.rightChild.parent = sibling;
				naughtyChild.rightChild = sibling;
				sibling = naughtyChild;
				naughtyChild = sibling.rightChild;
				sibling.color = typeOfNode.black;
				naughtyChild.color = typeOfNode.red;
			}
			else if((siblingParentRelation == 1) && (sibling.rightChild.color == typeOfNode.red) && (sibling.leftChild.color != typeOfNode.red)){
				naughtyChild = sibling.rightChild;
				parent.leftChild = naughtyChild;
				naughtyChild.parent = parent;
				sibling.rightChild = naughtyChild.leftChild;
				naughtyChild.leftChild.parent = sibling;
				sibling.parent = naughtyChild;
				naughtyChild.leftChild = sibling;
				sibling = naughtyChild;
				naughtyChild = sibling.leftChild;
				sibling.color = typeOfNode.black;
				naughtyChild.color = typeOfNode.red;
			}
			else if((siblingParentRelation == 0)&&(sibling.rightChild.color == typeOfNode.red)){
				naughtyChild = sibling.rightChild;
			}
			else if ((siblingParentRelation == 1) && (sibling.leftChild.color == typeOfNode.red)){
				naughtyChild = sibling.leftChild;
			}
			/* End of setting up the naughtyChild and siblings
			 * Start of rotations to make ZigZig possible */
			siblingParentRelation = (byte) ((sibling == parent.leftChild)? 1: 0);
			if(siblingParentRelation == (byte) 0){
				sibling.parent = parent.parent;
				if(parent.parent.leftChild == parent)
					parent.parent.leftChild = sibling;
				else
					parent.parent.rightChild = sibling;
				parent.parent = sibling;
				parent.rightChild = sibling.leftChild;
				sibling.leftChild = parent;
				parent.rightChild.parent = parent;
			}
			else {
				sibling.parent = parent.parent;
				if(parent.parent.leftChild == parent)
					parent.parent.leftChild = sibling;
				else
					parent.parent.rightChild = sibling;
				parent.parent = sibling;
				parent.leftChild = sibling.rightChild;
				sibling.rightChild = parent;
				parent.leftChild.parent = parent;
			}
//			Colouring them is pending
			sibling.color = parent.color;
			parent.color = typeOfNode.black;
			current.color = typeOfNode.black;
			naughtyChild.color = typeOfNode.black;
		}
	}

	RBNode inOrderPredecessor(RBNode problematicGuy){
		RBNode temp = problematicGuy.leftChild;
		while(temp.rightChild !=null){
			temp = temp.rightChild;
		}
		return temp;
	}
	
	private void doubleRed(RBNode problematicGuy) {
		RBNode son = problematicGuy;
		RBNode parent = son.parent;
		RBNode grandpa = null;
		RBNode uncleJi = null;
		
		if(parent!=null){
			/* terminating condition for recursive calls */
			if(parent.color != typeOfNode.red){
				return;
			}
			else 
				grandpa = parent.parent;
		}
		else {
			//root reached
			son.color = typeOfNode.black;
			return;
		}
		/* Our uncleJi is the RC (pun not intended) if parent is LC else LC */
		if (grandpa != null) {
			uncleJi = (grandpa.leftChild == parent)? grandpa.rightChild : grandpa.leftChild;
		}
		else{ 
			parent.color = typeOfNode.black;
		}
		
		/* Just another corner case check */
		if(uncleJi == null){
			/*son--LC--Parent = 1 else 0*/
			byte sonParentRelation = (byte) ((son == parent.leftChild)? 1: 0);
			byte parentGrandpaRelation = (byte) ((parent == grandpa.leftChild)? 1: 0);
			if(sonParentRelation == parentGrandpaRelation)
				this.zigZig(son);
			else
				this.zigZag(son);
			return;
		}
		
		else {
			/* 
			 * the uncleJi is not null
			 * lets see if he is red
			 * Case 1 starts 
			 * */
			if( uncleJi.color == typeOfNode.red ){
				grandpa.color = typeOfNode.red;
				parent.color = typeOfNode.black;
				uncleJi.color = typeOfNode.black;
				this.doubleRed(grandpa);
				return;
			} 
			/* Case 1 ends */
		
			else { 
				/* 
				 * the uncle is black
				 * the following lines for checking Zig-Zag or Zig-Zig rotations. LC=1;RC=0 
				 */
				byte sonParentRelation = (byte) ((son == parent.leftChild)? 1: 0);
				byte parentGrandpaRelation = (byte) ((parent == grandpa.leftChild)? 1: 0);
				
				/* Case 2 starts */
				if(sonParentRelation != parentGrandpaRelation){
					this.zigZag(son);
				}
				/* Case 2 ends */
				
				/* Case 3 starts */
				else 
					this.zigZig(son);
				/* Case 3 ends */
				return;
			}
		}
	}

	private void zigZig(RBNode son) {
		RBNode parent = son.parent;
		RBNode grandpa = parent.parent;
		RBNode otherSon = (parent.leftChild ==  son)? parent.rightChild : parent.leftChild;
		RBNode GGPa = grandpa.parent;
		
		parent.parent = GGPa;
		grandpa.parent = parent;
		if(GGPa != null){
			if(GGPa.leftChild == grandpa) 	
				GGPa.leftChild = parent;
			else 
				GGPa.rightChild = parent;
		}
		byte sonParentRelation = (byte) ((son == parent.leftChild)? 1: 0);
		
		if (sonParentRelation == (byte) 1){
			/*
			 * Left - Left
			 */
			parent.rightChild = grandpa;
			grandpa.leftChild = otherSon;
			if(otherSon != null) otherSon.parent = grandpa; 
		}
		else{
			parent.leftChild = grandpa;
			grandpa.rightChild = otherSon;
			if(otherSon != null) otherSon.parent = grandpa;
		}
		/*		After ZigZig,
		 * 		Parent = grandpa
		*		son=parent
		*/
		if(parent.parent == null)
			this.root = parent;	//update the root pointer
		parent.color = typeOfNode.black;
		grandpa.color = typeOfNode.red;
		son.color = typeOfNode.red;
	}

	private void zigZag(RBNode son) {
		RBNode parent = son.parent;
		RBNode grandpa = parent.parent;
		RBNode sonLC = son.leftChild;
		RBNode sonRC = son.rightChild;
		
		byte sonParentRelation = (byte) ((son == parent.leftChild)? 1: 0);
		if(sonParentRelation == (byte) 0){
			/*
			 * we have Son -RC- Parent -LC- Granny
			 * first parent -RC- sonLC
			 * grandpa -LC- son
			 * sonLC -p- parent
			 * son -p- grandpa 
			 */
			parent.rightChild = sonLC;
			grandpa.leftChild = son;
			son.leftChild = parent;
			if(sonLC != null) sonLC.parent = parent;
			son.parent = grandpa;
			parent.parent = son;
			/*
			 * we have parent -LC- son -LC- grandpa
			 * son-p- grandpa's p
			 * grandpa -LC- sonRC
			 * sonRC -p- grandpa
			 * son -RC- grandpa
			 * grandpa -P- son
			 */
			RBNode GGPa = grandpa.parent;
			son.parent = GGPa;
			if(GGPa.leftChild == grandpa) 	GGPa.leftChild = son;
			else 	GGPa.rightChild = son;
			son.rightChild = grandpa;
			grandpa.parent = son;
			grandpa.leftChild = sonRC;
			if (sonRC != null) sonRC.parent = grandpa;
			
		}
		else {
			/*
			 * we have son -LC- Parent -RC- Granny
			 * first parent -LC- sonRC
			 * grandpa -RC- son
			 * sonRC -p- parent
			 * son -p- grandpa
			 * son -RC-parent 
			 */
			parent.leftChild = sonRC;
			if(sonRC != null) sonRC.parent = parent;
			grandpa.rightChild = son;
			son.parent = grandpa;
			son.rightChild = parent;
			parent.parent = son;
			/*
			 * we have parent -RC- son -RC- grandpa
			 * son -p- grandpa's p
			 * grandpa -RC- sonLC
			 * sonLC -p- grandpa
			 * son -LC- grandpa
			 * grandpa -P- son
			 */
			RBNode GGPa = grandpa.parent;
			son.parent = GGPa;
			if(GGPa.leftChild == grandpa) 	GGPa.leftChild = son;
			else 	GGPa.rightChild = son;
			son.leftChild = grandpa;
			grandpa.parent = son;
			grandpa.rightChild = sonLC;
			if (sonLC != null) sonLC.parent = grandpa;	
		}
		/*After ZigZag, son is the grandpa*/
		if(son.parent == null)
			this.root = son;
		son.color = typeOfNode.black;
		parent.color = typeOfNode.red;
		grandpa.color = typeOfNode.red;
	}

	public static void main(String[] args) {
		RBT obj = new RBT();
		obj.root.color = typeOfNode.black;
		obj.root.data = 5;	
		
		obj.insert(4, obj.root);
		obj.insert(3, obj.root);
		obj.insert(2, obj.root);
		obj.insert(1, obj.root);
		obj.insert(0, obj.root);
		obj.insert(-1, obj.root);
		obj.insert(7, obj.root);
		obj.root.printPostorder(obj.root);
		System.out.println("Done Baby! :)");
		obj.insert(6, obj.root);
		
		obj.root.printPostorder(obj.root);
		System.out.println("Done Baby! :)");
		obj.delete(4);
		obj.root.printPostorder(obj.root);
		System.out.println("Done Baby! :)");
		obj.delete(7);
		obj.root.printPostorder(obj.root);
	}

}
