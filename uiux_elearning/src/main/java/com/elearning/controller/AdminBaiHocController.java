package com.elearning.controller;
import com.elearning.model.BaiHoc;
import com.elearning.service.BaiHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/baihoc")
public class AdminBaiHocController {
    @Autowired
    private BaiHocService baiHocService;

    @GetMapping
    public String viewDashboard(Model model) {
        model.addAttribute("baiHocList", baiHocService.getAllBaiHoc());
        return "admin/dashboard_baihoc";
    }

    @GetMapping("/edit/{id}")
    public String editBaiHoc(@PathVariable int id, Model model) {
        model.addAttribute("editBh", baiHocService.getBaiHocById(id));
        model.addAttribute("baiHocList", baiHocService.getAllBaiHoc());
        return "admin/dashboard_baihoc";
    }

    @PostMapping("/add")
    public String addBaiHoc(@RequestParam String tenBH, @RequestParam String noiDungBH, @RequestParam int maDM, Model model) {
        BaiHoc bh = new BaiHoc();
        bh.setTenBH(tenBH);
        bh.setNoiDungBH(noiDungBH);
        bh.setMaDM(maDM);
        baiHocService.addBaiHoc(bh);
        return "redirect:/admin/baihoc?success=true";
    }

    @PostMapping("/update")
    public String updateBaiHoc(@RequestParam int maBH, @RequestParam String tenBH, @RequestParam String noiDungBH, @RequestParam int maDM, Model model) {
        BaiHoc bh = new BaiHoc();
        bh.setMaBH(maBH);
        bh.setTenBH(tenBH);
        bh.setNoiDungBH(noiDungBH);
        bh.setMaDM(maDM);
        baiHocService.updateBaiHoc(bh);
        return "redirect:/admin/baihoc?success=true";
    }

    @GetMapping("/delete")
    public String deleteBaiHoc(@RequestParam int id) {
        baiHocService.deleteBaiHoc(id);
        return "redirect:/admin/baihoc?success=true";
    }
}
