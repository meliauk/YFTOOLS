package com.yifan.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MLinkedHashMap<K,V> extends LinkedHashMap<K,V> {


    @Override
    public V put(K key, V value) {
        V newV = value;
        List<V> list = new ArrayList<>();
        //   containsKey - -- 判断是否包含指定的键名
        if(containsKey(key)){
            List v = (List)get(key);
            v.add(value);
            list = v;
        }else {
            list.add(newV);
        }
        // 倒序 list
      //  Collections.reverse(list);
        return super.put(key,(V)list);
    }

/*

    public V putSM(K key, V value) {
        V newV = value;
        Map<String, V> hashMap = new HashMap<>();
        Map<String, V> monthMap = new HashMap<>();
        List<V> list = new ArrayList<>();
        //   containsKey - -- 判断是否包含指定的键名
        if (containsKey(key)) {
            Map v = (Map) get(key);
            for (int i = 0; i < v.keySet().size(); i++) {
                if (containsKey(i)) {
                    List obj = (List) get(i);
                    obj.add(value);
                    list = obj;
                }
                monthMap.put(i + "", (V) list);
            }

        } else {
            monthMap.put(key + "", newV);
        }
        return super.put(key, (V) monthMap);
    }
*/

}
