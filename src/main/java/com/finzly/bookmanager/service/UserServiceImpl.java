package com.finzly.bookmanager.service;

import com.finzly.bookmanager.database.entity.User;
import com.finzly.bookmanager.database.repo.UserRepository;
import com.finzly.bookmanager.exceptions.UsersNotAvailableException;
import com.finzly.bookmanager.models.UserDetails;
import com.finzly.bookmanager.models.UserRetrieveRequest;
import com.finzly.bookmanager.transformer.UserDataTransformer;
import jakarta.persistence.criteria.Predicate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserDataTransformer userDataTransformer;

    @Override
    public Page<UserDetails> queryUsers(UserRetrieveRequest userRetrieveRequest) {

        Page<User> users = userRepository.findAll(createSpecification(userRetrieveRequest),
                PageRequest.of(userRetrieveRequest.getPage(), userRetrieveRequest.getSize()));
        if(Objects.isNull(users)){
            throw new UsersNotAvailableException("User Not available");
        }
        return users.map(userDataTransformer::userEntityToPojo);
    }

    @Override
    public void createUser(UserDetails userDetails) {
        final User user = userDataTransformer.userPojoToEntity(userDetails);
        if (Objects.isNull(user)) {
            //log.info("user Entity Cannot Be Null");
            throw new IllegalArgumentException("user Entity Cannot Be Null");
        }
        userRepository.save(user);
    }


    @Override
    public void updateUser(UserDetails userDetails) {
        if (Objects.isNull(userDetails) || Objects.isNull(userDetails.getId())) {
            //log.info("User Entity Or user Id Cannot Be Null");
            throw new IllegalArgumentException("User Entity Or User Id Cannot Be Null");
        }
        final User userFromDB = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("Matching user details not found in db"));
        userDataTransformer.toUserEntity(userFromDB, userDetails);
        userRepository.save(userFromDB);
    }

    private Specification<User> createSpecification(@NonNull final UserRetrieveRequest userRetrieveRequest) {
        return (root, query, criteriaBuilder) -> {
            Predicate userPredicate = criteriaBuilder.conjunction();

            if (StringUtils.isNoneEmpty(userRetrieveRequest.getUserName())) {
                userPredicate = criteriaBuilder.and(userPredicate,
                        criteriaBuilder.equal(root.get("userName"), userRetrieveRequest.getUserName()));
            }
            if (Objects.nonNull(userRetrieveRequest.getId())) {
                userPredicate = criteriaBuilder.and(userPredicate,
                        criteriaBuilder.equal(root.get("id"), userRetrieveRequest.getId()));
            }
            return userPredicate;
        };
    }

}
