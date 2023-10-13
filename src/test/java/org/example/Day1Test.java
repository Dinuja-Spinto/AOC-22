package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.example.Day1.*;
import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void readFile(){
        ArrayList<String> actual = ElfCarryingCalories("/day1TestInput.txt");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("1000");
        expected.add("2000");
        expected.add("3000");
        expected.add("");
        expected.add("4000");
        expected.add("");
        expected.add("5000");
        expected.add("6000");
        expected.add("");
        expected.add("7000");
        expected.add("8000");
        expected.add("9000");
        expected.add("");
        expected.add("10000");

        assertEquals(expected,actual);
    }

    @Test
    void calculateTotalCarryingOf(){
        ArrayList<String> input = new ArrayList<>();
        input.add("1000");
        input.add("2000");
        input.add("3000");
        input.add("");
        input.add("4000");
        input.add("");
        input.add("5000");
        input.add("6000");
        input.add("");
        input.add("7000");
        input.add("8000");
        input.add("9000");
        input.add("");
        input.add("10000");

        TreeSet<Integer> result = calculateTotalCarryingOfElf(input);

        TreeSet<Integer> totalCaloriesOfElfCarryTest = new TreeSet<>();
        totalCaloriesOfElfCarryTest.add(6000);
        totalCaloriesOfElfCarryTest.add(4000);
        totalCaloriesOfElfCarryTest.add(11000);
        totalCaloriesOfElfCarryTest.add(24000);
        totalCaloriesOfElfCarryTest.add(10000);

        assertEquals(totalCaloriesOfElfCarryTest,result);

    }

    @Test
    void elfCarryingMostCaloriesTest() {
        TreeSet<Integer> totalCaloriesOfElfCarryTest = new TreeSet<>();
        totalCaloriesOfElfCarryTest.add(6000);
        totalCaloriesOfElfCarryTest.add(4000);
        totalCaloriesOfElfCarryTest.add(11000);
        totalCaloriesOfElfCarryTest.add(24000);
        totalCaloriesOfElfCarryTest.add(10000);
        assertEquals(24000,ElfCarryingMostCalories(totalCaloriesOfElfCarryTest));
    }

    @Test
    void totalOfElfCarryingCaloriesTest() {
        TreeSet<Integer> totalCaloriesOfElfCarryTest = new TreeSet<>();
        totalCaloriesOfElfCarryTest.add(6000);
        totalCaloriesOfElfCarryTest.add(4000);
        totalCaloriesOfElfCarryTest.add(11000);
        totalCaloriesOfElfCarryTest.add(24000);
        totalCaloriesOfElfCarryTest.add(10000);
        assertEquals(45000,top3ElfCarryingMostCalories(totalCaloriesOfElfCarryTest));

    }
}
