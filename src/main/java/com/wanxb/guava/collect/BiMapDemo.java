package com.wanxb.guava.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;

/**
 * @author wanxianbo
 * @description BiMap提供了一种新的集合类型，它提供了key和value的双向关联的数据结构。
 * @date 2022/7/11 0:08
 */
public class BiMapDemo {

    /**
     * Bimap数据的强制唯一性
     */
    @Test
    void BimapTest() {
        BiMap<Integer,String> logfileMap = HashBiMap.create();
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");
        System.out.println("logfileMap:"+logfileMap);
        // inverse方法会返回一个反转的BiMap，但是注意这个反转的map不是新的map对象，它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);

        logfileMap.put(4, "d.log");
        // IllegalArgumentException("value already present: " + value);
//        logfileMap.put(5,"d.log");
        // 使用 forcePut() 但是我们需要注意的是前面的key也会被覆盖了。
        logfileMap.forcePut(5, "d.log");
        System.out.println("logfileMap:" + logfileMap);
        System.out.println("filelogMap:" + filelogMap);
    }
}
