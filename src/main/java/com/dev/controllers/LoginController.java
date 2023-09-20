package com.dev.controllers;


import com.dev.objects.User;

import com.dev.objects.User;
import com.dev.responses.*;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.dev.utils.Errors.*;
import static com.dev.utils.Errors.ERROR_USERNAME_ALREADY_EXISTS;

@RestController
public class LoginController {

    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;

    @RequestMapping(value = "sign-up")
    public BasicResponse signUp (String username, String password, String firstName, String lastName, String socialSecurityNumber, String address, int admin) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                if (utils.isStrongPassword(password)) {
                    User fromDb = persist.getUserByUsername(username);
                    if (fromDb == null) {
                        User toAdd = new User(username, utils.createHash(username, password), firstName, lastName, socialSecurityNumber, address, admin);
                        persist.saveUser(toAdd);
                    } else {
                        errorCode = ERROR_USERNAME_ALREADY_EXISTS;
                    }
                } else {
                    errorCode = ERROR_WEAK_PASSWORD;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }


    @RequestMapping(value = "sign-up-admin" ,method = { RequestMethod.POST})
    public BasicResponse signUpAdmin (String username, String password , int admin) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                if (utils.isStrongPassword(password)) {
                    User fromDb = persist.getUserByUsername(username);
                    if (fromDb == null) {
                        User userToAdd = new User(username , utils.createHash(username, password),admin);
                        persist.saveUser(userToAdd);
                        success = true;
                    } else {
                        errorCode = ERROR_USERNAME_ALREADY_EXISTS;
                    }
                } else {
                    errorCode = ERROR_WEAK_PASSWORD;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

    @RequestMapping(value = "login")
    public BasicResponse login (String username, String password) {
        BasicResponse basicResponse = new BasicResponse();
        boolean success = false;
        Integer errorCode = null;
        if (username != null) {
            if (password != null) {
                String token = utils.createHash(username, password);
                User fromDb = persist.getUserByUsernameAndToken(username, token);
                if (fromDb != null) {
                    success = true;
                    basicResponse = new LoginResponse(token);
                } else {
                    errorCode = ERROR_WRONG_LOGIN_CREDS;
                }
            } else {
                errorCode = ERROR_MISSING_PASSWORD;
            }
        } else {
            errorCode = ERROR_MISSING_USERNAME;
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

//    @RequestMapping(value = "/get-all-users", method = {RequestMethod.GET, RequestMethod.POST})
//    public BasicResponse getAllUsers () {
//        List<User> allUsers = persist.getAllUsers();
//        AllUsersResponse allUsersResponse = new AllUsersResponse(true,null,allUsers);
//        return allUsersResponse;
//    }
//
//    @RequestMapping(value = "/get-all-tenders", method = {RequestMethod.GET, RequestMethod.POST})
//    public BasicResponse getAllTenders() {
//        List<Item> items = persist.getAllItems();
//        int max;
//        AllTenderResponse allTenderResponse= new AllTenderResponse(items);
//        List<ItemModel> list = allTenderResponse.getItemsList();
//        for (ItemModel itemModel:list) {
//            max = persist.getOffersById(itemModel.getId());
//            itemModel.setBigOffer(max);
//        }
//        AllTenderResponse allTenderResponse1 = new AllTenderResponse(true,null,list);
//        return allTenderResponse1;
//    }
//
//    @RequestMapping(value = "/get-all-offers", method = {RequestMethod.GET, RequestMethod.POST})
//    public BasicResponse getAllOffers () {
//        List<Offer> offers = persist.getAllOffers();
//        AllOfferResponse allOfferResponse = new AllOfferResponse(true,null,offers);
//        return allOfferResponse;
//    }
//
//
//    @RequestMapping(value = "/get-userCred", method = {RequestMethod.GET, RequestMethod.POST})
//    public User getUserCred (String token) {
//        User user = persist.getUserByToken(token);
//        return user;
//    }
//
//    @RequestMapping(value = "/check-email", method = {RequestMethod.GET, RequestMethod.POST})
//    public Employee getEmail (String email) {
//        Employee user = persist.getUserByUsername(email);
//        return user;
//    }
//    @RequestMapping(value = "/login-admin", method = {RequestMethod.GET, RequestMethod.POST})
//    public Employee getLogin (String email, String password) {
//        String token = utils.createHash(email,password);
//        Employee user = persist.getUserByToken(token);
//        return user;
//    }
}
