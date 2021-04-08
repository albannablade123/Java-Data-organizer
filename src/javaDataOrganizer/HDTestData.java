package javaDataOrganizer;

public class HDTestData {
	String serial;
	String Model;
	String Capacity;
	int power;
	
	public HDTestData(String serial, String model, String capacity, int power) {
		super();
		this.serial = serial;
		Model = model;
		Capacity = capacity;
		this.power = power;
	}
	protected String getSerial() {
		return serial;
	}
	protected void setSerial(String serial) {
		this.serial = serial;
	}
	protected String getModel() {
		return Model;
	}
	protected void setModel(String model) {
		Model = model;
	}
	protected String getCapacity() {
		return Capacity;
	}
	protected void setCapacity(String capacity) {
		Capacity = capacity;
	}
	protected int getPower() {
		return power;
	}
	protected void setPower(int power) {
		this.power = power;
	}

}
