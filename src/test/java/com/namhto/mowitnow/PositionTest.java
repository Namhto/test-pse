package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @Test
    void creatingPositionWithNegativeCoordinatesShouldFail() {
        assertThatThrownBy(() -> new Position(-1, 5)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Position(3, -2)).isInstanceOf(IllegalArgumentException.class);
    }
}