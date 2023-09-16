package com.finzly.bookmanager.transformer;

import com.finzly.bookmanager.database.entity.User;
import com.finzly.bookmanager.models.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserDataTransformer {
    User userPojoToEntity(UserDetails userDetails);
    UserDetails userEntityToPojo(User user);
    User toUserEntity(@MappingTarget User userFromDB, UserDetails userDetails);
}
