package org.project4.backend.controller.output;

import org.project4.backend.dto.Comment_Episode_DTO;
import org.project4.backend.dto.Comment_Movie_DTO;

import java.util.ArrayList;
import java.util.List;

public class Comment_Episode_Output {
    private int page;
    private int totalPage;
    private List<Comment_Episode_DTO> listResult = new ArrayList<>();

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

    public List<Comment_Episode_DTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<Comment_Episode_DTO> listResult) {
        this.listResult = listResult;
    }
}
