package com.example.shopping.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @Id
    private int id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne
    private Contact contact;

    private double totalAmount;

    private boolean paymentReceived;
    private String paymentMethod;
    private String creditCardNo;
    private String creditCardName;
    private int creditCardMonth;
    private int creditCardYear;
    private String cvc;

    public Order() {
    }

    public Order(int id) {
        this.id = id;
    }

    public void setPaymentInfo(
            boolean paymentReceived,
            String paymentMethod,
            String creditCardNo,
            String creditCardName,
            int creditCardMonth,
            int creditCardYear,
            String cvc
    ) {
        this.paymentReceived = paymentReceived;
        this.paymentMethod = paymentMethod;
        this.creditCardNo = creditCardNo;
        this.creditCardName = creditCardName;
        this.creditCardMonth = creditCardMonth;
        this.creditCardYear = creditCardYear;
        this.cvc = cvc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(boolean paymentReceived) {
        this.paymentReceived = paymentReceived;
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
