package com.kook.pointrade.domain;

import java.math.BigDecimal;

/**
 * Created by Sungpyo on 2016-06-19.
 */
public class PtradeDTO {
    private int userKey;
    private int fromPointKey;
    private int toPointKey;
    private BigDecimal rateSon;
    private BigDecimal rateMom;

    private String tradeCode;
    private int amount;

    private long timeMillis;


    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public int getFromPointKey() {
        return fromPointKey;
    }

    public void setFromPointKey(int fromPointKey) {
        this.fromPointKey = fromPointKey;
    }

    public int getToPointKey() {
        return toPointKey;
    }

    public void setToPointKey(int toPointKey) {
        this.toPointKey = toPointKey;
    }

    public BigDecimal getRateSon() {
        return rateSon;
    }

    public void setRateSon(BigDecimal rateSon) {
        this.rateSon = rateSon;
    }

    public BigDecimal getRateMom() {
        return rateMom;
    }

    public void setRateMom(BigDecimal rateMom) {
        this.rateMom = rateMom;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }
}
