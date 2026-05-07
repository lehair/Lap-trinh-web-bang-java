package com.elearning.dao;
import com.elearning.model.TracNghiem;
import java.util.List;

public interface TracNghiemDAO {
    List<TracNghiem> findAll();
    List<TracNghiem> findByBaiHocId(int maBH);
    TracNghiem findById(int id);
    boolean insert(TracNghiem tn);
    boolean update(TracNghiem tn);
    boolean delete(int id);
}
