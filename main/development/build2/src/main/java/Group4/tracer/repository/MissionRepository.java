package Group4.tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, String> {
    //Searching for all stages by product ID
    @Query(value = "SELECT * " +
            "FROM public.\"questMission\"", nativeQuery = true)
    Object[] findMissionArray();
}

