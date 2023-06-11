package com.greed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 贪心算法
 * @author yan
 */
public class GreedAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> setHashMap = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("A");
        hashSet1.add("B");
        hashSet1.add("C");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("A");
        hashSet2.add("D");
        hashSet2.add("E");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("D");
        hashSet3.add("F");
        hashSet3.add("G");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("A");
        hashSet4.add("B");
        HashSet<String> hashSet5= new HashSet<>();
        hashSet5.add("A");
        hashSet5.add("H");
        setHashMap.put("K1",hashSet1);
        setHashMap.put("K2",hashSet2);
        setHashMap.put("K3",hashSet3);
        setHashMap.put("K4",hashSet4);
        setHashMap.put("K5",hashSet5);

        HashSet<String> all = new HashSet<>();
        all.add("A");
        all.add("B");
        all.add("C");
        all.add("D");
        all.add("E");
        all.add("F");
        all.add("G");
        all.add("H");

        HashSet<String> tempSet = new HashSet<>();
        List<String> list = new ArrayList<>();
        String maxKey = null;
        while (all.size() != 0){
            maxKey = null;
            for (String key : setHashMap.keySet()) {
                tempSet.clear();
                HashSet<String> set = setHashMap.get(key);
                tempSet.addAll(set);
                tempSet.retainAll(all);
                // 贪心算法关键点 总是取当前最优解
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > setHashMap.get(maxKey).size()))
                    maxKey = key;
            }
            if (maxKey != null){
                list.add(maxKey);
                all.removeAll(setHashMap.get(maxKey));
            }
        }
        System.out.println(list);
    }
}
