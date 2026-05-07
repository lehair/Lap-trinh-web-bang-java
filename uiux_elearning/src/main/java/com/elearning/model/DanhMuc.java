package com.elearning.model;

public class DanhMuc {
    private int maDM;
    private String tenDanhMuc;
    private String iconURL;

    public DanhMuc() {}

    public DanhMuc(int maDM, String tenDanhMuc, String iconURL) {
        this.maDM = maDM;
        this.tenDanhMuc = tenDanhMuc;
        this.iconURL = iconURL;
    }

    public int getMaDM() { return maDM; }
    public void setMaDM(int maDM) { this.maDM = maDM; }
    public String getTenDanhMuc() { return tenDanhMuc; }
    public void setTenDanhMuc(String tenDanhMuc) { this.tenDanhMuc = tenDanhMuc; }
    public String getIconURL() { return iconURL; }
    public void setIconURL(String iconURL) { this.iconURL = iconURL; }
}
