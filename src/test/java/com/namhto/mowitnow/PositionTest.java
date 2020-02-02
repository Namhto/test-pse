package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @Test
    void creatingPositionWithNegativeCoordinatesShouldFail() {
        assertThatThrownBy(() -> new Position(-1, 5)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Position(3, -2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isGreaterThanIsTrue() {
        assertThat(new Position(10, 10).isGreaterThan(new Position(5, 10))).isTrue();
        assertThat(new Position(10, 10).isGreaterThan(new Position(10, 5))).isTrue();
        assertThat(new Position(10, 10).isGreaterThan(new Position(5, 5))).isTrue();
    }
    @Test
    void isGreaterThanIsFalse() {
        assertThat(new Position(2, 5).isGreaterThan(new Position(5, 5))).isFalse();
        assertThat(new Position(5, 2).isGreaterThan(new Position(5, 5))).isFalse();
        assertThat(new Position(5, 5).isGreaterThan(new Position(5, 5))).isFalse();
    }
}