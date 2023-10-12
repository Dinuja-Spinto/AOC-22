package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {

    public static ArrayList<Integer> ElfCarryingTotalCalories(File file) throws FileNotFoundException{

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getPath());
        }

        String calorieOfAFood;
        int elfTotalCalories=0;
        ArrayList<Integer> totalCaloriesOfElfCarry = new ArrayList<Integer>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            while ((calorieOfAFood = reader.readLine()) != null) {
                if(!calorieOfAFood.isEmpty()){
                    elfTotalCalories += Integer.parseInt(calorieOfAFood);
                }else{
                    totalCaloriesOfElfCarry.add(elfTotalCalories);
                    elfTotalCalories=0;
                }
            }
            if(elfTotalCalories != 0){
                totalCaloriesOfElfCarry.add(elfTotalCalories);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return totalCaloriesOfElfCarry;
    }

    //partOne
    public static String ElfCarryingMostCalories(ArrayList<Integer> totalCaloriesOfEachElf){
        int maxCarrying = Collections.max(totalCaloriesOfEachElf);
        return "Total Calories is that Elf carrying: "+maxCarrying;
    }

    //partTwo
    public static int top3ElfCarryingMostCalories(ArrayList<Integer> totalCaloriesOfEachElf){
        int top1 = 0;
        int top2 = 0;
        int top3 = 0;

        for (int num : totalCaloriesOfEachElf) {
            if (num > top1) {
                top3 = top2;
                top2 = top1;
                top1 = num;
            } else if (num > top2) {
                top3 = top2;
                top2 = num;
            } else if (num > top3) {
                top3 = num;
            }
        }
        return top1 + top2 + top3;
    }
}
