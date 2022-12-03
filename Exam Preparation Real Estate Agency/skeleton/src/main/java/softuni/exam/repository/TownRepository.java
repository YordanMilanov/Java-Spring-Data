package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Town;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    @Query("SELECT t FROM Town t WHERE t.townName = :name")
    Optional<Town> findByTownName(String name);
}