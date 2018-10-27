package com.company;

import java.util.Map;

public class Bus {
    private Integer busNumber;
    private Integer designatedRoute;
    private Integer initialPassengers;
    private Integer initialStopIndex;
    private Integer initialFuelMiles;
    private Integer passengerCapacity;
    private Integer maxFuelMiles;
    private Integer averageSpeed;
    private Integer currentStopIndex;
    private Integer nextStopIndex;
    private Integer currentStop;
    private Integer nextStop;
    private Integer initialMoveTime;
    private Integer nextStopTime;
    private Integer previousPassengers;
    private Integer currentPassengers;
    private Integer currentFuelMiles;

    public Bus(String[] ar, Route route){
        addBus(ar, route);
    }

    public void addBus(String[] ar, Route route) {
        this.busNumber = Integer.parseInt(ar[1]);
        this.designatedRoute = Integer.parseInt(ar[2]);
        this.initialStopIndex = Integer.parseInt(ar[3]);
        this.currentStopIndex = Integer.parseInt(ar[3]);
        this.initialPassengers = Integer.parseInt(ar[4]);
        this.currentPassengers = Integer.parseInt(ar[4]);
        this.passengerCapacity = Integer.parseInt(ar[5]);
        this.initialFuelMiles = Integer.parseInt(ar[6]);
        this.maxFuelMiles = Integer.parseInt(ar[7]);
        this.averageSpeed = Integer.parseInt(ar[8]);
        this.nextStopIndex = route.getNextStopIndex(this.currentStopIndex);
        this.currentStop = route.getStop(this.currentStopIndex);
        this.nextStop = route.getStop(this.nextStopIndex);
        this.initialMoveTime = 0;
    }

    public Event moveBus(Route route, Event newEvent, Map<Integer, Stop> stops, Integer rank){

        this.nextStopTime = rank + calculateTimeToNextStop(stops, currentStop, nextStop, averageSpeed);

        System.out.println("b:"+this.busNumber+"->s:"+this.nextStop+"@"+this.nextStopTime+"//p:0/f:0");

        //increment stops
        currentStopIndex = route.getNextStopIndex(this.currentStopIndex);
        nextStopIndex = route.getNextStopIndex(this.currentStopIndex);
        this.currentStop = route.getStop(this.currentStopIndex);
        this.nextStop = route.getStop(this.nextStopIndex);

        newEvent.addEvent(this.nextStopTime,"move_bus",this.busNumber);

        return newEvent;
    }

    public Integer getDesignatedRoute(){
        return this.designatedRoute;
    }

    private Integer calculateTimeToNextStop(Map<Integer, Stop> stops, Integer fromStop, Integer toStop, Integer averageSpeed){
        Stop fromStopObj = stops.get(fromStop);
        Stop toStopObj = stops.get(toStop);
        Double distance = 70 * Math.sqrt(Math.pow((toStopObj.getLongitude() - fromStopObj.getLongitude()), 2) + Math.pow((toStopObj.getLatitude() - fromStopObj.getLatitude()), 2));
        return (1 + ((distance.intValue() * 60) / averageSpeed));
    }

}
