package com.wanxb.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Duration;

/**
 * @author wanxianbo
 * @description Guava Cache有两种创建方式：cacheLoader
 * @date 2022/7/9 23:00
 */
public class CacheLoaderDemo {

    public static void main(String[] args) {
        LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(10))
                .maximumSize(100)
                .build(CacheLoader.from(key -> "hello" + key));

        System.out.println(cacheBuilder.getUnchecked("jerry"));
        System.out.println(cacheBuilder.getUnchecked("peida"));

        cacheBuilder.put("harry", "ssdded");
        System.out.println(cacheBuilder.getUnchecked("harry"));
    }
}
