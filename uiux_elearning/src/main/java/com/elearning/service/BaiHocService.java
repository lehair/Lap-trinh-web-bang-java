package com.elearning.service;
import com.elearning.model.BaiHoc;
import java.util.List;

public interface BaiHocService {
    List<BaiHoc> getAllBaiHoc();
    BaiHoc getBaiHocById(int id);
    boolean addBaiHoc(BaiHoc baiHoc);
    boolean updateBaiHoc(BaiHoc baiHoc);
    boolean deleteBaiHoc(int id);
}
