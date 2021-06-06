package dto;

public class CollectionDto {
    private String id;
    private String session;
    private String item;
    private String supplier;
    private String reading;
    private String test;
    private String qty;
    private String UOM;
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
