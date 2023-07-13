package pl.aminoacidswebapp.szelakamil.model;

import java.util.List;

public interface UserRepository {
    List<User> findByEmail(String email);
    User save(User user);
}
