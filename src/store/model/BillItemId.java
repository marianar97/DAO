package store.model;

public class BillItemId {
    private int id_bill;
    private int id_item;

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public BillItemId(int id_bill, int id_item) {
        this.id_bill = id_bill;
        this.id_item = id_item;
    }

    @Override
    public String toString() {
        return "BillItemId{" +
                "id_bill=" + id_bill +
                ", id_item=" + id_item +
                '}';
    }
}
