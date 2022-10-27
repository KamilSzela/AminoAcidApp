package pl.aminoacidswebapp.szelakamil.adapters;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.aminoacidswebapp.szelakamil.model.Requirement;
import pl.aminoacidswebapp.szelakamil.model.RequirementRepository;

@Repository
public interface SqlRequirementRepository extends RequirementRepository, JpaRepository<Requirement, Integer> {
}
