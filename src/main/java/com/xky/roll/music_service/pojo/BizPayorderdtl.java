package com.xky.roll.music_service.pojo;

import javax.persistence.*;

@Table(name = "biz_payorderdtl")
public class BizPayorderdtl {
    @Column(name = "dtlID")
    private String dtlid;

    @Column(name = "itemFee")
    private String itemfee;

    @Column(name = "itemName")
    private String itemname;

    @Column(name = "itemSpec")
    private String itemspec;

    @Column(name = "itemCount")
    private String itemcount;

    @Column(name = "itemUnit")
    private String itemunit;

    @Column(name = "itemAmount")
    private String itemamount;

    @Column(name = "itemPrice")
    private String itemprice;

    @Column(name = "hospitalId")
    private String hospitalid;

    @Column(name = "paitId")
    private String paitid;

    @Column(name = "regiNum")
    private String reginum;

    @Column(name = "reciNum")
    private String recinum;

    /**
     * @return dtlID
     */
    public String getDtlid() {
        return dtlid;
    }

    /**
     * @param dtlid
     */
    public void setDtlid(String dtlid) {
        this.dtlid = dtlid;
    }

    /**
     * @return itemFee
     */
    public String getItemfee() {
        return itemfee;
    }

    /**
     * @param itemfee
     */
    public void setItemfee(String itemfee) {
        this.itemfee = itemfee;
    }

    /**
     * @return itemName
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * @param itemname
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * @return itemSpec
     */
    public String getItemspec() {
        return itemspec;
    }

    /**
     * @param itemspec
     */
    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    /**
     * @return itemCount
     */
    public String getItemcount() {
        return itemcount;
    }

    /**
     * @param itemcount
     */
    public void setItemcount(String itemcount) {
        this.itemcount = itemcount;
    }

    /**
     * @return itemUnit
     */
    public String getItemunit() {
        return itemunit;
    }

    /**
     * @param itemunit
     */
    public void setItemunit(String itemunit) {
        this.itemunit = itemunit;
    }

    /**
     * @return itemAmount
     */
    public String getItemamount() {
        return itemamount;
    }

    /**
     * @param itemamount
     */
    public void setItemamount(String itemamount) {
        this.itemamount = itemamount;
    }

    /**
     * @return itemPrice
     */
    public String getItemprice() {
        return itemprice;
    }

    /**
     * @param itemprice
     */
    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    /**
     * @return hospitalId
     */
    public String getHospitalid() {
        return hospitalid;
    }

    /**
     * @param hospitalid
     */
    public void setHospitalid(String hospitalid) {
        this.hospitalid = hospitalid;
    }

    /**
     * @return paitId
     */
    public String getPaitid() {
        return paitid;
    }

    /**
     * @param paitid
     */
    public void setPaitid(String paitid) {
        this.paitid = paitid;
    }

    /**
     * @return regiNum
     */
    public String getReginum() {
        return reginum;
    }

    /**
     * @param reginum
     */
    public void setReginum(String reginum) {
        this.reginum = reginum;
    }

    /**
     * @return reciNum
     */
    public String getRecinum() {
        return recinum;
    }

    /**
     * @param recinum
     */
    public void setRecinum(String recinum) {
        this.recinum = recinum;
    }
}