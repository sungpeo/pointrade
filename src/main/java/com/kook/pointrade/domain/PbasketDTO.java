package com.kook.pointrade.domain;

/**
 * Created by Sungpyo on 2016-06-12.
 */
public class PbasketDTO {

    private long pbasketKey;
    private long userKey;
    private String cardNumber;
    private long balance;
    private long pointKey;

    //from ppoint
    private String pointName;

    private long criteria;
    private float currentRate;

    public long getPbasketKey() {
        return pbasketKey;
    }

    public void setPbasketKey(long pbasketKey) {
        this.pbasketKey = pbasketKey;
    }

    public long getUserKey() {
        return userKey;
    }

    public void setUserKey(long userKey) {
        this.userKey = userKey;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getPointKey() {
        return pointKey;
    }

    public void setPointKey(long pointKey) {
        this.pointKey = pointKey;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public long getCriteria() {
        return criteria;
    }

    public void setCriteria(long criteria) {
        this.criteria = criteria;
    }

    public float getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(float currentRate) {
        this.currentRate = currentRate;
    }
}
