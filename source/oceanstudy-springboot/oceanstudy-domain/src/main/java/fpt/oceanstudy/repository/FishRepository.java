package fpt.oceanstudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fpt.oceanstudy.dto.Fish;
import fpt.oceanstudy.entity.OsFish;

public interface FishRepository extends CrudRepository<OsFish, Integer>{
    @Query(nativeQuery = true, value = "SELECT id, name, weight, length, height, deep, age, img, video FROM oceanstudy.os_fish\r\n" + 
            "order by deep asc")
    List<OsFish> sortByDeep();
    
    @Query(nativeQuery = true, value = "SELECT f.id, f.name, f.weight, f.length, f.height, f.deep, f.age, f.img, f.video, fs.status \r\n" + 
            "FROM oceanstudy.os_fish f\r\n" + 
            "INNER JOIN os_fish_status fs on fs.id = f.status\r\n" + 
            "ORDER BY deep asc")
    List<Object[]> sortFishByDeep();
}
