package com.wanxb.guava.collect;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.Collection;

/**
 * @author wanxianbo
 * @description Guava的Multimap就提供了一个方便地把一个键对应到多个值的数据结构
 * @date 2022/7/10 23:05
 */
public class MultimapDemo {

    @Test
    void testStuScoreMultimap() {
        Multimap<String, StudentScore> scoreMultimap = ArrayListMultimap.create();

        for (int i = 0; i < 20; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.setCourseId(1001 + i);
            studentScore.setScore(100 - i);
            scoreMultimap.put("peida", studentScore);
        }

//        scoreMultimap.forEach((k, v) -> System.out.println("key:" + k + "\tvalues:" + v));
        System.out.println(scoreMultimap.keys());
        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.keySet().size());
//        System.out.println(scoreMultimap.get("peida"));

        Collection<StudentScore> studentScores = scoreMultimap.get("peida");
        StudentScore studentScore = new StudentScore();
        studentScore.setCourseId(2000);
        studentScore.setScore(100);
        studentScores.add(studentScore);

        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.keys());

        StudentScore studentScore2=new StudentScore();
        studentScore2.setCourseId(1045);
        studentScore2.setScore(56);
        scoreMultimap.put("jerry",studentScore2);

        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.keys());

        scoreMultimap.put("harry",studentScore2);
        scoreMultimap.removeAll("harry");
        System.out.println("scoreMultimap:"+scoreMultimap.size());
        System.out.println("scoreMultimap:"+scoreMultimap.get("harry"));
    }

    @Data
    static class StudentScore {
        private int courseId;
        private int score;
    }

}
