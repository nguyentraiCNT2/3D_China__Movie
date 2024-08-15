package org.project4.backend.controller.api.admin;

import org.project4.backend.controller.output.MonthlyRevenue;
import org.project4.backend.controller.output.Static_OutPut;
import org.project4.backend.controller.output.Static_movie_Output;
import org.project4.backend.service.admin_service.Order_Service;
import org.project4.backend.service.admin_service.Static_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/static")
public class Static_Controller {
    @Autowired
    private Static_Service static_service;
    @Autowired
    private Order_Service orderService;

    @GetMapping("/gettongso")
    public Static_OutPut gettongso() {
        Static_OutPut result = new Static_OutPut();
        result.setTongSoUser(static_service.tongSoUser());
        result.setTongSoCategory(static_service.tongSoCategory());
        result.setTongSoMovie(static_service.tongSoMovie());
        return result;
    }
    @GetMapping("/getphimvip")
    public Static_movie_Output gettongsovip() {
        Static_movie_Output result = new Static_movie_Output();
        result.setTongSoNoVipMovie(static_service.tongSoNoVipmovie());
        result.setTongSoVipMovie(static_service.tongSoVipmovie());
        return result;
    }
    @GetMapping("/monthly-revenue")
    public List<MonthlyRevenue> getMonthlyRevenue() {
        return orderService.getMonthlyRevenue();
    }
}
