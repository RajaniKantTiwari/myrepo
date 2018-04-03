package com.app.community.network.response.dashboard;

/**
 * Created by rajnikant on 03/04/18.
 */

public class OrderData {
    private String productname;
    private int quantity;
    private String tax_status;
    private String pricewithtax;
    private String pricewithouttax;
    private float selling_price;
    private float product_mrp;
    private float tax;
    private String avg_time_to_deliver;
    private float shipping;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTax_status() {
        return tax_status;
    }

    public void setTax_status(String tax_status) {
        this.tax_status = tax_status;
    }

    public String getPricewithtax() {
        return pricewithtax;
    }

    public void setPricewithtax(String pricewithtax) {
        this.pricewithtax = pricewithtax;
    }

    public String getPricewithouttax() {
        return pricewithouttax;
    }

    public void setPricewithouttax(String pricewithouttax) {
        this.pricewithouttax = pricewithouttax;
    }

    public float getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(float selling_price) {
        this.selling_price = selling_price;
    }

    public float getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(float product_mrp) {
        this.product_mrp = product_mrp;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public String getAvg_time_to_deliver() {
        return avg_time_to_deliver;
    }

    public void setAvg_time_to_deliver(String avg_time_to_deliver) {
        this.avg_time_to_deliver = avg_time_to_deliver;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }
}
