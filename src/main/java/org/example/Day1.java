package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Day1 {

    public static void main(String[] args) {
        String fileName = "/day1Input.txt";

        System.out.println("Total Max Calories: "+pipeLine_1(fileName));
        System.out.println("Total of top 3 Calories: "+pipeLine_2(fileName));
    }

    public static ArrayList<String> ElfCarryingCalories(String fileName) {
        InputStream inputStream = Day1.class.getResourceAsStream(fileName);
        String calorieOfAFood;
        ArrayList<String> allCaloriesOfElfCarry = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((calorieOfAFood = reader.readLine()) != null) {
                    allCaloriesOfElfCarry.add(calorieOfAFood);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allCaloriesOfElfCarry;
    }

    //partOne
    public static TreeSet<Integer> calculateTotalCarryingOfElf(ArrayList<String> allCaloriesOfElfCarry){
        TreeSet<Integer> totalCaloriesElfCarry = new TreeSet<>();
        int elfTotalCalories = 0;
        for (String calorieOfAFood : allCaloriesOfElfCarry) {
            if(!calorieOfAFood.isEmpty()){
                elfTotalCalories += Integer.parseInt(calorieOfAFood);
            }else{
                totalCaloriesElfCarry.add(elfTotalCalories);
                elfTotalCalories=0;
            }
        }
        if(elfTotalCalories != 0){
            totalCaloriesElfCarry.add(elfTotalCalories);
        }
        return totalCaloriesElfCarry;
    }

    public static int ElfCarryingMostCalories(TreeSet<Integer> totalCaloriesOfEachElf){
        return totalCaloriesOfEachElf.last();
    }

    //partTwo
    public static int top3ElfCarryingMostCalories(TreeSet<Integer> totalCaloriesOfEachElf){
        int top3Total = 0;
        int topCount = 3;

        Iterator<Integer> iteratorTotCal = totalCaloriesOfEachElf.descendingIterator();

        for (int i = 0; i < topCount && iteratorTotCal.hasNext(); i++) {
            int max = iteratorTotCal.next();
            top3Total += max;
        }

        return top3Total;
    }

    public static int pipeLine_1(String file) {

        return ElfCarryingMostCalories(
                calculateTotalCarryingOfElf(
                        ElfCarryingCalories(file)
                ));
    }

    public static int pipeLine_2(String file) {
        return top3ElfCarryingMostCalories(
                calculateTotalCarryingOfElf(
                        ElfCarryingCalories(file)
                ));
    }
}
