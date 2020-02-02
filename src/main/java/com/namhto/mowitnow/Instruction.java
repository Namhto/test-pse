package com.namhto.mowitnow;

import java.util.Arrays;

import static com.namhto.mowitnow.ErrorCode.CONFIGURATION_INVALID_INSTRUCTION;

public enum Instruction {
    TURN_RIGHT("D"),
    TURN_LEFT("G"),
    MOVE_FORWARD("A");

    private String value;

    Instruction(String value) {
        this.value = value;
    }

    public static Instruction get(char value) {
        return Arrays.stream(values())
                .filter(instruction -> instruction.value.equals(String.valueOf(value)))
                .findFirst()
                .orElseThrow(() -> new MowItNowException(CONFIGURATION_INVALID_INSTRUCTION.withData(value)));
    }
}
