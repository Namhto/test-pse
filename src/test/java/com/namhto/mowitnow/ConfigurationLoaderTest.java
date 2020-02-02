package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.namhto.mowitnow.ErrorCode.*;
import static com.namhto.mowitnow.Instruction.*;
import static com.namhto.mowitnow.Orientation.EAST;
import static com.namhto.mowitnow.Orientation.NORTH;
import static org.assertj.core.api.Assertions.*;

class ConfigurationLoaderTest {

    @Test
    void loadShouldFailBecauseEmptyConfigurationFile() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("empty-config.txt").toURI()).getAbsolutePath();
        MowItNowException exception = catchThrowableOfType(() -> new ConfigurationLoader().load(path), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(CONFIGURATION_EMPTY_CONFIGURATION_FILE);
    }

    @Test
    void loadShouldFailBecauseInvalidNorthEastLimitPosition() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("bad-first-line-config.txt").toURI()).getAbsolutePath();
        MowItNowException exception = catchThrowableOfType(() -> new ConfigurationLoader().load(path), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(CONFIGURATION_INVALID_NORTH_EAST_LIMIT_POSITION);
    }

    @Test
    void loadShouldFailBecauseNorthEastLimitPositionIsNotANumber() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("first-line-not-number-config.txt").toURI()).getAbsolutePath();
        MowItNowException exception = catchThrowableOfType(() -> new ConfigurationLoader().load(path), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(CONFIGURATION_POSITION_NOT_A_NUMBER);
    }

    @Test
    void loadShouldFailBecauseBadInitialPositionAndOrientation() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("bad-position-and-orientation-config.txt").toURI()).getAbsolutePath();
        MowItNowException exception = catchThrowableOfType(() -> new ConfigurationLoader().load(path), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(CONFIGURATION_INVALID_INITIAL_POSITION_AND_ORIENTATION);
    }

    @Test
    void loadShouldFailBecausePositionIsNotANumber() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("position-not-number-config.txt").toURI()).getAbsolutePath();
        MowItNowException exception = catchThrowableOfType(() -> new ConfigurationLoader().load(path), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(CONFIGURATION_POSITION_NOT_A_NUMBER);
    }

    @Test
    void loadShouldFailBecauseInvalidOrientation() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("invalid-orientation-config.txt").toURI()).getAbsolutePath();
        MowItNowException exception = catchThrowableOfType(() -> new ConfigurationLoader().load(path), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(CONFIGURATION_INVALID_ORIENTATION);
    }

    @Test
    void loadShouldFailBecauseInvalidInstruction() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("invalid-instruction-config.txt").toURI()).getAbsolutePath();
        MowItNowException exception = catchThrowableOfType(() -> new ConfigurationLoader().load(path), MowItNowException.class);
        assertThat(exception.getCode()).isEqualTo(CONFIGURATION_INVALID_INSTRUCTION);
    }

    @Test
    void loadShouldSucceed() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("config.txt").toURI()).getAbsolutePath();
        Configuration configuration = new ConfigurationLoader().load(path);
        assertThat(configuration.northEastLimitPosition).isEqualTo(new Position(5, 5));
        assertThat(configuration.mowerConfigurations).hasSize(2);
        assertMowerConfiguration(
                configuration.mowerConfigurations.get(0),
                new Position(1, 2),
                NORTH,
                Stream.of( TURN_LEFT, MOVE_FORWARD, TURN_LEFT, MOVE_FORWARD, TURN_LEFT, MOVE_FORWARD, TURN_LEFT, MOVE_FORWARD, MOVE_FORWARD).collect(Collectors.toList())
        );
        assertMowerConfiguration(
                configuration.mowerConfigurations.get(1),
                new Position(3, 3),
                EAST,
                Stream.of( MOVE_FORWARD, MOVE_FORWARD, TURN_RIGHT, MOVE_FORWARD, MOVE_FORWARD, TURN_RIGHT, MOVE_FORWARD, TURN_RIGHT, TURN_RIGHT, MOVE_FORWARD).collect(Collectors.toList())
        );
    }

    private void assertMowerConfiguration(
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