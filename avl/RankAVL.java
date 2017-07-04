package avl;

import java.util.Scanner;

public class RankAVL {
	
	 AVLTree avlt = new AVLTree();
	
	public void InitRank(AVLNode Node){
		if(Node ==null){
			return;
		}
		else{
			Node.rank = 1 + avlt.countNodes(Node.right);
			this.InitRank(Node.left);
			this.InitRank(Node.right);
		}
	}
	
	public int findRank(int val){
		AVLNode temp = this.avlt.root;
		int rank = 1;
		while(temp!=null){
			if(temp.data > val){
				if(temp.right != null)
					rank = rank + temp.right.rank + 1;
				temp = temp.left;
				
			}
			else if(temp.data < val){
				temp = temp.right;
			}
			else if (temp.data == val){
				if(temp.right != null)
					rank = rank + temp.right.rank;
				break;
			}
		}
		return rank;
	}
	
	
	
	public static void main(String args[]){
	Scanner scan = new Scanner(System.in);
    /* Creating object of AVLTree */
    int t;
    char ch;
    RankAVL ravl = new RankAVL();
    AVLNode temp;
    /*  Perform tree operations  */
    do    
    {
        System.out.println("\nAVLTree Operations\n");
        System.out.println("1. insert ");
        System.out.println("2. search");
        System.out.println("3. count nodes");
        System.out.println("4. Find Rank of a given node");
        System.out.println("5. clear tree");

        int choice = scan.nextInt();            
        switch (choice)
        {
        case 1 : 
            System.out.println("Enter integer element to insert");
            t = scan.nextInt() ;
            ravl.avlt.insert( t );   //continue here call init on root
            temp = ravl.avlt.root;
            ravl.InitRank(temp);
            break;                          
        case 2 : 
            System.out.println("Enter integer element to search");
            System.out.println("Search result : "+ ravl.avlt.search( scan.nextInt() ));
            temp = ravl.avlt.root;
            ravl.InitRank(temp);
            break;                                          
        case 3 : 
            System.out.println("Nodes = "+ ravl.avlt.countNodes());
            break;     
        case 4 : 
        	System.out.println("Enter integer element to find its rank");
        	t = scan.nextInt();
        	t = ravl.findRank(t);
        	System.out.println(t);
        	//continue here
//        	problemm is we store number of children greater than that node not rank.
            break;     
        case 5 : 
            System.out.println("\nTree Cleared");
            ravl.avlt.makeEmpty();
            break;         
        default : 
            System.out.println("Wrong Entry \n ");
            break;   
        }
        /*  Display tree  */ 
        System.out.print("\nPost order : ");
        ravl.avlt.postorder();
        System.out.print("\nPre order : ");
        ravl.avlt.preorder();
        System.out.print("\nIn order : ");
        ravl.avlt.inorder();

        System.out.println("\nDo you want to continue (Type y or n) \n");
        ch = scan.next().charAt(0);                        
    } while (ch == 'Y'|| ch == 'y');   
    scan.close();
	}
}