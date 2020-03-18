package com.example.mastercrawl.consistentHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// Server node

@AllArgsConstructor
@Data
public class ServerNode{

    private String ip;
    private int port;

}
