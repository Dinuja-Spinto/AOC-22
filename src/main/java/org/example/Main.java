/*
package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.example.Day1.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //Day1
        String filePath = "src/main/resources/day1Input.txt";
        File inputFile = new File(filePath);

        try {

            ArrayList<Integer> totalCaloriesOfEachElf = ElfCarryingTotalCalories(inputFile);
            //part1
            System.out.println(ElfCarryingMostCalories(totalCaloriesOfEachElf));
            //part2
            System.out.println("Total of Top three Elves carrying the most Calories: "+top3ElfCarryingMostCalories(totalCaloriesOfEachElf));

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }

    }
}*/
