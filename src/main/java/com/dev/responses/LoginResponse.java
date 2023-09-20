package com.dev.responses;



public class LoginResponse  extends BasicResponse{

    private String firstname;
    private String username;
    private int admin;
    private String token;


    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse(boolean success, Integer errorCode, String username,String firstname ,int admin, String token) {
        super(success, errorCode);
        this.firstname = firstname;
        this.username= username;
        this.admin = admin;
        this.token = token;

    }

    public LoginResponse( String username,String firstname , int admin ) {
        this.firstname = firstname;
        this.username=username;
        this.admin = admin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
