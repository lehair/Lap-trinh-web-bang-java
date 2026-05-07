package com.elearning.model;

public class DapAn {
    private int maDA;
    private String noiDungDapAn;
    private boolean isCorrect;
    private int maTN;

    public DapAn() {}

    public DapAn(String noiDungDapAn, boolean isCorrect) {
        this.noiDungDapAn = noiDungDapAn;
        this.isCorrect = isCorrect;
    }

    public int getMaDA() { return maDA; }
    public void setMaDA(int maDA) { this.maDA = maDA; }
    public String getNoiDungDapAn() { return noiDungDapAn; }
    public void setNoiDungDapAn(String noiDungDapAn) { this.noiDungDapAn = noiDungDapAn; }
    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }
    public int getMaTN() { return maTN; }
    public void setMaTN(int maTN) { this.maTN = maTN; }
}
