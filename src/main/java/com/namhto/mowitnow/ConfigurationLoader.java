package com.namhto.mowitnow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.namhto.mowitnow.ErrorCode.*;

public class ConfigurationLoader {

    private static Configuration configuration = new Configuration();

    private static MowerConfiguration currentParsedConfiguration;

    public static Configuration load(String configurationFilePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(configurationFilePath));
            load(lines);
            return configuration;
        } catch (IOException e) {
            throw new MowItNowException(CONFIGURATION_CANNOT_READ_FILE.withData(e.getMessage()));
        }
    }

    private static void load(List<String> lines) {
        if (lines.size() == 0) {
            throw new MowItNowException(CONFIGURATION_EMPTY_CONFIGURATION_FILE);
        }
        configuration.northEastLimitPosition = parseNorthEastLimitPosition(lines.get(0));
        lines.subList(1, lines.size()).forEach(ConfigurationLoader::parseMowerConfiguration);
    }

    private static Position parseNorthEastLimitPosition(String line) {
        String[] parts = line.split(" ");
        if (parts.length != 2) {
            throw new MowItNowException(CONFIGURATION_INVALID_NORTH_EAST_LIMIT_POSITION.withData(line));
        }
        try {
            return new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
            throw new MowItNowException(CONFIGURATION_POSITION_NOT_A_NUMBER.withData(line));
        }
    }

    private static void parseMowerConfiguration(String line) {
        if (currentParsedConfiguration == null) {
            parseInitialPositionAndOrientation(line);
        } else {
            parseInstructions(line);
            configuration.mowerConfigurations.add(currentParsedConfiguration);
            currentParsedConfiguration = null;
        }
    }

    private static void parseInitialPositionAndOrientation(String line) {
        String[] parts = line.split(" ");
        if (parts.length != 3) {
            throw new MowItNowException(CONFIGURATION_INVALID_INITIAL_POSITION_AND_ORIENTATION.withData(line));
        }
        try {
            Position initialPosition = new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            Orientation initialOrientation = Orientation.get(parts[2]);
            currentParsedConfiguration = new MowerConfiguration(initialPosition, initialOrientation);
        } catch (NumberFormatException e) {
            throw new MowItNowException(CONFIGURATION_POSITION_NOT_A_NUMBER.withData(line));
        }
    }

    private static void parseInstructions(String line) {
        for (char c : line.toCharArray()) {
            currentParsedConfiguration.instructions.add(Instruction.get(c));
        }
    }
}
