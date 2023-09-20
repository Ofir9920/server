package com.dev.responses;

import com.dev.objects.User;
import java.util.List;

public class AllUserResponse extends BasicResponse{

    private List<User> users;

    public AllUserResponse(List<User> users) {
        this.users = users;
    }

    public AllUserResponse(boolean success, Integer errorCode, List<User> users) {
        super(success, errorCode);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
