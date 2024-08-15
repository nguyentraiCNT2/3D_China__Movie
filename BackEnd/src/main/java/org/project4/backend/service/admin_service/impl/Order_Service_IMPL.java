package org.project4.backend.service.admin_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.controller.output.MonthlyRevenue;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.Orders_DTO;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.Notification_Entity;
import org.project4.backend.entity.Orders_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.repository.admin_repository.Notification_Repository;
import org.project4.backend.repository.admin_repository.Orders_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.service.admin_service.Order_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Order_Service_IMPL implements Order_Service {
    @Autowired
    private Orders_Repository order_Repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Movie_Repository movieRepository;
    @Autowired
    private Notification_Repository notificationRepository;

    @Override
    public List<Orders_DTO> getAll(Pageable pageable) {
        List<Orders_DTO> result = new ArrayList<>();
        List<Orders_Entity> ordersEntities = order_Repository.findAll(pageable).getContent();
        if (ordersEntities == null)
            throw new RuntimeException("Không có bộ phim có tên này");
        for (Orders_Entity orders : ordersEntities) {
            Orders_DTO dto = modelMapper.map(orders, Orders_DTO.class);
            result.add(dto);
        }
        return result;
    }

    @Override
    public int totalItem() {
        return (int) order_Repository.count();
    }

    @Override
    public List<MonthlyRevenue> getMonthlyRevenue() {
        List<Object[]> results = order_Repository.getMonthlyRevenue();
        List<MonthlyRevenue> revenueList = new ArrayList<>();

        for (Object[] result : results) {
            Integer month = (Integer) result[0];
            Integer year = (Integer) result[1];
            Long totalPoints = (Long) result[2];

            String monthYear = "Tháng " + month;
            MonthlyRevenue monthlyRevenue = new MonthlyRevenue();
            monthlyRevenue.setMonth(monthYear);
            monthlyRevenue.setTotalPoints(totalPoints);
            revenueList.add(monthlyRevenue);
        }

        return revenueList;
    }

    @Override
    public void buy_Movie(Orders_DTO orders_DTO) {

        if (orders_DTO == null)
            throw new RuntimeException("Không có đơn hàng nào");
        Movie_Entity movie = movieRepository.findById(orders_DTO.getMovie().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy phim"));
        User_Entity user = userRepository.findById(orders_DTO.getUser().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        if (user.getPoint() <= movie.getPrice().longValueExact())
            throw new RuntimeException("Xu còn lại của bạn không đủ vui lòng nạp thêm!");
        List<Orders_Entity> orderscheck = order_Repository.findByUserAndMovie(user, movie);
        if (orderscheck.size() > 0)
            throw new RuntimeException("Bạn đã mua phim này rồi!");
        Orders_Entity orders_entity = modelMapper.map(orders_DTO, Orders_Entity.class);
        Date currentDate = Date.valueOf(LocalDate.now());
        orders_entity.setMovie(movie);
        orders_entity.setUser(user);
        orders_entity.setPoint(movie.getPrice().longValueExact());
        orders_entity.setStatus("COMPLETED");
        orders_entity.setDate(currentDate);
        Orders_Entity orders = order_Repository.save(orders_entity);
        user.setPoint(user.getPoint() - movie.getPrice().longValueExact());
        userRepository.save(user);
        Notification_Entity notification = new Notification_Entity();
        notification.setUser(user);
        notification.setTimeadd(Date.valueOf(LocalDate.now()));
        notification.setTimeupdate(Date.valueOf(LocalDate.now()));
        notification.setContent("Đã mua phim: " + movie.getVnname() + " hết " + orders.getPoint() + " xu \n số dư hiện tại là: " + user.getPoint() + " xu.");
        notification.setStatus(false);
        notificationRepository.save(notification);

    }

    @Override
    public Orders_DTO getOrderById(Long id) {
        try {
            Orders_Entity orders_entity = order_Repository.findById(id).orElseThrow(() -> new Exception("Không tìm thấy đơn hàng"));
            return modelMapper.map(orders_entity, Orders_DTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Không tìm thấy đơn hang");
        }
    }

    @Override
    public Orders_DTO getByUserIdAndMovieId(Long userId, Long movieId) {
        try {
            User_Entity user = userRepository.findById(userId).orElseThrow(() -> new Exception("Không tìm thấy người dùng"));
            Movie_Entity movie = movieRepository.findById(movieId).orElseThrow(() -> new Exception("Không tìm thấy phim"));
            List<Orders_Entity> orders_entity = order_Repository.findByUserAndMovie(user, movie);
            if (orders_entity.size() == 0)
                throw new Exception("Không tìm thấy đơn hang");
            Orders_Entity orders = orders_entity.get(0);
            return modelMapper.map(orders, Orders_DTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Không tìm thấy đơn hang");
        }
    }

    @Override
    public List<Orders_DTO> getOrderByUserId(Long userId, Pageable pageable) {

        List<Orders_DTO> result = new ArrayList<>();
        User_Entity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        List<Orders_Entity> ordersEntities = order_Repository.findByUserOrderByDateDesc(user, pageable);
        if (ordersEntities == null)
            throw new RuntimeException("Không có bộ phim có tên này");
        for (Orders_Entity orders : ordersEntities) {
            Orders_DTO dto = modelMapper.map(orders, Orders_DTO.class);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Orders_DTO> getOrderByMovieId(Long movieId, Pageable pageable) {
        List<Orders_DTO> result = new ArrayList<>();
        Movie_Entity movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Không tìm thấy phim "));
        List<Orders_Entity> ordersEntities = order_Repository.findByMovie(movie, pageable);
        if (ordersEntities == null)
            throw new RuntimeException("Không có bộ phim có tên này");
        for (Orders_Entity orders : ordersEntities) {
            Orders_DTO dto = modelMapper.map(orders, Orders_DTO.class);
            result.add(dto);
        }
        return result;
    }
}
