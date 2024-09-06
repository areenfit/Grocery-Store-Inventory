// Arein Fetyani 1212673

// Class Item that implements the interface Comparable
public class Item implements Comparable<Item> {

	// Data Fields
	private String type;
	private int quantity;
	private double price;

	// Counstructors
	public Item() {
	}

	public Item(String type) {
		this.type = type;
	}

	// Setters & Getters
	public Item setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public Item setPrice(double price) {
		this.price = price;
		return this;
	}

	public String getType() {
		return type;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	// Methods
	public Item update(int qtyIncrease) {
		quantity= quantity+qtyIncrease;
		return this;
	}

	public Item update(double adjustmentFactor) {
		price = price + (price*adjustmentFactor);
		return this;
	}

	// Compares between prices and returns the difference between the two
	@Override
	public int compareTo(Item o) {
		return (int) (this.getPrice() - o.getPrice());
	}

}
