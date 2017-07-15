package com.kook.pointrade.domain;

/**
 * Created by Sungpyo on 2016-06-12.
 */
public class PbasketDTO {

    private int pbasketKey;
    private int userKey;
    private String cardNumber;
    private int balance;
    private int pointKey;

    //from ppoint
    private String pointName;

    private int criteria;
    private int currentRateSon;
    private int currentRateMom;

    public int getPbasketKey() {
        return pbasketKey;
    }

    public void setPbasketKey(int pbasketKey) {
        this.pbasketKey = pbasketKey;
    }

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPointKey() {
        return pointKey;
    }

    public void setPointKey(int pointKey) {
        this.pointKey = pointKey;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public int getCriteria() {
        return criteria;
    }

    public void setCriteria(int criteria) {
        this.criteria = criteria;
    }

    public int getCurrentRateSon() {
        return currentRateSon;
    }

    public void setCurrentRateSon(int currentRateSon) {
        this.currentRateSon = currentRateSon;
    }

    public int getCurrentRateMom() {
        return currentRateMom;
    }

    public void setCurrentRateMom(int currentRateMom) {
        this.currentRateMom = currentRateMom;
    }
}
