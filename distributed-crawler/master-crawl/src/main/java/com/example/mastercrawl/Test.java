package com.example.mastercrawl;

import com.example.mastercrawl.consistentHash.ConsistentHash;
import com.example.mastercrawl.consistentHash.HashFunc;
import com.example.mastercrawl.consistentHash.ServerNode;
import org.graalvm.compiler.lir.hashing.HashFunction;


import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {



        public void test(){

            HashSet<ServerNode> serverNodeHashSet = new HashSet<ServerNode>();
            ServerNode s1 = new ServerNode("192.168.1.1",9001);
            ServerNode s2 = new ServerNode("192.168.1.2",9002);
            ServerNode s3 = new ServerNode("192.168.1.3",9003);
            ServerNode s4 = new ServerNode("192.168.1.4",9004);

            serverNodeHashSet.add(s1);

            serverNodeHashSet = Stream.of(s1,s2,s3,s4).collect(Collectors.toCollection(HashSet::new));


            ConsistentHash<ServerNode> consistentHash = new ConsistentHash<ServerNode>(new HashFunc(), 1000, serverNodeHashSet);


//		for (int i = 0; i < 50; i++) {
//            System.out.println(consistentHash.get("192.168.1." + i).getIp());
//        }
//		consistentHash.remove(new ServerNode("192.168.1.1",9001));


        }



}
