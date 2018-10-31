package fpt.oceanstudy.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import fpt.oceanstudy.biz.FishBiz;
import fpt.oceanstudy.entity.OsFish;
import fpt.oceanstudy.repository.FishRepository;

@Service
public class FishBizImpl implements FishBiz {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(FishBizImpl.class);

    @Autowired
    FishRepository fishRepository;
    
    @Override
    public boolean updateFish(Iterable<OsFish> listFish, List<Integer> tobeDeletedIds) {
        if (tobeDeletedIds != null) {
            tobeDeletedIds.forEach(deleteId -> {
                fishRepository.deleteById(deleteId);
                ;
            });
        }

        fishRepository.saveAll(listFish);

        return true;
    }

    @Override
    public CrudRepository<OsFish, Integer> getRepo() {
        // TODO Auto-generated method stub
        return fishRepository;
    }
}
