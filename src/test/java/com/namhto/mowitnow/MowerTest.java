package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import static com.namhto.mowitnow.ErrorCode.POSITION_OUTSIDE_LIMITS;
import static com.namhto.mowitnow.Instruction.*;
import static com.namhto.mowitnow.Orientation.*;
import static org.assertj.core.api.Assertions.*;

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
        MowItNowException exception = catchThrowableOfType(() -> new Mower(new Position(3, 7), WEST, new Position(5, 5)), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(POSITION_OUTSIDE_LIMITS);
        MowItNowException exception1 = catchThrowableOfType(() -> new Mower(new Position(8, 2), WEST, new Position(5, 5)), MowItNowException.class);
        assertThat(exception1.getCode()).isEqualTo(POSITION_OUTSIDE_LIMITS);
    }

    @Test
    void turnMowerToRightShouldSucceed() {
        Mower mower = new Mower(new Position(3, 5), NORTH, new Position(10, 15));
        assertThat(mower.execute(TURN_RIGHT).getPositionAndOrientation()).isEqualTo("3 5 E");
        assertThat(mower.execute(TURN_RIGHT).getPositionAndOrientation()).isEqualTo("3 5 S");
        assertThat(mower.execute(TURN_RIGHT).getPositionAndOrientation()).isEqualTo("3 5 W");
        assertThat(mower.execute(TURN_RIGHT).getPositionAndOrientation()).isEqualTo("3 5 N");
    }

    @Test
    void turnMowerToLeftShouldSucceed() {
        Mower mower = new Mower(new Position(3, 5), SOUTH, new Position(10, 15));
        assertThat(mower.execute(TURN_LEFT).getPositionAndOrientation()).isEqualTo("3 5 E");
        assertThat(mower.execute(TURN_LEFT).getPositionAndOrientation()).isEqualTo("3 5 N");
        assertThat(mower.execute(TURN_LEFT).getPositionAndOrientation()).isEqualTo("3 5 W");
        assertThat(mower.execute(TURN_LEFT).getPositionAndOrientation()).isEqualTo("3 5 S");
    }

    @Test
    void moveMowerForwardShouldSucceed() {
        Mower mower = new Mower(new Position(0, 0), NORTH, new Position(10, 15));
        assertThat(mower.execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("0 1 N");
        assertThat(mower.execute(TURN_RIGHT).execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("1 1 E");
        assertThat(mower.execute(TURN_RIGHT).execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("1 0 S");
    }

    @Test
    void moveMowerForwardToBeOnLimitShouldSucceed() {
        Mower mower = new Mower(new Position(0, 1), SOUTH, new Position(10, 15));
        assertThat(mower.execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("0 0 S");
        Mower mower1 = new Mower(new Position(9, 15), EAST, new Position(10, 15));
        assertThat(mower1.execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("10 15 E");
    }

    @Test
    void moveMowerForwardShouldFailBecauseItWouldBeBeforeSouthWestLimit() {
        Mower mower = new Mower(new Position(0, 0), SOUTH, new Position(10, 15));
        assertThat(mower.execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("0 0 S");
        assertThat(mower.execute(TURN_RIGHT).execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("0 0 W");
    }

    @Test
    void moveMowerForwardShouldFailBecauseItWouldBeAfterNorthEastLimit() {
        Mower mower = new Mower(new Position(10, 15), NORTH, new Position(10, 15));
        assertThat(mower.execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("10 15 N");
        assertThat(mower.execute(TURN_RIGHT).execute(MOVE_FORWARD).getPositionAndOrientation()).isEqualTo("10 15 E");
    }
}