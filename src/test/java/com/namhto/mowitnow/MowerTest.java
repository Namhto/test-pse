package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowerTest {

    @Test
    void getPositionAndOrientationOnNewMowerShouldReturnInitialValue() {
        Mower mower = new Mower(new Position(3, 5), Orientation.EAST, new Position(10, 15));
        assertThat(mower.getPositionAndOrientation()).isEqualTo("3 5 E");
    }

    @Test
    void creatingAMowerWithNoOrientationShouldFail() {
        assertThatThrownBy(() -> new Mower(new Position(3, 7), null, new Position(10, 15)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void creatingAMowerWithNoNorthEastLimitPositionShouldFail() {
        assertThatThrownBy(() -> new Mower(new Position(3, 7), Orientation.WEST, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void creatingAMowerWithPositionOutsideLimitsShouldFail() {
        assertThatThrownBy(() -> new Mower(new Position(3, 7), Orientation.WEST, new Position(5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Mower(new Position(8, 2), Orientation.WEST, new Position(5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void turnMowerToRightShouldSucceed() {
        Mower mower = new Mower(new Position(3, 5), Orientation.NORTH, new Position(10, 15));
        assertThat(mower.move(Instruction.D).getPositionAndOrientation()).isEqualTo("3 5 E");
        assertThat(mower.move(Instruction.D).getPositionAndOrientation()).isEqualTo("3 5 S");
        assertThat(mower.move(Instruction.D).getPositionAndOrientation()).isEqualTo("3 5 W");
        assertThat(mower.move(Instruction.D).getPositionAndOrientation()).isEqualTo("3 5 N");
    }

    @Test
    void turnMowerToLeftShouldSucceed() {
        Mower mower = new Mower(new Position(3, 5), Orientation.SOUTH, new Position(10, 15));
        assertThat(mower.move(Instruction.G).getPositionAndOrientation()).isEqualTo("3 5 E");
        assertThat(mower.move(Instruction.G).getPositionAndOrientation()).isEqualTo("3 5 N");
        assertThat(mower.move(Instruction.G).getPositionAndOrientation()).isEqualTo("3 5 W");
        assertThat(mower.move(Instruction.G).getPositionAndOrientation()).isEqualTo("3 5 S");
    }
}