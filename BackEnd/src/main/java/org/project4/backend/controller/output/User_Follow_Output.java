package org.project4.backend.controller.output;

import org.project4.backend.dto.Notification_DTO;
import org.project4.backend.dto.User_Follow_DTO;

import java.util.ArrayList;
import java.util.List;

public class User_Follow_Output {
    private int page;
    private int totalPage;
    private List<User_Follow_DTO> listResult = new ArrayList<>();

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<User_Follow_DTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<User_Follow_DTO> listResult) {
        this.listResult = listResult;
    }
}
