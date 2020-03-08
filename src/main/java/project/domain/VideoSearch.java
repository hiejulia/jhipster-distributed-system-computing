package project.domain;

import lombok.Data;

@Data
public class VideoSearch {

    private String ip;
    private String searchTerm;
    private long userId;

}
