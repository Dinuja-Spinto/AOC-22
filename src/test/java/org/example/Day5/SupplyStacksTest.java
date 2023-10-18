package org.example.Day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.example.Day5.SupplyStacks.*;
import static org.example.Day5.SupplyStacks.getMoves;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplyStacksTest {

    @Test
    void readFileTest() {
        ArrayList<String> actual = readFile("/day5TestInput.txt");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("move 1 from 2 to 1");
        expected.add("move 3 from 1 to 3");
        expected.add("move 2 from 2 to 1");
        expected.add("move 1 from 1 to 2");

        assertEquals(expected,actual);
    }

    @Test
    void getMovesTest() {
        ArrayList<int[]> expected = new ArrayList<>();
        int [] one = {1,2,1};
        int [] two = {1,2,1};
        int [] three = {1,2,1};
        int [] four = {1,2,1};
        expected.add(one); expected.add(two); expected.add(three); expected.add(four);

        ArrayList<String> instructions = readFile("/day5TestInput.txt");

        Assertions.assertEquals(Arrays.toString(expected.get(0)), Arrays.toString(getMoves(instructions).get(0)));

    }

    @Test
    void fetchNumbersFromInstructionTest() {
        int [] expected = {1,2,1};

        Assertions.assertEquals(Arrays.toString(expected), Arrays.toString(fetchNumbersFromInstruction("move 1 from 2 to 1")));
    }

    @Test
    void getTopOfEachStackTest() {
        ArrayList<String> instructions = readFile("/day5TestInput.txt");
        String actual = getTopOfEachStack(getStacksAfterRearrange(getTestStacks(),getMoves(instructions)));

        assertEquals("CMZ",actual);
    }

    @Test
    void pipeLineTest() {
        assertEquals("ZBDRNPMVH", pipeLine("/day5Input.txt"));
    }

    @Test
    void pipeLine2Test() {
        assertEquals("WDLPFNNNB", pipeLine2("/day5Input.txt"));
    }
}