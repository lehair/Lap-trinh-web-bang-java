package com.elearning.controller;

import com.elearning.dao.TracNghiemDAO;
import com.elearning.model.DapAn;
import com.elearning.model.TracNghiem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/tracnghiem")
public class AdminTracNghiemController {
    @Autowired
    private TracNghiemDAO tracNghiemDAO;

    @GetMapping
    public String viewDashboard(Model model) {
        model.addAttribute("tracNghiemList", tracNghiemDAO.findAll());
        return "admin/dashboard_tracnghiem";
    }

    @GetMapping("/edit/{id}")
    public String editTracNghiem(@PathVariable int id, Model model) {
        model.addAttribute("editTn", tracNghiemDAO.findById(id));
        model.addAttribute("tracNghiemList", tracNghiemDAO.findAll());
        return "admin/dashboard_tracnghiem";
    }

    private List<DapAn> parseAnswers(String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung) {
        List<DapAn> list = new ArrayList<>();
        list.add(new DapAn(dapAnA, "A".equals(dapAnDung)));
        list.add(new DapAn(dapAnB, "B".equals(dapAnDung)));
        list.add(new DapAn(dapAnC, "C".equals(dapAnDung)));
        list.add(new DapAn(dapAnD, "D".equals(dapAnDung)));
        return list;
    }

    @PostMapping("/add")
    public String add(@RequestParam String noiDungCauHoi, @RequestParam(required = false) String loiGiaiThich, @RequestParam int maBH,
                      @RequestParam String dapAnA, @RequestParam String dapAnB, @RequestParam String dapAnC, @RequestParam String dapAnD,
                      @RequestParam String dapAnDung) {
        TracNghiem tn = new TracNghiem();
        tn.setNoiDungCauHoi(noiDungCauHoi);
        tn.setLoiGiaiThich(loiGiaiThich);
        tn.setMaBH(maBH);
        tn.setDanhSachDapAn(parseAnswers(dapAnA, dapAnB, dapAnC, dapAnD, dapAnDung));
        tracNghiemDAO.insert(tn);
        return "redirect:/admin/tracnghiem?success=true";
    }

    @PostMapping("/update")
    public String update(@RequestParam int maTN, @RequestParam String noiDungCauHoi, @RequestParam(required = false) String loiGiaiThich, @RequestParam int maBH,
                         @RequestParam String dapAnA, @RequestParam String dapAnB, @RequestParam String dapAnC, @RequestParam String dapAnD,
                         @RequestParam String dapAnDung) {
        TracNghiem tn = new TracNghiem();
        tn.setMaTN(maTN);
        tn.setNoiDungCauHoi(noiDungCauHoi);
        tn.setLoiGiaiThich(loiGiaiThich);
        tn.setMaBH(maBH);
        tn.setDanhSachDapAn(parseAnswers(dapAnA, dapAnB, dapAnC, dapAnD, dapAnDung));
        tracNghiemDAO.update(tn);
        return "redirect:/admin/tracnghiem?success=true";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        tracNghiemDAO.delete(id);
        return "redirect:/admin/tracnghiem?success=true";
    }
}
