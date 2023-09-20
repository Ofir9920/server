//package com.dev.controllers;
//
//import com.dev.objects.*;
//import com.dev.responses.*;
//import com.dev.utils.Constants;
//import com.dev.utils.Persist;
//import com.dev.utils.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.dev.objects.User;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//@RestController
//public class AdminController {
//    @Autowired
//    private Utils utils;
//
//    @Autowired
//    private Persist persist;
//    @RequestMapping(value = "/edit-users-creds", method = {RequestMethod.GET, RequestMethod.POST})
//    public BasicResponse editUserCred(String token , int credChange) {
//        BasicResponse basicResponse=null;
//        persist.updateCredByAdmin(token , credChange);
//        basicResponse = new BasicResponse(true , null);
//         return basicResponse;
//    }
//
//    @RequestMapping(value = "/get-total-bank", method = {RequestMethod.GET, RequestMethod.POST})
//    public BasicResponse getTotalOffers () {
//        AdminSys adminSys = persist.getById(1);
//        AllOfferResponse allOfferResponse = new AllOfferResponse(true,null,adminSys.getTotalBank());
//        return allOfferResponse;
//    }
//
//}
