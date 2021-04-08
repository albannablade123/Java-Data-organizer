package javaDataOrganizer;
import java.util.*;

public class NodeClass<T extends Comparable<T>> implements Comparable<NodeClass<T>> {
	T data;
	public int height;
	private NodeClass<T> leftChild;
	private NodeClass<T> rightChild;
	public int level;
	
	NodeClass(T d){
		this(d,null,null);
	}

	public NodeClass(T data, NodeClass<T> leftChild, NodeClass<T> rightChild) {
		super();
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		
		if (leftChild == null && rightChild == null) {
			setHeight(1);
		}
		else if (leftChild == null) {
			setHeight(rightChild.getHeight() + 1);
		}
		else if (rightChild == null) {
			setHeight(leftChild.getHeight() + 1);
		}
		else {
			setHeight(Math.max(leftChild.getHeight(), rightChild.getHeight()));
		}
		
	}

	protected T getData() {
		return data;
	}

	protected void setData(T data) {
		this.data = data;
	}

	protected int getHeight() {
		return height;
	}

	protected void setHeight(int height) {
		this.height = height;
	}

	protected NodeClass<T> getLeftChild() {
		return leftChild;
	}

	protected void setLeftChild(NodeClass<T> leftChild) {
		this.leftChild = leftChild;
	}

	protected NodeClass<T> getRightChild() {
		return rightChild;
	}

	protected void setRightChild(NodeClass<T> rightChild) {
		this.rightChild = rightChild;
	}

	protected int getLevel() {
		return level;
	}

	protected void setLevel(int level) {
		this.level = level;
	}
	
	public int compareTo(NodeClass<T> o) {
		return this.data.compareTo(o.data);
	}
	
	public String toString() {
		return "" + data;

	}

}
