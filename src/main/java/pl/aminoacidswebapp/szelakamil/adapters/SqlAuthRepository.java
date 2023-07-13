package pl.aminoacidswebapp.szelakamil.adapters;

import org.springframework.data.repository.CrudRepository;
import pl.aminoacidswebapp.szelakamil.model.AuthRepository;
import pl.aminoacidswebapp.szelakamil.model.Authority;

public interface SqlAuthRepository extends AuthRepository, CrudRepository <Authority, Integer> {
}
