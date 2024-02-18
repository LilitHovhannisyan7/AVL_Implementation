class Node
{
    int value;
    Node left;
    Node right;
    public Node(int value)
    {
        this.value = value;
    }
}
class AVL
{
    Node root;
    public AVL(int value)
    {
        this.root = new Node(value);
    }
    public Node leftRotate(Node z)
    {
        Node y = z.right;
        z.right = y.left;
        y.left = z;
        return y;
    }
    public Node rightRotate(Node z)
    {
        Node y = z.left;
        z.left = y.right;
        y.right = z;
        return y;
    }
    private int getHeight(Node node)
    {
        if(node == null)
        {
            return -1;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
    public int getHeight()
    {
        return this.getHeight(this.root);
    }

    public Node getMax()
    {
        return this.getMax(this.root);
    }
    private Node getMax(Node node)
    {
        if(node.right == null)
        {
            return node;
        }
        return getMax(node.right);
    }
    public int getBF(Node node)
    {
        return getHeight(node.left) - getHeight(node.right);
    }
    public void insert(int val)
    {
        this.insert(this.root, val);
    }
    private Node insert(Node node, int val)
    {
        if(node == null)
        {
            return new Node(val);
        }
        if(node.value < val)
        {
            node.right = insert(node.right, val);
        }
        else
        {
            node.left = insert(node.left, val);
        }

        int bf = this.getBF(node);
//        RL case
        if(bf < -1 && val < node.right.value)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
//        RR case
        if(bf < -1 && val > node.right.value)
        {
            return leftRotate(node);
        }
//        LR case
        if(bf > 1 && val > node.left.value)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
//      LL case
        if(bf > 1 && val < node.left.value)
        {
            return rightRotate(node);
        }
        return node;
    }

    public void delete(int val)
    {
        this.delete(val, this.root);
    }
    private Node delete(int val, Node node)
    {
        if(node == null)
        {
            return null;
        }
        if(node.value < val)
        {
            node.right = delete(val, node.right);
        }
        else if(node.value > val)
        {
            node.left = delete(val, node.left);
        }
        else
        {
            if(node.left == null)
            {
                return node.right;
            }
            else if(node.right == null)
            {
                return node.left;
            }
            else
            {
                Node temp = getMax(node.left);
                node.value = temp.value;
                node.left = delete(temp.value, node.left);
            }
        }

        int bf = getBF(node);
//        LL case
        if(bf > 1 && getBF(node.left) >= 0)
        {
            return rightRotate(node);
        }
//        LR case
        if(bf > 1 && getBF(node.left) < 0)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
//        RL case
        if(bf < -1 && getBF(node.right) > 0)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
//        RR case
        if(bf < -1 && getBF(node.right) <= 0)
        {
            return leftRotate(node);
        }
        return node;
    }



}



public class Main
{
    public static void main(String[] args)
    {

    }

}