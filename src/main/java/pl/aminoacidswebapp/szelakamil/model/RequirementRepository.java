package pl.aminoacidswebapp.szelakamil.model;

import java.util.List;
import java.util.Optional;

public interface RequirementRepository {
    Optional<Requirement> findByid(Integer id);
    List<Requirement> findAll();
}
