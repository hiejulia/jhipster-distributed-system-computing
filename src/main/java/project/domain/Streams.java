package project.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Streams {

    private long id;

    private long userId;
    private long contentId;
    private Date streamDate;
    private Date streamTime;
    private long streamLength;


}
