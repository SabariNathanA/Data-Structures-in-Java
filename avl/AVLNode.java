package avl;
public class AVLNode
{    
    AVLNode left, right;
    int data;
    int height;
    int rank;

    /* Constructor */
    public AVLNode()
    {
        left = null;
        right = null;
        data = 0;
        height = 0;
        rank = 0;
    }
    /* Constructor */
    public AVLNode(int n)
    {
        left = null;
        right = null;
        data = n;
        height = 0;
        rank = 0;
    }     
}