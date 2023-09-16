package com.finzly.bookmanager.service;

import com.finzly.bookmanager.models.UserDetails;
import com.finzly.bookmanager.models.UserRetrieveRequest;
import org.springframework.data.domain.Page;

public interface IUserService {

    Page<UserDetails> queryUsers(UserRetrieveRequest userRetrieveRequest);
    void createUser(UserDetails userDetails);
    void updateUser(UserDetails userDetails);
}
