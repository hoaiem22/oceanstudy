package fpt.oceanstudy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fpt.oceanstudy.entity.OsFish;
import fpt.oceanstudy.entity.OsFishStatus;

public interface FishStatusRepository extends CrudRepository<OsFish, Integer>{
    
    @Query("SELECT d FROM OsFishStatus d WHERE d.status = :name")
    public OsFishStatus findByName(@Param("name") String name);
    
}
