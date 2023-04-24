package com.study;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泛型擦除机制
 */
public class TestListAddString {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        list.add(new HashMap<>());
        try {
            Method add = list.getClass().getDeclaredMethod("add", Object.class);
            add.invoke(list,new ArrayList<>());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }
}
