package org.project4.backend.service.admin_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Role_DTO;
import org.project4.backend.entity.Role_Entity;
import org.project4.backend.repository.admin_repository.Role_Repository;
import org.project4.backend.service.admin_service.Role_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Role_Service_IMPL implements Role_Service {
    @Autowired
    private Role_Repository role_Repository;
    @Autowired
    private ModelMapper modelMapper;
    // hiển thị tất cả quyền hạn
    @Override
    public List<Role_DTO> getAll(Pageable pageable) {
        // time kiếm tất cả quyền hạn
        List<Role_Entity> list = role_Repository.findAll(pageable).getContent();
        // kiểm tra nếu không có quyền hạn nào
        if (list.isEmpty()) {
            throw new RuntimeException("Không có quyền hạn nào!");
        }
        List<Role_DTO> role_DTOS = new ArrayList<>();
        for (Role_Entity role_Entity : list) {
            Role_DTO role_DTO = modelMapper.map(role_Entity, Role_DTO.class);
            role_DTOS.add(role_DTO);
        }
        return role_DTOS;
    }
    // lấy quyền hạn theo tên
    @Override
    public List<Role_DTO> getByName(String name) {
        // tìm kiếm quyền hạn theo tên
        List<Role_Entity> list = role_Repository.findByName(name);
        // kiểm tra nếu không có quyền nào
        if (list.isEmpty()) {
            throw new RuntimeException("Không có quyền này!");
        }
        List<Role_DTO> role_DTOS = new ArrayList<>();
        for (Role_Entity role_Entity : list) {
            Role_DTO role_DTO = modelMapper.map(role_Entity, Role_DTO.class);
            role_DTOS.add(role_DTO);
        }
        return role_DTOS;
    }
    // hiển thị quyền hạn theo id
    @Override
    public Role_DTO getById(Long id) {
        try {
            // tìm kiếm quyền hạn theo id
            Role_Entity role_Entity = role_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
            Role_DTO role_DTO = modelMapper.map(role_Entity, Role_DTO.class);
            return role_DTO;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // thêm mới quyền hạn
    @Override
    public Role_DTO create(Role_DTO role) {
        try {
            // kiểm tra dữ liệu nhập vào có trống hay không
            if (role == null) throw new RuntimeException("Không đòng dạng!");
            // kiểm tra tên quyền hạn có trống hay không
        if (role.getName() == null || role.getName().equals("")) throw new RuntimeException("Không hãy nhập tên quynen!");
        Role_Entity role_Entity = modelMapper.map(role, Role_Entity.class);
         Role_Entity role_EP =   role_Repository.save(role_Entity);
         Role_DTO role_DTO = modelMapper.map(role_EP, Role_DTO.class);
            return role_DTO;
        }catch (Exception e) {
         throw new RuntimeException("Có lỗi không xác định khi thêm quyền hạn!");
        }
    }
// cập nhật quyền hạn
    @Override
    public Role_DTO update(Role_DTO role) {
        try {
            // kiểm tra dữ liệu nhập vào có trống hay không
            if (role == null) throw new RuntimeException("Dữ liệu nhập vào không thể để chống!");
            // tìm kiếm quyền hạn theo id
            Role_Entity role_Entity = role_Repository.findById(role.getId()).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
            modelMapper.map(role, role_Entity);
            Role_Entity role_EP = role_Repository.save(role_Entity);
            Role_DTO role_DTO = modelMapper.map(role_EP, Role_DTO.class);
            return role_DTO;
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định khi thêm quyền hình!");
        }
    }
    // xóa quyền hạn
    @Override
    public void delete(Long id) {
        try {
            // tìm kiếm quyền hạn theo id
            Role_Entity role_Entity = role_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
            role_Repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định khi xóa quyền hạn!");
        }
    }
}
