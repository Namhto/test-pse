package com.namhto.mowitnow;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MowerOrchestratorTest {

    @Test
    void runAndGetResultShouldSucceed() throws URISyntaxException {
        String path = new File(getClass().getClassLoader().getResource("config.txt").toURI()).getAbsolutePath();
        List<String> result = new MowerOrchestrator(new ConfigurationLoader().load(path)).run().getMowerPositionsAndOrientations();
        assertThat(result).containsExactly("1 3 N", "5 1 E");
    }
}