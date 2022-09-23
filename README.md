# Bowling

This is a java 8 console application for the ten pin bowling game. 

It works by accepting a file as an argument and printing the game result to the console.

The file must be in the following format: Each line must contain the player's name separated by a tab, as well as the number of pins knocked down or if there was a foul, denoted by the letter F.

### File ####
    Mike    10
    Mike    10
    Mike    10
    Mike    10
    Mike    F
    Mike    10
    Mike    10
    Mike    10
    Mike    0
    Mike    10
    Mike    10
    Mike    10
    Mike    10
    Mike    10

### Result ###

    Frame       1       2       3       4       5       6       7       8       9       10
    Mike
    Pinfalls        X       X       X       X   F   /       X       X   0   /       X   X   X   X
    Score       30      60      80      100     120     140     160     180     210     240

## Building jar ##
    ./gradlew bootJar

## Running tests ##
    ./gradlew test
    
## Cleaning the build ##
    ./gradlew clean
    
## Running with gradlew ##
    ./gradlew bootRun -Pargs=src/test/resources/positive/perfect.txt
    
## Running with jar ##
    java -jar build/libs/Bowling-1.0.0.jar src/test/resources/positive/scores.txt
    
## Building IDE project ###
    ./gradlew eclipse