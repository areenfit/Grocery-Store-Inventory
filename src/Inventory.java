// Arein Fetyani 1212673

import java.util.ArrayList;

// Class Inventory 
public class Inventory  {
	
	// Data Fields
	private ArrayList<Item> inventory = new ArrayList<>();
	private String category;

	// Counstructor
	public Inventory(String category) {
		this.category = category;
	}
	
	// Methods to add new items to the list
	public void newItem(String type, int quantity, double price) {
		if (findItem(type, true) == -1)
			inventory.add(new Item(type).setQuantity(quantity).setPrice(price));
		else
			System.out.println(type + " already exists");
	}

	public void newItem(String brand, String type, int quantity, double price) {
		if (findItem(type, true, brand) == -1)
			inventory.add(new Brand(brand, type).setQuantity(quantity).setPrice(price));
		else
			System.out.println(type + " already exists");
	}
	
	// Setters and Getters for the Quantity and Price for listed items if found
	public void setQuantity(String type, int quantity) {
		int index = findItem(type, false);
		if (index != -1) {
			inventory.get(index).setQuantity(quantity);
		}
	}

	public void setQuantity(String brand, String type, int quantity) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			inventory.get(index).setQuantity(quantity);
		}
	}

	public void setPrice(String type, double price) {
		int index = findItem(type, false);
		if (index != -1) {
			inventory.get(index).setPrice(price);
		}
	}

	public void setPrice(String brand, String type, double price) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			inventory.get(index).setPrice(price);
		}
	}

	public int getQuantity(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			return inventory.get(index).getQuantity();
		}
		return -1;
	}

	public int getQuantity(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			return inventory.get(index).getQuantity();
		}
		return 0;
	}

	public double getPrice(String type) {
		int index = findItem(type, false);
		if (index != -1) {
			return inventory.get(index).getPrice();
		}
		return Double.NaN;
	}

	public double getPrice(String brand, String type) {
		int index = findItem(type, false, brand);
		if (index != -1) {
			return inventory.get(index).getPrice();

		}
		return Double.NaN;
	}

	// Updating methods to change the Quantity and Price of listed items if found
	public void update(String type, int qtyIncrease) {
		if (findItem(type, false) != -1) { 
			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType())) {
					item.update(qtyIncrease);
				}
			}
		}
	}

	public void update(String brand, String type, int qtyIncrease) {
		if (findItem(type, false, brand) != -1) {
			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand())) {
					item.update(qtyIncrease);
				}
			}
		}

	}

	public void update(String type, double adjustmentFactor) {
		if (findItem(type, false) != -1) {

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType())) {
					item.update(adjustmentFactor);
				}
			}

		}
	}

	public void update(String brand, String type, double adjustmentFactor) {
		if (findItem(type, false, brand) != -1) {

			for (int i = 0; i < inventory.size(); i++) {
				Item item = inventory.get(i);
				if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand())) {
					item.update(adjustmentFactor);
				}
			}

		}
	}

	// Methods to find the wanted Item in the list
	private int findItem(String type, boolean warningIfFound) {
		int index = -1;
		int itemsFound = 0;
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (type.equals(item.getType())) {
				itemsFound++;
				index = i;
			}
		}
		if (itemsFound==1) {
			return index;
	}
		else if (itemsFound == 0 && warningIfFound == false) {
			System.out.println("cannot find " + " " + type);
		}
		else if (itemsFound != 0 && warningIfFound == true) {
			System.out.println("found more than one brand of " + type);
		}
		else if (itemsFound > 1) {
			System.out.println("found more than one brand of " + type);
		}
		
		return -1;
	}

	private int findItem(String type, boolean warningIfFound, String brand) {
		int index = -1;
		int itemsFound = 0;

		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (type.equals(item.getType()) && brand.equals(((Brand) item).getBrand()) && item instanceof Brand) {
				index = i;
				itemsFound++;
			}
		}
		if (itemsFound == 0 && warningIfFound == false) {
			System.out.println("cannot find " + brand + " " + type);
		}
		if (itemsFound != 0 && warningIfFound == true) {
				System.out.println("found more than one brand of " + type);
		}

		if (itemsFound==1) {
				return index;
		}
		return -1;

	}
	
	// Method to print complete information about the Items in the list and their total value
	public void stockReport() {
		double total = 0.0;
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			if (item instanceof Brand) {
				System.out.println(((Brand) item).getBrand() + " " + ((Brand) item).getType() + " - in stock: "
				        + ((Brand) item).getQuantity() + ", price: $" + ((Brand) item).getPrice());
				total += ((Brand) item).getQuantity() * ((Brand) item).getPrice();
			} else {
				System.out.println(
				        item.getType() + " - in stock: " + item.getQuantity() + ", price: $" + item.getPrice());
				total += (item.getQuantity() * item.getPrice());
			}
		}
		System.out.println("Total value: $" + total);
		System.out.println("");
	}
}