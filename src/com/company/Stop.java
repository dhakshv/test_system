package com.company;

public class Stop {
    private Integer id;
    private String stopName;
    private Integer passengerCount;
    private Integer initialPassengerCount;
    private Double latitude;
    private Double longitude;

    public Stop(String[] ar){
        addStop(ar);
    }

    private void addStop(String[] ar){
        this.id = Integer.parseInt(ar[1]);
        this.stopName = ar[2];
        this.initialPassengerCount = Integer.parseInt(ar[3]);
        this.passengerCount = Integer.parseInt(ar[3]);
        this.latitude = Double.parseDouble(ar[4]);
        this.longitude = Double.parseDouble(ar[5]);
    }

    public Double getLongitude(){
        return this.longitude;
    }

    public Double getLatitude(){
        return this.latitude;
    }

    public Integer getId(){
        return this.id;
    }

}
