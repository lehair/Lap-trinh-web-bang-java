package com.elearning.dao;
import com.elearning.model.BaiHoc;
import java.util.List;

public interface BaiHocDAO {
    List<BaiHoc> findAll();
    BaiHoc findById(int id);
    boolean insert(BaiHoc baiHoc);
    boolean update(BaiHoc baiHoc);
    boolean delete(int id);
}
