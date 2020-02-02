package com.namhto.mowitnow;

public class EntryPoint {

    public static void main(String[] args) {
        Configuration configuration = new ConfigurationLoader().load(args[0]);
        MowerOrchestrator orchestrator = new MowerOrchestrator(configuration);
        orchestrator.run().getMowerPositionsAndOrientations().forEach(System.out::println);
    }
}
