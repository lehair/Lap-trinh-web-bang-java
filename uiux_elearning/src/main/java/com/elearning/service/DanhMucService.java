package com.elearning.service;
import com.elearning.model.DanhMuc;
import java.util.List;

public interface DanhMucService {
    List<DanhMuc> getAllDanhMuc();
    DanhMuc getDanhMucById(int id);
    boolean addDanhMuc(DanhMuc danhMuc);
    boolean updateDanhMuc(DanhMuc danhMuc);
    boolean deleteDanhMuc(int id);
}
