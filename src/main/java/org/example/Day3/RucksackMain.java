package org.example.Day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class RucksackMain {
    public static void main(String[] args) {
        String fileName = "/day3Input.txt";

        //part1
        System.out.println("sum of the priorities: "+pipeLine(fileName));

        //part2
        System.out.println("sum of the common items priorities: "+pipeline_2(fileName));


    }
    //--part1--
    public static ArrayList<String> readFile(String fileName) {
        InputStream inputStream = RucksackMain.class.getResourceAsStream(fileName);
        String rucksackItems;
        ArrayList<String> allItemsInEachRuck = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((rucksackItems = reader.readLine()) != null) {
                    allItemsInEachRuck.add(rucksackItems);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allItemsInEachRuck;
    }

    public static ArrayList<Rucksack> splitItemOfCompartments(ArrayList<String> rucks){
        ArrayList<Rucksack> rackSucks = new ArrayList<>();
        for(String allItems : rucks){
            int length = allItems.length();
            int middle = length / 2;
            Rucksack rucksack = new Rucksack();
            rucksack.setCompartmentOne(allItems.substring(0,middle));
            rucksack.setCompartmentTwo(allItems.substring(middle));
            rackSucks.add(rucksack);
        }
        return rackSucks;
    }

    public static ArrayList<String> findSameItemInCompartments(ArrayList<Rucksack> rucks){
        ArrayList<String> sameItemInEachRack = new ArrayList<>();
        for(Rucksack ruckSack : rucks){
            String compartmentOne = ruckSack.getCompartmentOne();
            String compartmentTwo = ruckSack.getCompartmentTwo();

            Set<Character> setOne = compartmentOne.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
            Set<Character> setTwo = compartmentTwo.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

            setOne.retainAll(setTwo);

            String sameItem = setOne.stream().map(Object::toString).collect(Collectors.joining(""));

            sameItemInEachRack.add(sameItem);
        }
        return sameItemInEachRack;
    }

    public static ArrayList<Integer> setPriorityForEachItem(ArrayList<String> items){
        ArrayList<Integer> PriorityOfSameItem = new ArrayList<>();
        for(String item : items){
            PriorityOfSameItem.add(calculatePriority(item));
        }
        return PriorityOfSameItem;
    }

    public static int calculatePriority(String item){
        int ascii = item.charAt(0);
        int result = 0;
        if( ascii <= 122 && ascii >= 97 ){
            result = ascii - 96;
        } else if ( ascii <= 90 && ascii >= 65 ) {
            result = ascii - 38;
        }
        return result;
    }

    public static int sumOfPriorityOfTheItem(ArrayList<Integer> priorityForEachItem){
        int total=0;
        for(Integer pNum : priorityForEachItem) {
            total += pNum;
        }
        return total;
    }

    public static ArrayList<Rucksack> ruckSacks(String fileName){
        return splitItemOfCompartments(
                readFile(fileName)
        );
    }

    public static int pipeLine(String fileName){
        return sumOfPriorityOfTheItem(
                setPriorityForEachItem(
                    findSameItemInCompartments(
                            ruckSacks(fileName))));
    }


    //--part2--
    public static ArrayList<ElvesGroup> groupElves(ArrayList<Rucksack> rucks){
        ArrayList<ElvesGroup> elvesGroups = new ArrayList<>();
        for (int i = 0; i < rucks.size(); i += 3) {
            elvesGroups.add(new ElvesGroup(rucks.get(i), rucks.get(i+1), rucks.get(i+2)));
        }
        return elvesGroups;
    }

    public static ArrayList<String> findCommanItemOfGroup(ArrayList<ElvesGroup> groups){
        ArrayList<String> commanItemOfGroup = new ArrayList<>();
        for(ElvesGroup group : groups){
            String ruckOne = group.getElvOne().getCompartmentOne()+group.getElvOne().getCompartmentTwo();
            String ruckTwo = group.getElvTwo().getCompartmentOne()+group.getElvTwo().getCompartmentTwo();
            String ruckThree = group.getElvThree().getCompartmentOne()+group.getElvThree().getCompartmentTwo();

            Set<Character> commonItem = ruckOne.chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> ruckTwo.contains(String.valueOf(c)))
                    .filter(c -> ruckThree.contains(String.valueOf(c)))
                    .collect(Collectors.toSet());

            String sameItem = commonItem.stream().map(Object::toString).collect(Collectors.joining(""));

            commanItemOfGroup.add(sameItem);
        }
        return commanItemOfGroup;
    }

    public static int pipeline_2(String fileName){
        return sumOfPriorityOfTheItem(
                setPriorityForEachItem(
                        findCommanItemOfGroup(
                                groupElves(
                                ruckSacks(fileName)))));
    }

}
