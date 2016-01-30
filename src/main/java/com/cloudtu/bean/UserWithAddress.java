package com.cloudtu.bean;

import java.util.List;

public class UserWithAddress {
    User user;
    List<Address> addresses;

    public UserWithAddress(User user, List<Address> addresses) {
        this.user = user;
        this.addresses = addresses;
    }

    public User getUser() {
        return user;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
