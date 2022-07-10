package com.wanxb.guava.collect;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wanxianbo
 * @description Multiset占据了List和Set之间的一个灰色地带：允许重复，但是不保证顺序。
 * @date 2022/7/10 23:27
 */
public class MultisetDemo {

    /**
     * 常见使用场景：Multiset有一个有用的功能，就是跟踪每种对象的数量，所以你可以用来进行数字统计
     */
    @Test
    void testWordCount() {
        String strWorld="wer|dffd|ddsa|dfd|dreg|de|dr|ce|ghrt|cf|gt|ser|tg|ghrt|cf|gt|" +
                "ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr|wer|dffd|ddsa|dfd|dreg|de|dr|" +
                "ce|ghrt|cf|gt|ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr";
        String[] words = strWorld.split("\\|");
        // stream 计数
        Map<String, Long> wordCountMap = Arrays.stream(words).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        // 使用Map进行计数
        Map<String, Integer> countMap = Maps.newHashMap();
        for (String word : words) {
            Integer count = countMap.get(word);
            if (Objects.isNull(count)) {
                countMap.put(word, 1);
            } else {
                countMap.put(word, count + 1);
            }
        }

        // 使用Multiset 来计数
        HashMultiset<String> wordMultiset = HashMultiset.create();
        List<String> wordList = Lists.newArrayList();
        Collections.addAll(wordList, words);
        wordMultiset.addAll(wordList);

        // stream 计数 遍历
        System.out.println("stream 计数 遍历=========================================");
        wordCountMap.forEach((k, v) -> System.out.println(k + " count: " + v));
        // 普通map 遍历
        System.out.println("普通map 遍历========================================");
        countMap.forEach((k, v) -> System.out.println(k + " count:" + v));
        // Multiset 计数遍历
        System.out.println("Multiset 计数遍历========================================");
        wordMultiset.forEachEntry((k, v) -> System.out.println(k + " count:" + v));
    }
}
