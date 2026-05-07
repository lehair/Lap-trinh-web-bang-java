package com.elearning.model;

import java.sql.Timestamp;

public class BaiHoc {
    private int maBH;
    private String tenBH;
    private String noiDungBH;
    private String linkURL;
    private String hinhAnh;
    private Timestamp ngayDang;
    private int maDM;

    public BaiHoc() {}

    public int getMaBH() { return maBH; }
    public void setMaBH(int maBH) { this.maBH = maBH; }
    public String getTenBH() { return tenBH; }
    public void setTenBH(String tenBH) { this.tenBH = tenBH; }
    public String getNoiDungBH() { return noiDungBH; }
    public void setNoiDungBH(String noiDungBH) { this.noiDungBH = noiDungBH; }
    public String getLinkURL() { return linkURL; }
    public void setLinkURL(String linkURL) { this.linkURL = linkURL; }
    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
    public Timestamp getNgayDang() { return ngayDang; }
    public void setNgayDang(Timestamp ngayDang) { this.ngayDang = ngayDang; }
    public int getMaDM() { return maDM; }
    public void setMaDM(int maDM) { this.maDM = maDM; }
}
