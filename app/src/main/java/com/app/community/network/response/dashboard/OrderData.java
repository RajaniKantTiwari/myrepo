package com.app.community.network.response.dashboard;

/**
 * Created by rajnikant on 03/04/18.
 */

public class OrderData {
    private String productname;
    private String quantity;
    private String tax_status;
    private String pricewithtax;
    private String pricewithouttax;
    private String selling_price;
    private String product_mrp;
    private String tax;
    private String avg_time_to_deliver;
    private String shipping;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(String product_mrp) {
        this.product_mrp = product_mrp;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getAvg_time_to_deliver() {
        return avg_time_to_deliver;
    }

    public void setAvg_time_to_deliver(String avg_time_to_deliver) {
        this.avg_time_to_deliver = avg_time_to_deliver;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }
}
