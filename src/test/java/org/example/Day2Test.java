package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.Day2.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    @Test
    void readFileTest() {
        ArrayList<ArrayList<String>> actual = readFile("/day2TestInput.txt");
        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        ArrayList<String> row1 = new ArrayList<>();
        row1.add("A");
        row1.add("Y");
        expected.add(row1);
        ArrayList<String> row2 = new ArrayList<>();
        row2.add("B");
        row2.add("X");
        expected.add(row2);
        ArrayList<String> row3 = new ArrayList<>();
        row3.add("C");
        row3.add("Z");
        expected.add(row3);

        assertEquals(expected,actual);
    }

    @Test
    void findOutComeScoreOfEachRoundTest() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(6);
        expected.add(0);
        expected.add(3);
        assertEquals(expected,findOutComeScoreOfEachRound(readFile("/day2TestInput.txt")));
    }

    @Test
    void findSelectedShapeScoreOfEachRoundTest() {
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);
        expected.add(3);
        assertEquals(expected,findSelectedShapeScoreOfEachRound(readFile("/day2TestInput.txt")));
    }

    @Test
    void findTotalScoreEachRoundTest() {
        ArrayList<Integer> outCome = new ArrayList<>();
        outCome.add(2);
        outCome.add(1);
        outCome.add(3);

        ArrayList<Integer> selectShape = new ArrayList<>();
        selectShape.add(6);
        selectShape.add(0);
        selectShape.add(3);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(8);
        expected.add(1);
        expected.add(6);
        assertEquals(expected,findTotalScoreEachRound(outCome,selectShape));
    }

    @Test
    void findTotalScoreTest() {
        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(8);
        actual.add(1);
        actual.add(6);

        assertEquals(15,findTotalScore(actual));
    }

    @Test
    void pipeLineTest() {
        assertEquals(15,pipeLine("/day2TestInput.txt"));
    }

    //Part 2 Unit test.
    @Test
    void findOutComeScoreOfEachRoundPart2Test(){
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(0);
        expected.add(6);

        assertEquals(expected,findOutComeScoreOfEachRoundPart2(readFile("/day2TestInput.txt")));
    }

    @Test
    void findSelectedShapeScoreOfEachRoundPart2Test(){
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(1);
        expected.add(1);

        assertEquals(expected,findSelectedShapeScoreOfEachRoundPart2(readFile("/day2TestInput.txt")));
    }

    @Test
    void pipeLine_2Test() {
        assertEquals(12,pipeLine_2("/day2TestInput.txt"));
    }
}