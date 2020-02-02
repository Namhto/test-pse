package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MowerTest {

    @Test
    void getPositionAndOrientationOnNewMowerShouldReturnInitialValue() {
        Mower mower = new Mower(3, 5, Orientation.EAST);
        assertThat(mower.getPositionAndOrientation()).isEqualTo("3 5 E");
    }

    @Test
    void creatingAMowerWithNegativePositionShouldFail() {
        assertThatThrownBy(() -> new Mower(-1, 5, Orientation.NORTH)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Mower(3, -2, Orientation.SOUTH)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void creatingAMowerWithNoOrientationShouldFail() {
        assertThatThrownBy(() -> new Mower(3, 7, null)).isInstanceOf(IllegalArgumentException.class);
    }
}