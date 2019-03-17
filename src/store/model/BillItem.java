package store.model;

public class BillItem {

    private BillItemId id;

    public BillItem(BillItemId id) {
        this.id = id;
    }

    public BillItemId getId() {
        return id;
    }

    public void setId(BillItemId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BillItem{" +
                "id=" + id +
                '}';
    }
}
