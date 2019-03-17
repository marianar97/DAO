package store.model;

/**
 * This class represents a bill object with it's corresponding
 * id, itemType, description, unitValue
 * @author Mariana Ramirez Duque
 * @version 1.0
 *
 */

public class Item {
    private int id;
    private int itemT;
    private String description;
    private float unitValue;

    /**
     * Creates an object Item with it's corresponding attributes
     * @param itemT         The type of the item
     * @param description   The description of the item
     * @param unitValue     The value of each unit of the item
     */
    public Item(int itemT, String description, float unitValue) {
        this.itemT = itemT;
        this.description = description;
        this.unitValue = unitValue;
    }

    /**
     * Gets the type of the item
     * @return an int representing the type of the item
     */
    public int getItemT() {
        return itemT;
    }

    /**
     * Sets the type of the item
     * @param itemT an int representing the type of the item
     */
    public void setItemT(int itemT) {
        this.itemT = itemT;
    }

    /***
     * Gets the item's id
     * @return  an int representing the item's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the item's id
     * @param id an int representing the item's id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the item's description
     * @return a String representing the item's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the item's description
     * @param description a String representing the item's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the item's unit value
     * @return a float representing the item's unit value
     */
    public float getUnitValue() {
        return unitValue;
    }

    /**
     * Sets the item's unit value
     * @param unitValue a float representing the item's unit value
     */
    public void setUnitValue(float unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * This methods return the object's attributes as a String
     * @return A String representing the object attributes
     */
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemT=" + itemT +
                ", description='" + description + '\'' +
                ", unitValue=" + unitValue +
                '}';
    }
}
