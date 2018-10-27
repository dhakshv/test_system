package com.company;

public class Event {
    private Integer rank;
    private String type;
    private Integer targetObject;

    public Event(){
    }

    public Event(String[] ar){
        addEvent(Integer.parseInt(ar[1]), ar[2], Integer.parseInt(ar[3]));
    }

    public void addEvent(Integer rank, String type, Integer targetObject){
        this.type = type;
        this.rank = rank;
        this.targetObject = targetObject;
    }

    public String getType() {
        return this.type;
    }

    public Integer getRank(){
        return this.rank;
    }

    public Integer getTargetObject(){
        return this.targetObject;
    }

}
