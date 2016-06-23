package com.kook.pointrade.domain;

/**
 * Created by Sungpyo on 2016-06-19.
 */
public class PtradeDTO {
    private long userKey;
    private long fromPointKey;
    private long toPointKey;
    private String tradeCode;
    private float rate;
    private long amount;
    private String statCode;

    public long getUserKey() {
        return userKey;
    }

    public void setUserKey(long userKey) {
        this.userKey = userKey;
    }

    public long getFromPointKey() {
        return fromPointKey;
    }

    public void setFromPointKey(long fromPointKey) {
        this.fromPointKey = fromPointKey;
    }

    public long getToPointKey() {
        return toPointKey;
    }

    public void setToPointKey(long toPointKey) {
        this.toPointKey = toPointKey;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getStatCode() {
        return statCode;
    }

    public void setStatCode(String statCode) {
        this.statCode = statCode;
    }


}
