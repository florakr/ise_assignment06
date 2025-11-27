package de.seuhd.campuscoffee.domain.model;

import lombok.Builder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Domain record that stores the user metadata.
 * This is an immutable value object - use the builder or toBuilder() to create modified copies.
 * Records provide automatic implementations of equals(), hashCode(), toString(), and accessors.
 * <p>
 * We validate the fields in the API layer based on the DTOs, so no validation annotations are needed here.
 *
 * @param id            the unique identifier; null when the user has not been created yet
 * @param createdAt     timestamp set on user creation
 * @param updatedAt     timestamp set on user creation and update
 * @param loginName     the login name of the user
 * @param emailAddress  the email address of the user
 * @param firstName     the first name of the user
 * @param lastName      the last name of the user
 */

@Builder(toBuilder = true)
public record User (
        @Nullable Long id, // id is null when creating a new user
        @Nullable LocalDateTime createdAt, // is null when using DTO to create a new user
        @Nullable LocalDateTime updatedAt, // is set when creating or updating a user
        @NonNull String loginName,
        @NonNull String emailAddress,
        @NonNull String firstName,
        @NonNull String lastName

) implements Serializable { // serializable to allow cloning (see TestFixtures class).
    @Serial
    private static final long serialVersionUID = 1L;
}
