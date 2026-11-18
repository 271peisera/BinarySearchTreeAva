import java.util.*;

/**
 * This BinarySearchTree object defines a reference based binary search tree
 * 
 * @author  
 * @version 
 */

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>
{
    protected BinaryNode<T> root;      // reference to the root of this BST

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
        root = null;
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return root == null;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        return size(root);
    }

    private int size(BinaryNode<T> tree){
        if (tree == null)
            return 0;
        List<T> b = new ArrayList<T>();
        return inorderTraverse(tree, b).size();
    }

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        if(root == null)
        {
            root = new BinaryNode<T>(element);
        }
        else
        {
            BinaryNode<T> current = root;
            boolean a = true;
            while(a)
            {
                if(element.compareTo(current.getInfo()) > 0)
                {
                    if(current.getRight() == null)
                    {
                        current.setRight(new BinaryNode<T>(element));
                        a = false;
                    }
                    else
                    {
                        current = current.getRight();
                    }
                }
                else if(element.compareTo(current.getInfo()) < 0)
                {
                    if(current.getLeft() == null)
                    {
                        current.setLeft(new BinaryNode<T>(element));
                        a = false;
                    }
                    else
                    {
                        current = current.getLeft();
                    } 
                }

            }
        }
    }

    // Returns true if this BST contains an element e such that 
    // e.compareTo(element) == 0; otherwise, returns false.
    public boolean contains (T element)
    {
        BinaryNode<T> current = root;
        while (current != null)
        {
            int comparison = element.compareTo(current.getInfo());
            if (comparison == 0)
                return true;
            else if (comparison > 0)
                current = current.getRight();
            else
                current = current.getLeft();
        }
        return false;
    }

    // Returns a graphical representation of the tree
    public String toString()
    {
        return toString(root, 0);
    }

    private String toString(BinaryNode<T> tree, int level)
    {
        String str = "";
        if (tree != null)
        {
            str += toString(tree.getRight(), level + 1);
            for (int i = 1; i <= level; ++i)
                str = str + "| ";
            str += tree.getInfo().toString() + "\n";
            str += toString(tree.getLeft(), level + 1);
        }
        return str;
    }

    // Returns a list of elements from a preorder traversal
    public List<T> preorderTraverse()
    {
        List<T> a = new ArrayList<>();
        return preorderTraverse(root, a);
    }

    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse()
    {
        List<T> a = new ArrayList<>();
        return inorderTraverse(root, a);
    }

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        List<T> a = new ArrayList<>();
        return postorderTraverse(root, a);
    }

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
        root = remove(root, element);
    }

    private BinaryNode<T> remove(BinaryNode<T> tree, T element)
    {
        if (tree == null)
            return null;

        int comparison = element.compareTo(tree.getInfo());
        if (comparison < 0)
        {
            tree.setLeft(remove(tree.getLeft(), element));
        }
        else if (comparison > 0)
        {
            tree.setRight(remove(tree.getRight(), element));
        }
        else
        {
            if (tree.getLeft() == null)
                return tree.getRight();
            if (tree.getRight() == null)
                return tree.getLeft();

            BinaryNode<T> successor = tree.getRight();
            while (successor.getLeft() != null)
                successor = successor.getLeft();

            tree.setInfo(successor.getInfo());
            tree.setRight(remove(tree.getRight(), successor.getInfo()));
        }

        return tree;
    }

    // Restructures this BST to be optimally balanced
    public void balance()
    {
    }

    private List<T> preorderTraverse(BinaryNode<T> next, List<T> b)
    {
        if(next !=null)
        {
            b.add(next.getInfo());
            preorderTraverse(next.getLeft(), b);
            preorderTraverse(next.getRight(), b);
        }
        return b;
    }

    private List<T> postorderTraverse(BinaryNode<T> next, List<T> b)
    {
        if(next !=null)
        {
            postorderTraverse(next.getLeft(), b);
            postorderTraverse(next.getRight(), b);
            b.add(next.getInfo());
        }
        return b;
    }

    private List<T> inorderTraverse(BinaryNode<T> next, List<T> b)
    {
        if(next !=null)
        {
            inorderTraverse(next.getLeft(), b);
            b.add(next.getInfo());
            inorderTraverse(next.getRight(), b);
        }
        return b;
    }
}