package com.easybyts.chats.controller.User.GetUsers.DTO;

import java.util.List;

public class GetUsersResponse {
    private String status;
    private List<GetUsers> users;

    public GetUsersResponse(String status, List<GetUsers> users) {
        this.status = status;
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetUsers> getUsers() {
        return users;
    }

    public void setUsers(List<GetUsers> users) {
        this.users = users;
    }
}
