package com.wanxb.guava.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author wanxianbo
 * @description
 * @date 2022/7/10 23:53
 */
public class ImmutableCollectionDemo {
    /**
     * 为什么要用immutable对象？immutable对象有以下的优点：
     *
     * 对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
     * 线程安全的：immutable对象在多线程下安全，没有竞态条件
     * 不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
     * 可以被使用为一个常量，并且期望在未来也是保持不变的
     * immutable对象可以很自然地用作常量，因为它们天生就是不可变的对于immutable对象的运用来说，它是一个很好的防御编程（defensive programming）的技术实践。
     */

    /**
     * JDK中实现immutable集合
     */
    @Test
    void testJDKImmutable() {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);

        List<String> unmodifiableList= Collections.unmodifiableList(list);

        System.out.println(unmodifiableList);

        List<String> unmodifiableList1=Collections.unmodifiableList(Arrays.asList("a","b","c"));
        System.out.println(unmodifiableList1);

        String temp=unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]："+temp);

        list.add("baby");
        System.out.println("list add a item after list:"+list);
        System.out.println("list add a item after unmodifiableList:"+unmodifiableList);

        unmodifiableList1.add("bb");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList1);

        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList);

        // 说明：Collections.unmodifiableList实现的不是真正的不可变集合，当原始集合修改后，不可变集合也发生变化。
        // 不可变集合不可以修改集合数据，当强制修改时会报错，实例中的最后两个add会直接抛出不可修改的错误。
    }


    /**
     * 一个immutable集合可以有以下几种方式来创建：
     *
     * 用copyOf方法, 譬如, ImmutableSet.copyOf(set)
     * 使用of方法，譬如，ImmutableSet.of("a", "b", "c")或者ImmutableMap.of("a", 1, "b", 2)
     * 使用Builder类
     */
    @Test
    void testGuavaImmutable() {
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list：" + list);

        ImmutableList<String> immutableList = ImmutableList.copyOf(list);
        System.out.println("immutableList:" + immutableList);

        ImmutableList<String> imOflist = ImmutableList.of("peida", "jerry", "harry");
        System.out.println("imOflist:" + imOflist);

        ImmutableSortedSet<String> imSortList=ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println("imSortList："+imSortList);

        list.add("baby");
        System.out.println("list add a item after list:"+list);
        System.out.println("list add a item after imlist:"+immutableList);

        ImmutableSet<Color> imColorSet =
                ImmutableSet.<Color>builder()
                        .add(new Color(0, 255, 255))
                        .add(new Color(0, 191, 255))
                        .build();

        System.out.println("imColorSet:"+imColorSet);

    }
}
