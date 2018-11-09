package fev.management.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fev.management.model.Fish;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/fish")
public class WebController {

    
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fish>> getAllStudent() {
        Jedis jedis = new Jedis("127.0.0.1");
        ObjectMapper mapper = new ObjectMapper();
        String json = jedis.get("fish-list");
        System.out.println("get all fish: " + json);
        List<Fish> vms = null;
        try {
            vms = mapper.readValue(json, new TypeReference<List<Fish>>() {
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<List<Fish>>(vms, HttpStatus.OK);
    }
}
