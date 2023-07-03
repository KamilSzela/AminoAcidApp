package pl.aminoacidswebapp.szelakamil.model;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
