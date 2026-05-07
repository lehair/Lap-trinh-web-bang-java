package com.elearning.service.impl;
import com.elearning.dao.BaiHocDAO;
import com.elearning.model.BaiHoc;
import com.elearning.service.BaiHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BaiHocServiceImpl implements BaiHocService {
    @Autowired
    private BaiHocDAO baiHocDAO;

    @Override
    public List<BaiHoc> getAllBaiHoc() { return baiHocDAO.findAll(); }
    @Override
    public BaiHoc getBaiHocById(int id) { return baiHocDAO.findById(id); }
    @Override
    public boolean addBaiHoc(BaiHoc bh) { return baiHocDAO.insert(bh); }
    @Override
    public boolean updateBaiHoc(BaiHoc bh) { return baiHocDAO.update(bh); }
    @Override
    public boolean deleteBaiHoc(int id) { return baiHocDAO.delete(id); }
}
