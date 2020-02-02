package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import static com.namhto.mowitnow.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;

class PositionTest {

    @Test
    void creatingPositionWithNegativeCoordinatesShouldFail() {
        MowItNowException exception = catchThrowableOfType(() -> new Position(-1, 5), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(INVALID_POSITION);
        MowItNowException exception1 = catchThrowableOfType(() -> new Position(3, -2), MowItNowException.class);
        assertThat(exception1.getCode()).isEqualTo(INVALID_POSITION);
    }

    @Test
    void creatingPositionFromANullPositionShouldFail() {
        MowItNowException exception = catchThrowableOfType(() -> new Position(null), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(INVALID_POSITION);
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