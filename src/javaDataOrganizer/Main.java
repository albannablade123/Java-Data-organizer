package javaDataOrganizer;
import java.io.*;
import java.util.*;

public class Main {
	
	public static void searchMaxValue(BinarySearchTree tree) {
		long startTime = System.nanoTime();
		String max = tree.findMaxValue();
		long stopTime = System.nanoTime();
		
		long elapsedTime = stopTime - startTime;
		System.out.println("Max value: " + max +  " Operation took: " + elapsedTime + " NanoSeconds");
	}
	
	public static void searchMinValue(BinarySearchTree tree) {
		long startTime = System.nanoTime();
		String min = tree.findMinValue();
		long stopTime = System.nanoTime();
		
		long elapsedTime = stopTime - startTime;
		System.out.println("Min value: " + min +  " Operation took: " + elapsedTime + " NanoSeconds");
		
		
	}

	public static BinarySearchTree insertTree(String pathToCsv, BinarySearchTree tree) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
		ArrayList<String> starWarsNames = new ArrayList<String>();
		
		String line;
		int insertionTime = 0;
		int insertionOperation = 0;
		
		while((line = csvReader.readLine()) != null && !line.isEmpty()) {
			long startTime = System.nanoTime();
			tree.insert(line);
			long stopTime = System.nanoTime();
			insertionTime += stopTime - startTime;
			insertionOperation++;
				
		}
		
		double avgInsertionTime = insertionTime/insertionOperation;

		
		System.out.println("Insertion Operation on average took: " + avgInsertionTime + " NanoSeconds");
		return tree;
	}
	
	public static void searchList(String pathToText, BinarySearchTree tree) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(pathToText));
		String line;
		
		
		while((line = csvReader.readLine()) != null && !line.isEmpty()) {
			long startTime = System.nanoTime();
			if(tree.search(line) != null) {
				long stopTime = System.nanoTime();
				long elapsedTime = stopTime - startTime;
				startTime = System.nanoTime();
				
				System.out.println("=====================");
				
				System.out.println("Node: " + tree.search(line).data);
				System.out.println("Depth: " + tree.getLevel(tree.search(line),tree.search(line).data));
				System.out.println("Is leaf? " + tree.isLeaf(tree.search(line),tree.search(line).data));
				System.out.println("Size: " + tree.getSize(tree.search(line)));
				System.out.println("Search Operation took: " + elapsedTime + " NanoSeconds");
			}
			else {
				System.out.println("============");
				System.out.println(line + " Not found");
				System.out.println("============");
			}
				
		}

	}
	public static  ArrayList<HDTestData> initArrayList(ArrayList<HDTestData> Data,String pathToCsv) throws IOException {
		BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
		String headerLine = csvReader.readLine();
		String line;
		while((line = csvReader.readLine()) != null && !line.isEmpty()) {
			String[] linex = line.split(",");
			Data.add(new HDTestData(linex[0],linex[1],linex[2],Integer.parseInt(linex[3])));		
		}
		return Data;
	}
	//part B, takes in arrayList and perform AVL tree operations
	public static void partB(ArrayList Data) throws IOException {
		AVLTree<String> avltreeSerialNum = new AVLTree<>();
		AVLTree<String> model = new AVLTree<>();
		AVLTree<String> dataCapacity = new AVLTree<>();
		AVLTree<Integer> dataPower = new AVLTree<>();
		
		int totalTime = 0;
		int totalInsertOperation = 0;
		
		int randomSample = (int) (Math.random() * Data.size());
		System.out.println(randomSample);
		for(int i = 0; i < 100;i++) {
			long startTimeInsert = System.nanoTime();
			avltreeSerialNum.insert(((HDTestData) Data.get(i)).getSerial());
			long stopTimeInsert = System.nanoTime();
			totalTime += (stopTimeInsert-startTimeInsert);
			//===============================///
			startTimeInsert = System.nanoTime();
			model.insert(((HDTestData) Data.get(i)).getModel());
			stopTimeInsert = System.nanoTime();
			totalTime += (stopTimeInsert-startTimeInsert);
			//==============================//
			startTimeInsert = System.nanoTime();
			dataCapacity.insert(((HDTestData) Data.get(i)).getCapacity());
			stopTimeInsert = System.nanoTime();
			totalTime += (stopTimeInsert-startTimeInsert);
			//=======================================//
			startTimeInsert = System.nanoTime();
			dataPower.insert(((HDTestData) Data.get(i)).getPower());
			stopTimeInsert = System.nanoTime();
			totalTime += (stopTimeInsert-startTimeInsert);
			
			totalInsertOperation+= 4;
		}
		
		int averageTime = totalTime/totalInsertOperation;
		System.out.println("inserting data to avl tree......");
		System.out.println("insertion operation on average took " + averageTime + " nanoseconds");
		System.out.println();
		System.out.println("Operation 4: Printing nodes");
		
		double totalSearchTime = 0;
		int sample = (int) (Math.random() * 250);
		long timer = 0;
		int counter = 0;
		for (int i = 0; i < sample; i++) {
			long startSearchTime =  System.nanoTime();
			long stopSearchTime = System.nanoTime();
			String errorCode = "variable not found";
			HDTestData test1 = (HDTestData) Data.get((int) ((Math.random() * sample) + 0));
			
			System.out.println("======================================");
			startSearchTime = System.nanoTime();
			if(avltreeSerialNum.search(test1.getSerial())!=null) {
				stopSearchTime = System.nanoTime();
				AVLHeightPrint(avltreeSerialNum,test1,0);
				totalSearchTime += stopSearchTime - startSearchTime;
				timer = stopSearchTime - startSearchTime;
				counter++;
				
			}else {
				System.out.println(errorCode);stopSearchTime = System.nanoTime();totalSearchTime += stopSearchTime - startSearchTime;
				counter++;
			}
			startSearchTime = System.nanoTime();
			if(model.search(test1.getModel())!=null) {
				counter++;
				stopSearchTime = System.nanoTime();
				AVLHeightPrint(model,test1,1);
				totalSearchTime += stopSearchTime - startSearchTime;
			}else {
				counter++;System.out.println(errorCode);stopSearchTime = System.nanoTime();totalSearchTime += stopSearchTime - startSearchTime;
			}
			
			
			startSearchTime = System.nanoTime();
			if(dataCapacity.search(test1.getCapacity())!=null) {
				stopSearchTime = System.nanoTime();
				AVLHeightPrint(dataCapacity,test1,2);
				counter++;
			}else {
				counter++;System.out.println(errorCode);
				stopSearchTime = System.nanoTime();
				totalSearchTime += stopSearchTime - startSearchTime;
			}
			
			startSearchTime = System.nanoTime();
			if(dataPower.search(test1.getPower())!=null) {
				counter++;
				stopSearchTime = System.nanoTime();
				AVLHeightPrint(dataPower,test1,3);
				totalSearchTime += stopSearchTime - startSearchTime;
			}else {
				counter++; System.out.println(errorCode);
				stopSearchTime = System.nanoTime();
				totalSearchTime += stopSearchTime - startSearchTime;
			}

		}
		
		double avgSearchTime = totalSearchTime/(sample*4);
		System.out.printf("\naverage search time: %f NanoSeconds with sample: %d\n ",avgSearchTime,sample);
		System.out.println();
		
		//=========================================Deletion=================
		
		System.out.println("Executing Deletion Operation......");
		
		double averageDeletionOperation =  deleteTimer(avltreeSerialNum) + deleteTimer(model) + deleteTimer(dataPower) + deleteTimer(dataCapacity);
		System.out.println("average deletion time is: " + (averageDeletionOperation/4) + " nanosecond");

	}
	
	public static double deleteTimer(AVLTree tree) {
		int numOfOperations = 0;
		long startDeleteTimer = System.nanoTime();
		long stopDeleteTimer = 0;
		int totalTime = 0;
		while(tree.getMax() != null) {
			tree.remove(tree.getMax());	
			stopDeleteTimer = System.nanoTime();
			numOfOperations++;
		}
		totalTime += stopDeleteTimer-startDeleteTimer;
		double avgTime = totalTime/numOfOperations;
		return avgTime;
		
		
		
	}
	
	
	public static void AVLHeightPrint(AVLTree tree,HDTestData object,int field) {
		switch(field) {
		case 0:
			System.out.printf("Serial %s found, and height of node is ",tree.search(object.getSerial()).getData());
			System.out.println(tree.getLevel(tree.search(object.getSerial()), object.getSerial()));
			break;
		case 1:
			System.out.printf("Model %s found, and height of node is ",tree.search(object.getModel()).getData());
			System.out.println(tree.getLevel(tree.search(object.getModel()), object.getModel()));
			break;
		case 2:
			System.out.printf("Capacity %s found, and height of node is ",tree.search(object.getCapacity()).getData());
			System.out.println(tree.getLevel(tree.search(object.getCapacity()), object.getCapacity()));
			break;
		case 3:
			System.out.printf("Power %d found, and height is ",tree.search(object.getPower()).getData());
			System.out.println(tree.getLevel(tree.search(object.getPower()), object.getPower()));
			break;
		}
		
		

	}
	


	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("provide first file: ");
		String arg1 = sc.next();
		System.out.println("provide second file: ");
		String arg2 = sc.next();
		//"C:\\Users\\hp15pav\\Documents\\Workspace\\Java_Data_Structure\\starwars.txt"
		//"C:\\Users\\hp15pav\\Documents\\Workspace\\Java_Data_Structure\\starwarssearch.txt";
		BinarySearchTree BST = new BinarySearchTree();
		BinarySearchTree Tentacion = insertTree(arg1,BST);
		BST.TreePrinter();
		System.out.println("=================");
		searchMinValue(Tentacion);
		searchMaxValue(Tentacion);
		
		
		System.out.println("===================== Part 4 to 5 ========================");
		searchList(arg2, BST);
		
		System.out.println("=====================================Part B===============================");
		AVLTree<Integer> tree = new AVLTree<Integer>();
		System.out.println("provide csv file: ");
		//String arg3 = sc.next();
		//"C:\\Users\\hp15pav\\Documents\\Workspace\\Java_Data_Structure\\data_main.csv";
	    ArrayList<HDTestData> HDTestArray = new ArrayList<HDTestData>();
	    String arg4 = "data_main.csv";

	    partB(initArrayList(HDTestArray,arg4));

		
	}

}
