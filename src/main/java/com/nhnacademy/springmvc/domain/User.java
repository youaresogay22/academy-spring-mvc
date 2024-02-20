package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    String id;

    @Getter
    String password;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private String name;

    public static User create(String id, String password) {
        return new User(id, password);
    }

    private User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public User() {
    }

    private static final String MASK = "*****";

    public static User constructPasswordMaskedUser(User user) {
        User newUser = User.create(user.getId(), MASK);
        newUser.setAge(user.getAge());
        newUser.setName(user.getName());

        return newUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
