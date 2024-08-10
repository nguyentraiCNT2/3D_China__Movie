package org.project4.backend.controller.output;

import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.Orders_DTO;

import java.util.ArrayList;
import java.util.List;

public class Orders_OutPut {
    private int page;
    private int totalPage;
    private List<Orders_DTO > listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Orders_DTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<Orders_DTO> listResult) {
        this.listResult = listResult;
    }
}
