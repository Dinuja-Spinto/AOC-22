package org.example.Day8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.Day8.Treetop.*;
import static org.junit.jupiter.api.Assertions.*;

class TreetopTest {

    String fileName = "/day8TestInput.txt";

    @Test
    void readFileTest() {
        ArrayList<String> actual = readFile(fileName);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("30373");
        expected.add("25512");
        expected.add("65332");
        expected.add("33549");
        expected.add("35390");

        assertEquals(expected,actual);

    }

    @Test
    void captureTreeHeightTest() {
        ArrayList<ArrayList<Integer>> actual = captureTreeHeight(readFile(fileName));
        ArrayList<ArrayList<Integer>> expected = new ArrayList<>();

        ArrayList<Integer> row = new ArrayList<>();
        row.add(3); row.add(0); row.add(3); row.add(7); row.add(3);
        expected.add(row);

        ArrayList<Integer> row1 = new ArrayList<>();
        row1.add(2); row1.add(5); row1.add(5); row1.add(1); row1.add(2);
        expected.add(row1);

        ArrayList<Integer> row2 = new ArrayList<>();
        row2.add(6); row2.add(5); row2.add(3); row2.add(3); row2.add(2);
        expected.add(row2);

        ArrayList<Integer> row3 = new ArrayList<>();
        row3.add(3); row3.add(3); row3.add(5); row3.add(4); row3.add(9);
        expected.add(row3);

        ArrayList<Integer> row4 = new ArrayList<>();
        row4.add(3); row4.add(5); row4.add(3); row4.add(9); row4.add(0);
        expected.add(row4);

        assertEquals(expected,actual);
    }

    @Test
    void calculateTreesVisibleOnTheEdgeTest() {
        int actual = calculateTreesVisibleOnTheEdge(captureTreeHeight(readFile(fileName)));
        int expected = 16;

        assertEquals(expected,actual);
    }

    @Test
    void calculateTreesVisibleInInteriorTest() {
        int actual = calculateTreesVisibleInInterior(captureTreeHeight(readFile(fileName)));
        int expected = 5;

        assertEquals(expected,actual);
    }

    @Test
    void totalVisibleTreesTest() {
        int actual = totalVisibleTrees(captureTreeHeight(readFile(fileName)));
        int expected = 21;

        assertEquals(expected,actual);
    }

    @Test
    void pipeLineTest() {
        int actual = pipeLine(fileName);
        int expected = 21;

        assertEquals(expected,actual);
    }

    @Test
    void calculateHighestScenicScore() {
    }

    @Test
    void pipeLine2() {
    }
}