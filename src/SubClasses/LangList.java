package SubClasses;

import java.util.ArrayList;
import java.util.HashMap;

public class LangList {
    private final ArrayList<String> keys;
    private final ArrayList<Integer> values;
    private final HashMap<Integer, String> libs;
    private final ArrayList<String> exceptions;
    private final ArrayList<String> normalBDT;
    private final HashMap<String, String> classBDT;

    public LangList() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
        libs = new HashMap<>();
        exceptions = new ArrayList<>();
        normalBDT = new ArrayList<>();
        classBDT = new HashMap<>();
    }

    public void add(String key, Integer value) {
        keys.add(key);
        values.add(value);
    }

    public void add(String key, Integer value, String lib) {
        add(key, value);
        libs.put(value, lib);
    }

    public void addException(String key, Integer value) {
        add(key, value);
        exceptions.add(key);
    }

    public void addBDTA(String key, Integer value) {
        addException(key, value);
        normalBDT.add(key);
    }

    public void addBDTO(String key, Integer value, String classs){
        add(key, value);
        add(classs, value);
        classBDT.put(key,classs);
    }

    public Integer get(String key) {
        return values.get(keys.indexOf(key));
    }

    public String get(Integer value) {
        return keys.get(values.indexOf(value));
    }

    public String getLib(Integer value) {
        return libs.get(value);
    }

    public String getClass(String key){
        return classBDT.get(key);
    }

    public Boolean contains(String key) {
        return keys.contains(key);
    }

    public Boolean contains(Integer value) {
        return values.contains(value);
    }

    public Boolean containsLib(Integer value) {
        return libs.containsKey(value);
    }

    public Boolean containsException(String exception) {
        return exceptions.contains(exception);
    }

    public Boolean containsBDT(String exception) {
        return normalBDT.contains(exception);
    }
} 
