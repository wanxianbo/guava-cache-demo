package com.wanxb.guava.cache;

import com.google.common.cache.*;
import com.google.common.util.concurrent.Callables;

import java.time.Duration;
import java.util.concurrent.Executors;

/**
 * @author wanxianbo
 * @description
 * @date 2022/7/10 16:13
 */
public class CallableDemo {
    /**
     * 回收的参数：
     *
     * 大小的设置：CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
     * 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
     * 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
     * 明确的删除：invalidate(key)  invalidateAll(keys)  invalidateAll()
     * 删除监听器：CacheBuilder.removalListener(RemovalListener)
     * refresh机制：
     *
     * LoadingCache.refresh(K) 在生成新的value的时候，旧的value依然会被使用。
     * CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value
     * CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache
     * @param args agrs
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        // 主动移除数据方式，主动移除有三种方法：
        // 单独移除用 Cache.invalidate(key)
        // 批量移除用 Cache.invalidateAll(keys)
        // 移除所有用 Cache.invalidateAll()
        // 默认Removal Listener中的行为是和移除动作同步执行的
        // 如果需要改成异步形式，可以考虑使用RemovalListeners.asynchronous(RemovalListener, Executor)
        Cache<Object, String> cache = CacheBuilder.newBuilder().maximumSize(10)
                .expireAfterWrite(Duration.ofSeconds(10))
                .removalListener(RemovalListeners.asynchronous(notification -> System.out.println(notification.getKey() + "被移除了"), Executors.newFixedThreadPool(10)))
                .build();

        String jerry = cache.get("jerry", Callables.returning("hello" + "jerry!"));
        System.out.println(jerry);
        String peida = cache.get("peida", Callables.returning("hello" + "peida!"));
        System.out.println(peida);
    }
}
