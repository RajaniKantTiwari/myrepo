package com.app.community.network.response;

/**
 * Created by rajnikant on 23/02/18.
 */

public class Order{
    private int id;
    private int merchant_id;
    private String invoiceNumber;
    private String paymentStatus;
    private String invoiceDate;
    private String shipping;
    private String subtotal;
    private String grandtotal;
    private String store_name;
    private String avg_time_to_deliver;
    private String order_status;
    private boolean isFeedbacksubmit;
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isFeedbacksubmit() {
        return isFeedbacksubmit;
    }

    public void setFeedbacksubmit(boolean feedbacksubmit) {
        isFeedbacksubmit = feedbacksubmit;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAvg_time_to_deliver() {
        return avg_time_to_deliver;
    }

    public void setAvg_time_to_deliver(String avg_time_to_deliver) {
        this.avg_time_to_deliver = avg_time_to_deliver;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }
}
