package org.project4.backend.controller.output;

import org.project4.backend.dto.Movie_DTO;

import java.util.ArrayList;
import java.util.List;

public class Movie_output {
    private int page;
    private int totalPage;
    private List<Movie_DTO> listResult = new ArrayList<>();

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

    public List<Movie_DTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<Movie_DTO> listResult) {
        this.listResult = listResult;
    }
}
