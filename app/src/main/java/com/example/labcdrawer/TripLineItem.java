package com.example.labcdrawer;

import java.io.Serializable;

public class TripLineItem implements Serializable {

    private String line;
    private TransportMode transportMode;

    public TripLineItem(){

    }

    public TripLineItem(String line, TransportMode transportMode){
        this.line = line;
        this.transportMode = transportMode;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }
}
