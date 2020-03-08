package project.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Content {
    private long id;
    private String title;
    private String episode;
    private String genre;
    private long timeLength;
    private String distributor;
    private Date releaseDate;

}
