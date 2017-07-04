package rbTrees;

public class RBNode {
	public int data;
	public RBNode parent = null;
	public RBNode rightChild = null;
	public RBNode leftChild = null;
	public enum typeOfNode {red, black};
	public typeOfNode color;

    /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
    void printPostorder(RBNode node)
    {
        if (node == null)
            return;
 
        // first recur on left subtree
        printPostorder(node.leftChild);
 
        // then recur on right subtree
        printPostorder(node.rightChild);
 
        // now deal with the node
        System.out.println(node.data + " " + node.color);
    }
 
    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(RBNode node)
    {
        if (node == null)
            return;
 
        /* first recur on left child */
        printInorder(node.leftChild);
 
        /* then print the data of node */
        System.out.println(node.data + " " + node.color);
 
        /* now recur on right child */
        printInorder(node.rightChild);
    }
 
    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(RBNode node)
    {
        if (node == null)
            return;
 
        /* first print data of node */
        System.out.println(node.data + " " + node.color);
 
        /* then recur on left sutree */
        printPreorder(node.leftChild);
 
        /* now recur on right subtree */
        printPreorder(node.rightChild);
    }
 
}
