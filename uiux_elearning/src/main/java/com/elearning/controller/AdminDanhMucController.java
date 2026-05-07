package com.elearning.controller;
import com.elearning.model.DanhMuc;
import com.elearning.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/danhmuc")
public class AdminDanhMucController {
    @Autowired
    private DanhMucService danhMucService;

    @GetMapping
    public String viewDashboard(Model model) {
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());
        return "admin/dashboard_danhmuc";
    }

    @GetMapping("/edit/{id}")
    public String editDanhMuc(@PathVariable int id, Model model) {
        model.addAttribute("editDm", danhMucService.getDanhMucById(id));
        model.addAttribute("danhMucList", danhMucService.getAllDanhMuc());
        return "admin/dashboard_danhmuc";
    }

    @PostMapping("/add")
    public String addDanhMuc(@RequestParam String tenDanhMuc, @RequestParam(required = false) String iconURL) {
        danhMucService.addDanhMuc(new DanhMuc(0, tenDanhMuc, iconURL));
        return "redirect:/admin/danhmuc?success=true";
    }

    @PostMapping("/update")
    public String updateDanhMuc(@RequestParam int maDM, @RequestParam String tenDanhMuc, @RequestParam(required = false) String iconURL) {
        danhMucService.updateDanhMuc(new DanhMuc(maDM, tenDanhMuc, iconURL));
        return "redirect:/admin/danhmuc?success=true";
    }

    @GetMapping("/delete")
    public String deleteDanhMuc(@RequestParam int id) {
        danhMucService.deleteDanhMuc(id);
        return "redirect:/admin/danhmuc?success=true";
    }
}
