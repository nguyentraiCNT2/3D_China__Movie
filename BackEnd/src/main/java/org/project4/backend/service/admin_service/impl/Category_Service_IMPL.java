package org.project4.backend.service.admin_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Category_DTO;
import org.project4.backend.entity.Category_Entity;
import org.project4.backend.repository.admin_repository.Category_Repository;
import org.project4.backend.service.admin_service.Category_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Category_Service_IMPL implements Category_Service {
    @Autowired
    private Category_Repository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Category_DTO> getAll(Pageable pageable) {
        List<Category_Entity> categoryEntities = categoryRepository.findAll(pageable).getContent();
        List<Category_DTO> categoryDTOS = new ArrayList<>();
        for (Category_Entity categoryEntity : categoryEntities) {
            Category_DTO categoryDTO = modelMapper.map(categoryEntity, Category_DTO.class);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public List<Category_DTO> getByName(String name,Pageable pageable) {
        List<Category_Entity> categoryEntities = categoryRepository.findByNameLike("%" + name + "%",pageable);
        List<Category_DTO> categoryDTOS = new ArrayList<>();
        for (Category_Entity categoryEntity : categoryEntities) {
            Category_DTO categoryDTO = modelMapper.map(categoryEntity, Category_DTO.class);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public Category_DTO getById(Long id) {
        try {
            Category_Entity categoryEntity = categoryRepository.findById(id).get();
            Category_DTO categoryDTO = modelMapper.map(categoryEntity, Category_DTO.class);
            return categoryDTO;
        } catch (Exception e) {
            throw new RuntimeException("Không có tài liệu nào ");
        }

    }

    @Override
    public Category_DTO save(Category_DTO category) {
        try {
            if (category == null)
                throw new RuntimeException("Không thêm được Category");
            if (category.getName() == null)
                throw new RuntimeException("Bạn chưa nhập tên loại phim!");
            Category_Entity categoryEntity = modelMapper.map(category, Category_Entity.class);
       Category_Entity categoryEntity1 =     categoryRepository.save(categoryEntity);
            Category_DTO categoryDTO = modelMapper.map(categoryEntity1, Category_DTO.class);
            return categoryDTO;
        }catch (Exception e) {
            throw new RuntimeException("Không thêm được Category");
        }
    }

    @Override
    public Category_DTO update(Category_DTO category) {
        try {
            if (category == null )
                throw  new RuntimeException(" Bạn chưa nhập dữ liệu!");
            if(category.getName() == null)
                throw new RuntimeException("Chọn tên loại phim!");
            Category_Entity categoryEntity = categoryRepository.findById(category.getId()).get();
            modelMapper.map(category, categoryEntity);
            Category_Entity categorySave = categoryRepository.save(categoryEntity);
            Category_DTO categoryDTO = modelMapper.map(categorySave, Category_DTO.class);
            return categoryDTO;
        }catch (Exception e){
            throw new RuntimeException("Có lỗi sảy ra khi cập nhật Loại phim!");
        }

     }

    @Override
    public void deleteById(Long id) {
        try {
            if (id == null)
                throw new RuntimeException("Chọn tên loại phim!");
            Category_Entity category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có loại phim nào có id = "+id));
            categoryRepository.delete(category);
        } catch (Exception e) {
            throw new RuntimeException("Có lỗi sảy ra khi xóa Loại phim!");
        }
    }

    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }

}
