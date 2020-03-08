package project.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.domain.Video;
import project.service.VideoService;
import javax.jms.JMSException;
import java.util.List;


@RestController
@RequestMapping("/videos")
public class VideoRestController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<?> createTreatment(@RequestBody Video video) {


        if (videoService.add(video)) {
            try {
                // Producer send
                producer.sendMessage(video.getId(), "Add video");
            } catch (JMSException e) {

            }
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {

        List<Video> videos = videoService.get(id);
        return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Video video) {

        if (videoService.update(video)) {
            try {
                producer.sendMessage(video.getId(), "Treatment Information Update");
            } catch (JMSException e) {
                LOG.error(e.getMessage());
            }
            return new ResponseEntity<Video>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<Video>(HttpStatus.NOT_ACCEPTABLE);
    }



}
