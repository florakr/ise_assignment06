package de.seuhd.campuscoffee.domain.ports;

import de.seuhd.campuscoffee.domain.model.User;
import org.jspecify.annotations.NonNull;
import java.util.List;


public interface UserService {

    /**
     * Clears all user data from the system.
     * This is typically used for testing or administrative purposes.
     * Warning: This operation is destructive and cannot be undone.
     */
    void clear();

    /**
     * Retrieves all users from the system.
     *
     * @return a list of all users in the system; never null, but may be empty if no users exist
     */
    @NonNull List<User> getAll();

    /**
     * Retrieves a specific user by their unique identifier.
     *
     * @param id the unique identifier of the user to retrieve; must not be null
     * @return the user with the specified ID; never null
     */
    @NonNull User getById(@NonNull Long id);

    /**
     * Retrieves a specific user by their unique login name.
     *
     * @param loginName the unique login name of the user to retrieve; must not be null
     * @return the user with the specified login name; never null
     */
    @NonNull User getByLoginName(@NonNull String loginName);

    /**
     * Creates a new user or updates an existing one.
     * This method performs an "upsert" operation:
     * <ul>
     *   <li>If the user has no ID (null), a new user is created</li>
     *   <li>If the user has an ID, and it exists, the existing user is updated</li>
     * </ul>
     *
     * @param user the user to create or update; must not be null
     * @return the persisted user entity with updated timestamps and ID; never null
     */
    @NonNull User upsert(@NonNull User user);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete; must not be null
     */

    void delete(@NonNull Long id);

}
