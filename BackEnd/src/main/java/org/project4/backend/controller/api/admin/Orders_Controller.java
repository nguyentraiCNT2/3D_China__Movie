package org.project4.backend.controller.api.admin;

import org.project4.backend.controller.output.Orders_OutPut;
import org.project4.backend.controller.output.Orders_OutPut;
import org.project4.backend.service.admin_service.Order_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
public class Orders_Controller {
    @Autowired
    private Order_Service orderService;
    @GetMapping("/all")
    public Orders_OutPut getAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Orders_OutPut result = new Orders_OutPut();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(orderService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/getbyuser")
    public Orders_OutPut getbyuser(@RequestParam("userid") Long userid,@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Orders_OutPut result = new Orders_OutPut();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(orderService.getOrderByUserId(userid,pageable));
        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
        return result;
    }

}
