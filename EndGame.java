package com.example.scoutingmisgav;

import android.view.View;
import android.widget.RadioButton;

public class EndGame {
    public int Points=0;
    public State current;

    enum State{
        Latch,
        PartiallyParked,
        FullyParked,
        NONE;
        }

    public void SetScore(State Endstate){
        if(Endstate==State.Latch){
            Points=50;
        }
        else if(Endstate==State.FullyParked){
            Points=25;
        }
        else if(Endstate==State.PartiallyParked){
            Points=15;
        }

        else if(Endstate==State.NONE){
            Points=0;
        }
        current = Endstate;
    }

    public int getPoints() {
        return Points;
    }

    public String getPointsAsString(){return "Total : "+String.valueOf(Points);}

    public String State (){
        return String.valueOf(getPoints()) + ",";
    }
    public byte[] getCsvState(){
        return State().getBytes();
    }
}
