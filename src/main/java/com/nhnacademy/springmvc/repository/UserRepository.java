package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.User;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(String id, String password);

    User getUser(String id);

    User addUser(String id, String password);

    // TODO #7: 이름 추가
    User addUser(String id, String password, int age, String name);

    // TODO #13: 수정용 API 추가
    void modify(User user);

}
