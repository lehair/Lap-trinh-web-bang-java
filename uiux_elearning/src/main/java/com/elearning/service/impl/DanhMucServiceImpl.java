package com.elearning.service.impl;
import com.elearning.dao.DanhMucDAO;
import com.elearning.model.DanhMuc;
import com.elearning.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DanhMucServiceImpl implements DanhMucService {
    @Autowired
    private DanhMucDAO danhMucDAO;

    @Override
    public List<DanhMuc> getAllDanhMuc() { return danhMucDAO.findAll(); }

    @Override
    public DanhMuc getDanhMucById(int id) { return danhMucDAO.findById(id); }

    @Override
    public boolean addDanhMuc(DanhMuc dm) { return danhMucDAO.insert(dm); }

    @Override
    public boolean updateDanhMuc(DanhMuc dm) { return danhMucDAO.update(dm); }

    @Override
    public boolean deleteDanhMuc(int id) { return danhMucDAO.delete(id); }
}
