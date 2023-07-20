package pl.aminoacidswebapp.szelakamil.model;

import java.util.Optional;

public interface AuthRepository {
    Optional<Authority> findByUserId(int userId);
    Authority save(Authority auth);
}
