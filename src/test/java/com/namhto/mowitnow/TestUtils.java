package com.namhto.mowitnow;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {

    public static void assertException(Throwable throwable, ErrorCode code) {
        assertThat(throwable).isInstanceOf(MowItNowException.class);
        assertThat(((MowItNowException) throwable).getCode()).isEqualTo(code);
    }

    public static void assertMowerConfiguration(
            MowerConfiguration configuration,
            Position position,
            Orientation orientation,
            List<Instruction> instructions
    ) {
        assertThat(configuration.initialPosition).isEqualTo(position);
        assertThat(configuration.initialOrientation).isEqualTo(orientation);
        assertThat(configuration.instructions).containsExactlyElementsOf(instructions);
    }
}
