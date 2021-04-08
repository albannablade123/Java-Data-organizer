package javaDataOrganizer;

public class BinarySearchTree {
	private Node root;
	
	//=======Insert function========
	public void insert(String data) {
		if(root == null) {
			root = new Node(data);
			return;
		}
		
		Node pointer = root;
		
		while(true) {
			if(data.compareTo(pointer.data) < 0) {
				if(pointer.left != null) {
					pointer = pointer.left;
				}
				else {
					pointer.left = new Node(data);
					break;
				}
			}
			else if (data.compareTo(pointer.data)>0) {
				if(pointer.right != null) {
					pointer = pointer.right;
				}
				else {
					pointer.right = new Node(data);
					break;
				}
			}
		}
	}
	
	//=======function to find Max value========
	
	public String findMaxValue() {
        return maxValue(this.root);
    }
 
	
	private String maxValue(Node node) {
        if(node.getRight() != null) {
            return maxValue(node.getRight());
        }
        return node.getData();
    }
	
	//=======function to find Min value========
	public String findMinValue() {
        return minValue(this.root);
    }
	
	private String minValue(Node node) {

        if(node.getLeft() != null) {
            return minValue(node.getLeft());
        }
        return node.getData();
    }
	
	//=======function to get level========
	
	 /*
	
    */

	public void TreePrinter() {
		System.out.println("===================Tree Traversal Print============");
		traverseTree(root);
	}
	private void traverseTree(Node T) {
		if(T != null) {
			traverseTree(T.left);
			System.out.println(T.data + " ");
			traverseTree(T.right);
		}
	}
	
	
	
	//=======function to Check if the node is a leaf========
	/*
	public boolean isLeaf(String data) {
		return isLeaf(root,data);
	}
	*/
	
	
	public boolean isLeaf(Node T, String data) {
		//System.out.println("right data is" + T.right.data);
		
		if(T == null) {
			return false;
		}
		if (T.left == null && T.right == null) {
	        return true;   
	    }
		return false;
		
	}
	//=======function Search========
	public Node search(String Data) {
		return searchRecursive(this.root, Data);
	}
	
	
	private Node searchRecursive(Node root, String Data) {
		
		if(root == null) {
			return null;
        }
            
        if (root.data.equals(Data)) {
        	return root;
        }
        
            
        if (Data.compareTo(root.data) < 0) {
        	return searchRecursive(root.left, Data);
      
        }
        else if (Data.compareTo(root.data)> 0) {
        	return searchRecursive(root.right, Data);
        		
        }
        return null;
	}
	
	public Node getNode(String Data) {
    	return root;
    }
	
	public int getLevel(Node node, String data)  
    { 
        return getLevel(this.root, data,0); 
    } 
	
	private int getLevel(Node node, String data, int level)  
    { 
        if (node == null) 
            return 0; 
   
        if (node.data.equals(data)) 
            return level; 
   
        int downlevel = getLevel(node.left, data, level + 1); 
        if (downlevel != 0) 
            return downlevel; 
   
        downlevel = getLevel(node.right, data, level + 1); 
        return downlevel; 
    } 
	
	public int getSize(Node root){
		if(root==null){
			return 0;
		}
		return 1 + getSize(root.left) + getSize(root.right);
	}
   
    /* Returns level of given data value */
    
	
	
}
