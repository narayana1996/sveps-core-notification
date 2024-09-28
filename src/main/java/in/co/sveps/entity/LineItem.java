package in.co.sveps.entity;


import lombok.Data;

@Data
public class LineItem {

    private String description;
    private int quantity;
    private double unitPrice;
    private double total;

    public LineItem() {
        this.total = quantity * unitPrice;
    }


}
