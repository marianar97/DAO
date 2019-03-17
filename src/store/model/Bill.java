package store.model;

import java.util.List;
import java.util.Date;

/**
 * This class represents a bill object with it's corresponding
 * billId, date, clientId, itemsId
 * @author Mariana Ramirez Duque
 * @version 1.0
 *
 */

public class Bill {
    private int billId;
    private Date date;
    private int clientId;
    private List<Integer> itemsId;

    /**
     * Creates a bill object with it's corresponding parameters
     * @param date      The bill's date
     * @param clientId  The clientId owner of the bill
     * @param itemsId   The itemsID that were purchased
     */
    public Bill(Date date, int clientId, List<Integer> itemsId) {
        this.date = date;
        this.clientId = clientId;
        this.itemsId = itemsId;
    }

    /**
     * Gets the bill's id
     * @return an int representing the id of the bill
     */
    public int getBillId() {
        return billId;
    }

    /**
     * Sets the bill's id
     * @param billId an int representing the id of the bill
     */
    public void setBillId(int billId) {
        this.billId = billId;
    }

    /**
     * Gets the bill's Date
     * @return a Date object representing the bill's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the bill's date
     * @param date a date object representing the date of the bill
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the clientId owner of the bill
     * @return an int representing the clientId owner of the bill
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the clientId owner of the bill
     * @param clientId an int representing the clientId owner of the bill
     */

    public void setClientid(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the ids of the items that were purchased
     * @return a List of Integers representing the ids of the items that were purchased
     */
    public List<Integer> getItemsId() {
        return itemsId;
    }

    /**
     * Sets the ids of the items that were purchased
     * @param itemsId a List of Integers representing the ids of the items that were purchased
     */
    public void setItemsId(List<Integer> itemsId) {
        this.itemsId = itemsId;
    }


    /**
     * This methods return the object's attributes as a String
     * @return A String representing the object attributes
     */
    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", date=" + date +
                ", clientId=" + clientId +
                ", itemsId=" + itemsId +
                '}';
    }
}

