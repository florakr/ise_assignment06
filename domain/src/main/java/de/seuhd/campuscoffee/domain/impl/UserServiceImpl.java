package de.seuhd.campuscoffee.domain.impl;

import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.ports.UserService;
import de.seuhd.campuscoffee.domain.ports.UserDataService;


import de.seuhd.campuscoffee.domain.exceptions.DuplicationException;
import de.seuhd.campuscoffee.domain.exceptions.MissingFieldException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;

 /**
  * Implementation of the User service that handles business logic related to User entities.
  */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDataService userDataService;

    @Override
    public void clear() {
        log.warn("Clearing all user data");
        userDataService.clear();
    }

    @Override
    public @NonNull List<User> getAll() {
        log.debug("Retrieving all users");
        return userDataService.getAll();
    }

    @Override
    public @NonNull User getById(@NonNull Long id) {
        log.debug("Retrieving user with ID: {}", id);
        return userDataService.getById(id);
    }

    @Override
    public @NonNull User getByLoginName(@NonNull String loginName) {
        log.debug("Retrieving user with login name: {}", loginName);
        return userDataService.getByLoginName(loginName);
    }

    @Override
    public @NonNull User upsert(@NonNull User user) {
        if (user.id() == null) {
            // create a new user
            log.info("Creating new user: {}", user.loginName());
        } else {
            // update existing user
            log.info("Updating user with ID: {}", user.id());
            // user ID must be set
            Objects.requireNonNull(user.id());
            // user must exist in the database before the update
            userDataService.getById(user.id());
        }
        return performUpsert(user);
    }

    @Override
    public void delete(@NonNull Long id) {
        log.info("Trying to delete user witj ID: {}", id);
        userDataService.delete(id);
        log.info("Deleted user with ID: {}", id);
    }

    private @NonNull User performUpsert(User user) {
        try {
            User upsertedUser = userDataService.upsert(user);
            log.info("Successfully upserted user with ID: {}", upsertedUser.id());
            return upsertedUser;
        } catch (DuplicationException e) {
            log.error("Error upserting user '{}': {}", user.loginName(), e.getMessage());
            throw e;
        }
    }
}
