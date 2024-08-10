package org.project4.backend.service.admin_service;

import org.project4.backend.dto.Category_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Category_Service {
    public List<Category_DTO> getAll(Pageable pageable);
    public List<Category_DTO> getByName(String name,Pageable pageable);

    public Category_DTO getById(Long id);

    public Category_DTO save(Category_DTO category);

    public Category_DTO update(Category_DTO category);

    public void deleteById(Long id);
    int totalItem();
}
