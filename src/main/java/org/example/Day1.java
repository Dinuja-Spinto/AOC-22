package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day1 {
    //part one
    public static String ElfCarryingMostCalories(File file) throws FileNotFoundException{

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int maxCarrying = Collections.max(totalCaloriesOfElfCarry);

        return "Total Calories is that Elf carrying: "+maxCarrying;
    }

}
