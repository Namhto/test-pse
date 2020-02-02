package com.namhto.mowitnow;

import java.util.ArrayList;
import java.util.List;

public class MowerOrchestrator {

    private Configuration globalConfiguration;

    private List<String> mowerPositionsAndOrientations = new ArrayList<>();

    public MowerOrchestrator(Configuration globalConfiguration) {
        this.globalConfiguration = globalConfiguration;
    }

    public MowerOrchestrator run() {
        globalConfiguration.mowerConfigurations.forEach(configuration -> {
            Mower mower = new Mower(configuration.initialPosition, configuration.initialOrientation, globalConfiguration.northEastLimitPosition);
            configuration.instructions.forEach(mower::execute);
            mowerPositionsAndOrientations.add(mower.getPositionAndOrientation());
        });
        return this;
    }

    public List<String> getMowerPositionsAndOrientations() {
        return mowerPositionsAndOrientations;
    }
}
