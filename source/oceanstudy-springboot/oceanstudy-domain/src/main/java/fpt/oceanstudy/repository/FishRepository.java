package fpt.oceanstudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fpt.oceanstudy.entity.OsFish;

public interface FishRepository extends CrudRepository<OsFish, Integer>{

    @Query(nativeQuery = true, value = "SELECT id, name, weight, length, height, deep, age, img, video FROM oceanstudy.os_fish\r\n" + 
            "order by deep asc")
    List<OsFish> sortByDeep();
}
