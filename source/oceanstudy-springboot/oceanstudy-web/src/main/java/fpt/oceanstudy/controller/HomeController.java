package fpt.oceanstudy.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import fpt.oceanstudy.biz.FishBiz;
import fpt.oceanstudy.entity.OsFish;
import fpt.oceanstudy.model.FishModel;
import fpt.oceanstudy.repository.FishRepository;
import fpt.oceanstudy.util.AppUtil;

@Controller
@ComponentScan(value = "fpt.oceanstudy.biz")
public class HomeController {

    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    FishRepository fishRepository;
    
    @Autowired
    FishBiz fishBiz;
    
    @GetMapping({"/","/soon"})
    public String home() {
        return "soon";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/index")
    public String index() {
        
        return "index";
    }
    
    @ResponseBody
    @GetMapping("/loadFish")
    public List<OsFish> loadFish() {
        return (List<OsFish>) fishRepository.findAll();
    }
    
    
    @PostMapping("/saveFish")
    @ResponseBody
    public List<OsFish> saveTeam(@Valid @RequestBody FishModel data, Errors errors,
            HttpServletRequest request) {
        LOG.info("save Fish....");

        // If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            LOG.error(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            LOG.info("step1");
            return null;
        } else {

            Iterable<OsFish> entities = AppUtil.parseFish(data);

            fishBiz.updateFish(entities, data.getDeletedIds());
        }

        // Reload data from db
        List<OsFish> fish = (List<OsFish>) fishRepository.findAll();

        return fish;

    }
}
