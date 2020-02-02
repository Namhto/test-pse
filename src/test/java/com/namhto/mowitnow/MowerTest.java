package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import static com.namhto.mowitnow.Instruction.*;
import static com.namhto.mowitnow.Orientation.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowerTest {

    @Test
    void getPositionAndOrientationOnNewMowerShouldReturnInitialValue() {
        Mower mower = new Mower(new Position(3, 5), EAST, new Position(10, 15));
        assertThat(mower.getPositionAndOrientation()).isEqualTo("3 5 E");
    }

    @Test
    void creatingAMowerWithNoOrientationShouldFail() {
        assertThatThrownBy(() -> new Mower(new Position(3, 7), null, new Position(10, 15)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void creatingAMowerWithNoNorthEastLimitPositionShouldFail() {
        assertThatThrownBy(() -> new Mower(new Position(3, 7), WEST, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void creatingAMowerWithPositionOutsideLimitsShouldFail() {
        assertThatThrownBy(() -> new Mower(new Position(3, 7), WEST, new Position(5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Mower(new Position(8, 2), WEST, new Position(5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void turnMowerToRightShouldSucceed() {
        Mower mower = new Mower(new Position(3, 5), NORTH, new Position(10, 15));
        assertThat(mower.move(D).getPositionAndOrientation()).isEqualTo("3 5 E");
        assertThat(mower.move(D).getPositionAndOrientation()).isEqualTo("3 5 S");
        assertThat(mower.move(D).getPositionAndOrientation()).isEqualTo("3 5 W");
        assertThat(mower.move(D).getPositionAndOrientation()).isEqualTo("3 5 N");
    }

    @Test
    void turnMowerToLeftShouldSucceed() {
        Mower mower = new Mower(new Position(3, 5), SOUTH, new Position(10, 15));
        assertThat(mower.move(G).getPositionAndOrientation()).isEqualTo("3 5 E");
        assertThat(mower.move(G).getPositionAndOrientation()).isEqualTo("3 5 N");
        assertThat(mower.move(G).getPositionAndOrientation()).isEqualTo("3 5 W");
        assertThat(mower.move(G).getPositionAndOrientation()).isEqualTo("3 5 S");
    }

    @Test
    void moveMowerForwardShouldSucceed() {
        Mower mower = new Mower(new Position(0, 0), NORTH, new Position(10, 15));
        assertThat(mower.move(A).getPositionAndOrientation()).isEqualTo("0 1 N");
        assertThat(mower.move(D).move(A).getPositionAndOrientation()).isEqualTo("1 1 E");
        assertThat(mower.move(D).move(A).getPositionAndOrientation()).isEqualTo("1 0 S");
    }

    @Test
    void moveMowerForwardToBeOnLimitShouldSucceed() {
        Mower mower = new Mower(new Position(0, 1), SOUTH, new Position(10, 15));
        assertThat(mower.move(A).getPositionAndOrientation()).isEqualTo("0 0 S");
        Mower mower1 = new Mower(new Position(9, 15), EAST, new Position(10, 15));
        assertThat(mower1.move(A).getPositionAndOrientation()).isEqualTo("10 15 E");
    }

    @Test
    void moveMowerForwardShouldFailBecauseItWouldBeBeforeSouthWestLimit() {
        Mower mower = new Mower(new Position(0, 0), SOUTH, new Position(10, 15));
        assertThat(mower.move(A).getPositionAndOrientation()).isEqualTo("0 0 S");
        assertThat(mower.move(D).move(A).getPositionAndOrientation()).isEqualTo("0 0 W");
    }

    @Test
    void moveMowerForwardShouldFailBecauseItWouldBeAfterNorthEastLimit() {
        Mower mower = new Mower(new Position(10, 15), NORTH, new Position(10, 15));
        assertThat(mower.move(A).getPositionAndOrientation()).isEqualTo("10 15 N");
        assertThat(mower.move(D).move(A).getPositionAndOrientation()).isEqualTo("10 15 E");
    }
}