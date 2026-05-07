package com.elearning.model;

import java.util.ArrayList;
import java.util.List;

public class TracNghiem {
    private int maTN;
    private String noiDungCauHoi;
    private String hinhAnh;
    private String loiGiaiThich;
    private int maBH;
    
    // Thêm danh sách đáp án cho câu hỏi này
    private List<DapAn> danhSachDapAn = new ArrayList<>();

    public TracNghiem() {}

    public int getMaTN() { return maTN; }
    public void setMaTN(int maTN) { this.maTN = maTN; }
    public String getNoiDungCauHoi() { return noiDungCauHoi; }
    public void setNoiDungCauHoi(String noiDungCauHoi) { this.noiDungCauHoi = noiDungCauHoi; }
    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
    public String getLoiGiaiThich() { return loiGiaiThich; }
    public void setLoiGiaiThich(String loiGiaiThich) { this.loiGiaiThich = loiGiaiThich; }
    public int getMaBH() { return maBH; }
    public void setMaBH(int maBH) { this.maBH = maBH; }
    public List<DapAn> getDanhSachDapAn() { return danhSachDapAn; }
    public void setDanhSachDapAn(List<DapAn> danhSachDapAn) { this.danhSachDapAn = danhSachDapAn; }
}
