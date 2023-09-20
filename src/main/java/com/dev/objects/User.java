
package com.dev.objects;

import javax.persistence.*;

    @Entity
    @Table(name = "my_user")
    public class User{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private int id;
        @Column
        private String username;
        @Column
        private String token;
        @Column
        private String firstName;
        @Column
        private String lastName;
        @Column
        private String socialSecurityNumber;
        @Column
        private String address;
        @Column
        private String phoneNumber;

        @Column
        private int admin;

        public User(String username, String token, String firstName, String lastName, String socialSecurityNumber, String address, int admin) {
            this.username = username;
            this.token = token;
            this.firstName = firstName;
            this.lastName = lastName;
            this.socialSecurityNumber = socialSecurityNumber;
            this.address = address;
            this.phoneNumber = "0";
            this.admin = admin;
        }



        public User() {

        }

        public User(String email, String token, int admin) {
            this.username = email;
            this.token = token;
            this.firstName = "";
            this.lastName = "";
            this.socialSecurityNumber = "";
            this.address = "";
            this.phoneNumber = "0";
            this.admin = admin;

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String email) {
            this.username = email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getSocialSecurityNumber() {
            return socialSecurityNumber;
        }

        public void setSocialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = "0";
        }

        public int getAdmin() {
            return admin;
        }

        public void setAdmin(int admin) {
            admin = admin;
        }
    }
