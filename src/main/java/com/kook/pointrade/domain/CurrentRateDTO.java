package com.kook.pointrade.domain;

import java.math.BigDecimal;

/**
 * Created by Sungpyo on 2016-06-25.
 */
public class CurrentRateDTO {
    private int fromPointKey;
    private int toPointKey;
    private String tradeCode;
    private BigDecimal currentRateSon;
    private BigDecimal currentRateMom;


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

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public BigDecimal getCurrentRateSon() {
        return currentRateSon;
    }

    public void setCurrentRateSon(BigDecimal currentRateSon) {
        this.currentRateSon = currentRateSon;
    }

    public BigDecimal getCurrentRateMom() {
        return currentRateMom;
    }

    public void setCurrentRateMom(BigDecimal currentRateMom) {
        this.currentRateMom = currentRateMom;
    }
}
