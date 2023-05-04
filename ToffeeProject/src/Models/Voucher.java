package Models;
public class Voucher {
    private int id;
    private double Discount;
    String typeOfDiscount;
    public int getId() {
        return id;
    }
    public void setDiscount(double discount) {
        Discount = discount;
    }
    public double getDiscount() {
        return Discount;
    }
    public void setTypeOfDiscount(String typeOfDiscount) {
        this.typeOfDiscount = typeOfDiscount;
    }
    public String getTypeOfDiscount() {
        return typeOfDiscount;
    }
}
