package com.project.cacheserver.controller;


import com.project.cacheserver.service.CacheInitService;
import com.project.cacheserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cache")
public class CacheController {

    public static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    UserService userService;

    @Autowired
    CacheInitService cacheInitService;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> readUser(@PathVariable("id") String id) {
        String user = userService.readUser(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable("id") String id, @RequestParam(value = "userjson") String user) {
        try {
            userService.addUser(id,user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@PathVariable("id") String id) {
        try {
            if(userService.removeUser(id) ) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/initialize/", method = RequestMethod.POST)
    public ResponseEntity<?> initializeCache(@RequestParam(value = "allrecords") String allRecords) {
        try {
            cacheInitService.clearCache();
            cacheInitService.initializeCache(allRecords);
            logger.info("Successfully initialized cache.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Failed to initialize cache.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
