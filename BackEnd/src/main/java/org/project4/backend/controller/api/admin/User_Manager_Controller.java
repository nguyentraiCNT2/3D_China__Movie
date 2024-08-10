package org.project4.backend.controller.api.admin;

import org.project4.backend.controller.output.Movie_output;
import org.project4.backend.controller.output.User_OutPut;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.service.admin_service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")
public class User_Manager_Controller {
    @Autowired
    private User_Service userService;
    @GetMapping("/all")
    public User_OutPut getAll( @RequestParam("page") int page, @RequestParam("limit") int limit) {
        User_OutPut result = new User_OutPut();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(userService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/getbyname")
    public User_OutPut getByName(@RequestParam("name") String name,@RequestParam("page") int page, @RequestParam("limit") int limit) {
        User_OutPut result = new User_OutPut();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByName(name,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/getbyrole")
    public User_OutPut getbyrole(@RequestParam("roleid") Long roleid,@RequestParam("page") int page, @RequestParam("limit") int limit) {
        User_OutPut result = new User_OutPut();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(userService.getByRole(roleid,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
      try {
          User_DTO user = userService.getById(id);
          return ResponseEntity.ok(user);
      }catch (Exception e) {
          return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
    @PutMapping("/update/role")
    public ResponseEntity<?> update(@RequestParam("userid") Long userid,@RequestParam("roleid") Long roleid) {
        try {
            User_DTO user =  userService.updateRole(userid, roleid);
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/recharge")
    public ResponseEntity<?> recharge(@RequestParam("userid") Long userid,@RequestParam("point") Long point) {
        try {
            User_DTO user =  userService.recharge(userid, point);
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("Xoa thanh cong");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
