package pl.mateusz.Registration.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.Registration.models.CountryModel;

public interface CountryRepository extends JpaRepository<CountryModel, Long> {
}
