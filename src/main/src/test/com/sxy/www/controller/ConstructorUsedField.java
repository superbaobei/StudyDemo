package com.sxy.www.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyusun on 2019/4/23.
 */
public class ConstructorUsedField {

    private List<Integer> list = new ArrayList<>();

    public Integer a = new Integer(0);

    public ConstructorUsedField() {
        System.out.println("list.size() = " + list.size());
        System.out.println("a = " + a);
    }
}
