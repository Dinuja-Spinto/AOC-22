package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.example.Day1.*;
import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void totalCaloriesOfElf(){
        String filePath = "src/test/java/org/example/day1TestInput.txt";
        File inputFile = new File(filePath);

        ArrayList<Integer> totalCaloriesOfElfCarryTest = new ArrayList<>();
        totalCaloriesOfElfCarryTest.add(6000);
        totalCaloriesOfElfCarryTest.add(4000);
        totalCaloriesOfElfCarryTest.add(11000);
        totalCaloriesOfElfCarryTest.add(24000);
        totalCaloriesOfElfCarryTest.add(10000);

        try {
            assertEquals(totalCaloriesOfElfCarryTest,ElfCarryingTotalCalories(inputFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void elfCarryingMostCaloriesTest() {
        ArrayList<Integer> totalCaloriesOfElfCarryTest = new ArrayList<>();
        totalCaloriesOfElfCarryTest.add(6000);
        totalCaloriesOfElfCarryTest.add(4000);
        totalCaloriesOfElfCarryTest.add(11000);
        totalCaloriesOfElfCarryTest.add(24000);
        totalCaloriesOfElfCarryTest.add(10000);
        assertEquals("Total Calories is that Elf carrying: 24000",ElfCarryingMostCalories(totalCaloriesOfElfCarryTest));
    }

    @Test
    void totalOfElfCarryingCaloriesTest() {
        ArrayList<Integer> totalCaloriesOfElfCarryTest = new ArrayList<>();
        totalCaloriesOfElfCarryTest.add(6000);
        totalCaloriesOfElfCarryTest.add(4000);
        totalCaloriesOfElfCarryTest.add(11000);
        totalCaloriesOfElfCarryTest.add(24000);
        totalCaloriesOfElfCarryTest.add(10000);
        assertEquals(45000,top3ElfCarryingMostCalories(totalCaloriesOfElfCarryTest));

    }
}