package com.example.scoutingmisgav;

public class Teleop {
    public int Points=0;
    public int MineralInLander=0;
    int MineralInDepot=0;
    public void SetScore(int Lander,int Depot){
        Points=0;
        Points=Lander*5;
        Points+=Depot*2;
    }

    public int getPoints() {
        return Points;
    }

    public String getPointsAsString() {
        return "Total : "+String.valueOf(Points);
    }

    public String getMineralInLanderAsString(){
        return String.valueOf(MineralInLander);
    }
    public String getMineralInDepotAsString(){
        return String.valueOf(MineralInDepot);
    }

    public void AddMinearlToLander(){
        MineralInLander+=1;
    }
    public void DisMinearlToLander(){
        MineralInLander-=1;
    }

    public void AddMinearlToDepot(){
        MineralInDepot+=1;
    }
    public void DisMinearlToDepot(){
        MineralInDepot-=1;
    }

    public String State(){
        return String.valueOf(MineralInLander*5) + "," + String.valueOf(MineralInDepot*2) + ",";
    }
    public byte[] getCsvState(){
        return State().getBytes();
    }



}
