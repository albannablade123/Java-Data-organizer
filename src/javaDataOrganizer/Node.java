package javaDataOrganizer;

public class Node {
	public String data;
	public Node left;
	public Node right;
	
	
	public Node(String data) {
		this.data = data;
		left = null;
		right = null;
	}
	public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public String getData() {
        return data;
    }
    
	

}
