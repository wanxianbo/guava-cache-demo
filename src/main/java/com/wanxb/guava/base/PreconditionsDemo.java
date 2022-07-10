package com.wanxb.guava.base;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wanxianbo
 * @description
 * @date 2022/7/10 17:16
 */
public class PreconditionsDemo {
    public static void main(String[] args) {
//        User user = null;
//        Preconditions.checkNotNull(user, "user不能为null");
        User user = new User();
        user.setId(1L);
        user.setName("Tom");
        user.setAge(0);
        Preconditions.checkNotNull(user.getName(), "name不能为空");
//        Preconditions.checkArgument(user.getAge() > 0 && user.getAge() < 200, "年龄不得小于0岁与大于200岁");

        List<User> userList = Lists.newArrayList();
        userList.add(new User(2L, "a", 10));
        userList.add(new User(3L, "b", 10));
        userList.add(new User(4L, "c", 10));
        Preconditions.checkElementIndex(1, userList.size(), "index " + 1 + " 不在 list中， List size为：" + userList.size());
        Preconditions.checkPositionIndex(1, userList.size(), "index " + 1 + " 不在 list中， List size为：" + userList.size());
        Preconditions.checkPositionIndexes(1, 4, userList.size());
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Long id;

        private String name;

        private Integer age;
    }
}
