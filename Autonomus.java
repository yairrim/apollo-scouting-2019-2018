package com.example.scoutingmisgav;

public class Autonomus {
    public int Points=0;
    public boolean land = false;
    public boolean sample = false;
    public boolean sample2 = false;
    public boolean teamMarker = false;
    public boolean parking = false;

    public void SetScore(boolean Land,boolean Sampling,boolean Sampling2,boolean TeamMarker,boolean Parking){
        land = Land;
        sample = Sampling;
        sample2 = Sampling2;
        teamMarker = TeamMarker;
        parking = Parking;
        Points= (Land?30:0) + (Sampling?25:0) + (Sampling2?25:0) + (TeamMarker?15:0) + (Parking?10:0);
    }

    public int getPoints() {
        return Points;
    }

    public String getPointsAsString(){
        return "Total : "+String.valueOf(Points);
    }
    public String State(){
        return String.valueOf((land?30:0)) + "," + String.valueOf(sample?25:0) + "," + String.valueOf(sample2?25:0) + "," + String.valueOf(teamMarker?15:0) + "," + String.valueOf(parking?10:0) + ",";
    }
    public byte[] getCsvState(){
        return State().getBytes();
    }

}
