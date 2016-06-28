package com.kook.pointrade.service.object;

import com.kook.pointrade.constants.AppConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Sungpyo on 2016-06-26.
 */
public class Rate implements Comparable<Rate>{
    private BigDecimal rateSon;
    private BigDecimal rateMom;

    public Rate (BigDecimal rateSon, BigDecimal rateMom){
        this.rateSon = rateSon;
        this.rateMom = rateMom;
    }

    public int adjustRate(int amount){

        return (int) BigDecimal.valueOf(amount).multiply(rateSon).divide(rateMom,0, RoundingMode.DOWN).longValue();
    }


    public Rate up(){
        if(BigDecimal.ONE.equals(this.rateMom))
            this.rateSon.add(AppConstants.UNIT_RATE);
        else
            this.rateMom.subtract(AppConstants.UNIT_RATE);
        return this;
    }
    public Rate down(){
        if(BigDecimal.ONE.equals(this.rateSon))
            this.rateMom.add(AppConstants.UNIT_RATE);
        else
            this.rateSon.subtract(AppConstants.UNIT_RATE);
        return this;
    }

    public Rate upsideDown(){
        return new Rate(rateMom, rateSon);
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

    @Override
    public boolean equals(Object anObject){
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Rate) {
            Rate anotherRate = (Rate) anObject;
            return this.rateSon.equals(anotherRate.rateSon) && this.rateMom.equals(anotherRate.rateMom);
        }
        return false;
    }

    @Override
    public int compareTo(Rate o) {
        int son = this.rateSon.compareTo(o.rateSon);
        return son!=0 ? son : o.rateMom.compareTo(this.rateMom);
    }

}
