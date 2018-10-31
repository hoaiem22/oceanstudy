package fpt.oceanstudy.biz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fpt.oceanstudy.entity.OsFish;

public interface FishBiz{
    boolean updateFish(Iterable<OsFish> report, List<Integer> tobeDeletedIds);

    CrudRepository<OsFish, Integer> getRepo();
}
