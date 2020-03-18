package com.example.mastercrawl.consistentHash;


public class HashFunc {

    int hash(Object key) {
        return MD5.md5(key.toString()).hashCode();
    }
}
