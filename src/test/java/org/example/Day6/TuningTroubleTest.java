package org.example.Day6;

import org.junit.jupiter.api.Test;

import static org.example.Day6.TuningTrouble.*;
import static org.junit.jupiter.api.Assertions.*;

class TuningTroubleTest {

    @Test
    void readFileTest() {
        String expected = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        String actual = readFile("/day6TestInput.txt");

        assertEquals(expected,actual);
    }

    @Test
    void charactersToBeProcessedBeforeFirstMarkerTest() {
        String dataStream = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        int expected = 7;
        int actual = charactersToBeProcessedBeforeFirstMarker(dataStream,CharSeqAllDifferent.SIZE4.getValue());

        assertEquals(expected,actual);
    }

    @Test
    void hasNoDuplicateCharsTestFalse() {
        assertFalse(hasNoDuplicateChars("mjqj"));
    }

    @Test
    void hasNoDuplicateCharsTestTrue() {
        assertTrue(hasNoDuplicateChars("jpqm"));
    }

    @Test
    void pipeLineTest() {
        assertEquals(7, pipeLine("/day6TestInput.txt"));
    }

    @Test
    void pipeLine2Test() {
        assertEquals(19, pipeLine2("/day6TestInput.txt"));
    }
}