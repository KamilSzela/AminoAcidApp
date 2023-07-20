package pl.aminoacidswebapp.szelakamil.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aminoacidswebapp.szelakamil.model.User;
import pl.aminoacidswebapp.szelakamil.model.UserRepository;

@Repository
public interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
