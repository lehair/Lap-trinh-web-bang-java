package com.elearning.controller;

import com.elearning.dao.BaiHocDAO;
import com.elearning.dao.TracNghiemDAO;
import com.elearning.model.BaiHoc;
import com.elearning.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private BaiHocDAO baiHocDAO;

    @Autowired
    private TracNghiemDAO tracNghiemDAO;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());
        model.addAttribute("baiHocList", baiHocDAO.findAll());
        return "index";
    }

    @GetMapping("/baihoc/{id}")
    public String lessonDetail(@PathVariable int id, Model model) {
        BaiHoc bh = baiHocDAO.findById(id);
        model.addAttribute("baiHoc", bh);
        return "lesson_detail";
    }

    @GetMapping("/baihoc/{id}/quiz")
    public String takeQuiz(@PathVariable int id, Model model) {
        BaiHoc bh = baiHocDAO.findById(id);
        model.addAttribute("baiHoc", bh);
        model.addAttribute("tracNghiemList", tracNghiemDAO.findByBaiHocId(id));
        return "quiz_taking";
    }
}
