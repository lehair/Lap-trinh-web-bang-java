package com.elearning.dao;
import com.elearning.model.DanhMuc;
import java.util.List;

public interface DanhMucDAO {
    List<DanhMuc> findAll();
    DanhMuc findById(int id);
    boolean insert(DanhMuc danhMuc);
    boolean update(DanhMuc danhMuc);
    boolean delete(int id);
}
