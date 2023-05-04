package Models;
public class Voucher {
    private int id;
    private double Discount;
    String typeOfDiscount;
    private int adminId;
    private int cartId;
    private static int LastID = 0;

    Voucher(double discount,String type,int admin_id) {
        LastID++;
        id = LastID;
        Discount = discount;
        typeOfDiscount = type;
        adminId = admin_id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

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
