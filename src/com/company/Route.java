package com.company;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private Integer id;
    private Integer routeNumber;
    private List<Integer> listOfStops = new ArrayList<>();
    private String name;

    public Route(String[] ar){
        addRoute(ar);
    }

    private void addRoute(String[] ar){
        this.routeNumber = Integer.parseInt(ar[1]);
        this.id = Integer.parseInt(ar[2]);
        this.name = ar[3];
    }

    public void extendRoute(String[] ar){
        this.listOfStops.add(Integer.parseInt(ar[2]));
    }

    public Integer getNextStopIndex(Integer currentIndex){
        if (currentIndex.equals(listOfStops.size()-1)){
            return  0;
        }
        return currentIndex+1;
    }

    public Integer getStop(Integer stopIndex){
        return listOfStops.get(stopIndex);
    }
}
