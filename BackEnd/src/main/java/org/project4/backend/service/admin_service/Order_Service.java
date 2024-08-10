package org.project4.backend.service.admin_service;

import org.project4.backend.controller.output.MonthlyRevenue;
import org.project4.backend.dto.Orders_DTO;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface Order_Service {
    List<Orders_DTO> getAll(Pageable pageable);
    int totalItem();
    List<MonthlyRevenue>getMonthlyRevenue();
    void  buy_Movie(Orders_DTO orders_DTO);
    Orders_DTO getOrderById(Long id);
    Orders_DTO getByUserIdAndMovieId(Long userId, Long movieId);
    List<Orders_DTO> getOrderByUserId(Long userId, Pageable pageable);
    List<Orders_DTO> getOrderByMovieId(Long movieId, Pageable pageable);
}
