package store.model;

/**
 * This class represents a itemType object with it's corresponding
 * id and description
 * @author Mariana Ramirez Duque
 * @version 1.0
 *
 */

public class ItemType {
    private int id;
    private String description;

    /**
     * Create an item type object with the corresponding parameters
     * @param description   The description of the item type
     */
    public ItemType(String description) {
        this.description = description;
    }

    /**
     * Gets the id of the ItemType
     * @return an int representing the ItemType id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the ItemType
     * @param id an int representing the ItemType id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the description of the ItemType
     * @return a String representing the description of ItemType
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the ItemType
     * @param description a String representing the description of ItemType
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This methods return the object's attributes as a String
     * @return A String representing the object attributes
     */
    @Override
    public String toString() {
        return "ItemType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
