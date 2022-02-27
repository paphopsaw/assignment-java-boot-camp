package com.example.shopping.users;

public class PaymentRequest {
    private String paymentMethod;
    private String creditCardNo;
    private String creditCardName;
    private int creditCardMonth;
    private int creditCardYear;
    private String cvc;

    public PaymentRequest() {
    }

    public PaymentRequest(String paymentMethod, String creditCardNo, String creditCardName, int creditCardMonth, int creditCardYear, String cvc) {
        this.paymentMethod = paymentMethod;
        this.creditCardNo = creditCardNo;
        this.creditCardName = creditCardName;
        this.creditCardMonth = creditCardMonth;
        this.creditCardYear = creditCardYear;
        this.cvc = cvc;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public int getCreditCardMonth() {
        return creditCardMonth;
    }

    public void setCreditCardMonth(int creditCardMonth) {
        this.creditCardMonth = creditCardMonth;
    }

    public int getCreditCardYear() {
        return creditCardYear;
    }

    public void setCreditCardYear(int creditCardYear) {
        this.creditCardYear = creditCardYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
}
