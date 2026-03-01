package Group4.tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.inputShares;

@Repository
public interface InputSharesRepository extends JpaRepository<inputShares, String> {

    //Search for the inputSharesId
    //This is the SQL query that is run with each inputSharesId search
    @Query(value = "SELECT * " +
            "FROM public.\"inputShares\" " +
            "WHERE input_shares_id = :inputSharesId " +
            "ORDER BY input_shares_id ASC", nativeQuery = true)

    //findInputSharesArray takes an inputSharesId string and returns an array of input shares information
    Object[] findInputSharesArray(@Param("productId") String inputSharesId);

}