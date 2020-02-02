# Mower

### Build procedure

This is a gradle project, so to build the application, please do the following:

 - Install java on your computer (version 8 or above). Download is available [here](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html).
 - Install gradle on your computer. Installation guide is available [here](https://gradle.org/install/).
 - Open a terminal in the project root folder.
 - Run the following command: `gradle jar`.  This will build the executable jar file `test-pse-1.0-SNAPSHOT.jar` in `build/libs`

To run the application, please do the following:

 - Open a terminal in `build/libs`.
 - Run the following command: `java -jar test-pse-1.0-SNAPSHOT.jar <absolute path to the configuration file>`

### Tests

To run the unit tests available in `src/test`, please do the following:
 - Open a terminal in the project root folder.
 - Run the following command: `gradle clean test`.