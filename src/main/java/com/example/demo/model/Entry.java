package com.example.demo.model;

public class Entry {

    private KeyType key;
    private String value;

    public Entry(KeyType key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public void setKey(KeyType key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isAction(){
        return getKey() == KeyType.ACTION;
    }

    public ActionType getAction(){
        return ActionType.valueOf(getValue());
    }

    public boolean isIndex(){
        return getKey() == KeyType.INDEX;
    }

    public int getIndex(){
        return Integer.parseInt(getValue());
    }

    public boolean isInputFile(){
        return getKey() == KeyType.IN_FILE;
    }

    public boolean isOutputFile(){
        return getKey() == KeyType.OUT_FILE;
    }

    public boolean isReadFile(){
        return getKey() == KeyType.READ_FILE;
    }

}
