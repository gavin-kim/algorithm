package designpattern.structural.composite;

public class Part extends Item {

    public Part(String description, int cost) {
        super(description, cost);
    }

    /** Empty implementation, no need for parts */
    public void addItem(Item item) {

    }
    public void removeItem(Item item) {

    }
    public Item[] getItems() {
        return new Item[0];
    }
}
