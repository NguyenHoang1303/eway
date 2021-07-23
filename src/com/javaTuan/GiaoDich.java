package com.javaTuan;

public class GiaoDich {
    private KhachHang trader;
    private Integer year;   //nam giao dich
    private String podName; //Ten san pam
    private Integer amount; //gia san pam

    public GiaoDich(KhachHang trader, Integer year, String podName, Integer amount) {
        this.trader = trader;
        this.year = year;
        this.podName = podName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return trader.toString() + "Transaction(Year:"+ year +
                ", ProdName: " +podName + ", Amount: "+ amount+ ")";
    }

    public KhachHang getTrader() {
        return trader;
    }

    public void setTrader(KhachHang trader) {
        this.trader = trader;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
