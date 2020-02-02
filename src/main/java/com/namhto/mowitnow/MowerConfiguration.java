package com.namhto.mowitnow;

import java.util.ArrayList;
import java.util.List;

public class MowerConfiguration {

    public Position initialPosition;

    public Orientation initialOrientation;

    public List<Instruction> instructions = new ArrayList<>();

    public MowerConfiguration(Position initialPosition, Orientation initialOrientation) {
        this.initialPosition = initialPosition;
        this.initialOrientation = initialOrientation;
    }
}
