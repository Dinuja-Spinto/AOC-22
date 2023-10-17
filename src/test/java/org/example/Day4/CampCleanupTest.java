package org.example.Day4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.Day4.CampCleanup.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CampCleanupTest {

    @Test
    void readFileTest() {
        ArrayList<String> actual = readFile("/day4TestInput.txt");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("2-4,6-8");
        expected.add("2-3,4-5");
        expected.add("5-7,7-9");
        expected.add("2-8,3-7");
        expected.add("6-6,4-6");
        expected.add("2-6,4-8");

        assertEquals(expected,actual);
    }

    @Test
    void getElvesPairsTest() {
        ArrayList<String> actual = readFile("/day4TestInput.txt");
        ArrayList<ElvesPair> elvesPairs = new ArrayList<>();
        elvesPairs.add(new ElvesPair(new Elf("2-4"), new Elf("6-8")));

        assertEquals(elvesPairs.get(0).getElf1().getRangeOfIDs(),getElvesPairs(actual).get(0).getElf1().getRangeOfIDs());
    }

    @Test
    void totalOfOverlappingPairsTest() {
        ArrayList<String> actual = readFile("/day4TestInput.txt");
        assertEquals(2,totalOfOverlappingPairs(getElvesPairs(actual), CampCleanup :: isOverLapping));
    }

    @Test
    void pipeLineTest() {
        assertEquals(2,pipeLine("/day4TestInput.txt"));
    }

    @Test
    void totalOfOverlappingPairs_2Test() {
        ArrayList<String> actual = readFile("/day4TestInput.txt");
        assertEquals(4,totalOfOverlappingPairs(getElvesPairs(actual),CampCleanup :: isOverLapping_2));
    }

    @Test
    void pipeLine_2Test() {
        assertEquals(4,pipeLine_2("/day4TestInput.txt"));
    }
}