package javaDataOrganizer;
import java.util.*;

public class AVLTree<T extends Comparable<T>> {
	NodeClass<T> root;
	
	public AVLTree() {
		root = null;
	}
	
	private int checkHeight(NodeClass<T> N) {
		if(N == null) {
			return 0;
		}
		return N.getHeight();
	}
	
	public T getMin() {
		NodeClass<T> local = root;
		if(local == null) {
			return null;
		}
		while(local.getLeftChild() != null) {
			local = local.getLeftChild();
		}
		return local.getData();
	}
	
	public T getMax() {
		NodeClass<T> local = root;
		
		if(local == null) {
			return null;
		}
		while(local.getRightChild() != null) {
			local = local.getRightChild();
		}
		return local.getData();
	}
	
	public NodeClass<T> insert(T data){
		root = insert(root, data);
		
		switch (getBalance(root)) {
		case 1:
			root = leftRotate(root);
			break;
		case -1:
			root = rightRotate(root);
			break;
		default:
			break;
		}
		
		if(getBalance(root) == 1) {
			root = leftRotate(root);
		}
		else if(getBalance(root) == -1) {
			root = rightRotate(root);
		}
		return root;
		
	}
	
	public NodeClass<T> insert(NodeClass<T> node, T data) {
		if (node == null)
			return new NodeClass<T>(data);
		if (node.getData().compareTo(data) > 0) {
			node = new NodeClass<T>(node.getData(), insert(node.getLeftChild(), data),
					node.getRightChild());
		} else if (node.getData().compareTo(data) < 0) {
			node = new NodeClass<T>(node.getData(), node.getLeftChild(), insert(
					node.getRightChild(), data));
		}
		
		switch (getBalance(node)) {
		case 1:
			node = leftRotate(node);
			break;
		case -1:
			node = rightRotate(node);
			break;
		default:
			return node;
		}
		
		return node;
		
	}
	
	public NodeClass<T> leftRotate(NodeClass<T> node) {
		
		NodeClass<T> q = node;
		NodeClass<T> p = q.getRightChild();
		NodeClass<T> c = q.getLeftChild();
		NodeClass<T> a = p.getLeftChild();
		NodeClass<T> b = p.getRightChild();
		q = new NodeClass<T>(q.getData(), c, a);
		p = new NodeClass<T>(p.getData(), q, b);
		return p;
		
	}
	
	public NodeClass<T> rightRotate(NodeClass<T> node) {

		NodeClass<T> x = node;
		NodeClass<T> y = x.getLeftChild();
		NodeClass<T> three = x.getRightChild();
		NodeClass<T> one = y.getLeftChild();
		NodeClass<T> two = y.getRightChild();
		x = new NodeClass<T>(x.getData(),two,three);
		y = new NodeClass<T>(y.getData(),one,x);
		return y;
	
		
	}


	public int getBalance(NodeClass<T> N) {
		int LeftHeight = checkHeight(N.getLeftChild());
		int rightHeight = checkHeight(N.getRightChild());
		if(LeftHeight - rightHeight >= 2) {
			return -1;
		}
		else if(LeftHeight - rightHeight <= -2) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public void remove( T x )
    {
        root = remove( x, root );
    }
	private NodeClass<T> remove(T data, NodeClass<T> node )
    {
		
		 if( node == null )
	            return node;   // Item not found; do nothing
	            
	        int compareResult = data.compareTo( node.getData());
	            
	        if( compareResult < 0 )
	            node.setLeftChild(remove( data, node.getLeftChild() ));
	        else if( compareResult > 0 )
	            node.setRightChild(remove( data, node.getRightChild() ));
	        else if( node.getLeftChild()!= null && node.getRightChild() != null ) // Two children
	        {
	            node.setData((getMin()));
	            node.setRightChild(remove(node.getData(),node.getRightChild()));
	        }
	        else
	            node = ( node.getLeftChild() != null ) ? node.getLeftChild() : node.getRightChild();
	        return ( node );
    }
	
	public NodeClass<T> search(T data) {
		NodeClass<T> node = root;
		while(node != null) {
			if(node.getData().compareTo(data) == 0) {
				return node;
			}
			else if(node.getData().compareTo(data)>0) {
				node = node.getLeftChild();
			}
			else if(node.getData().compareTo(data)<0) {
				node = node.getRightChild();
			}
		}
		return node;
	}
	
	public int getLevel(NodeClass<T> node, T data)  
    { 
        return getLevel(this.root, data,0); 
    } 
	
	private int getLevel(NodeClass<T> node, T data, int level)  
    { 
        if (node == null) 
            return 0; 
   
        if (node.data.equals(data)) 
            return level; 
   
        int downlevel = getLevel(node.getLeftChild(), data, level + 1); 
        if (downlevel != 0) 
            return downlevel; 
   
        downlevel = getLevel(node.getRightChild(), data, level + 1); 
        return downlevel; 
    } 
	public String toString() {
		return root.toString();
	}
	
	
	public void PrintTree() {
		root.level = 0;
		Queue<NodeClass<T>> queue = new LinkedList<NodeClass<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			NodeClass<T> node = queue.poll();
			System.out.println(node);
			int level = node.level;
			NodeClass<T> left = node.getLeftChild();
			NodeClass<T> right = node.getRightChild();
			if (left != null) {
				left.level = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.level = level + 1;
				queue.add(right);
			}
		}
	}
	
	public void PrintElementsInTree() {
		root.level = 0;
		Queue<NodeClass<T>> queue = new LinkedList<NodeClass<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			NodeClass<T> node = queue.poll();
			System.out.println(node);
			int level = node.level;
			NodeClass<T> left = node.getLeftChild();
			NodeClass<T> right = node.getRightChild();
			if (left != null) {
				left.level = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.level = level + 1;
				queue.add(right);
			}
		}
	}
	
	void preOrder(NodeClass<T> node) { 
        if (node != null) { 
            System.out.print(node.data + " "); 
            preOrder(node.getLeftChild()); 
            preOrder(node.getRightChild()); 
        } 
    } 
}
