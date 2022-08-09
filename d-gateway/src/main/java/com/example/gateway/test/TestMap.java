package com.example.gateway.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


//함수형 프로그래밍에서 쓰는 위임 패턴
@Slf4j
public class TestMap <K , V>  implements Map{

    private final HashMap<K,V> hashMap;

    public TestMap(HashMap<K, V> hashMap) {
        this.hashMap = hashMap;
    }


    @Override
    public int size() {
        log.info("E => {}", hashMap);
        return hashMap.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
