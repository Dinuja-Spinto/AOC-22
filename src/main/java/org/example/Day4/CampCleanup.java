package org.example.Day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CampCleanup {

    public interface InterceptionCondition{
        boolean doesConditionSatisfy(String range1, String range2);
    }

    public static void main(String[] args) {
        String fileName = "/day4Input.txt";

        //--part1
        System.out.println("Total of pairs does one range fully contain the other: "+pipeLine(fileName));

        //--part2
        System.out.println("Total of assignment pairs do the ranges overlap: "+pipeLine_2(fileName));
    }
    //--part1--
    public static ArrayList<String> readFile(String fileName) {
        InputStream inputStream = CampCleanup.class.getResourceAsStream(fileName);
        String assignmentsForEachPair;
        ArrayList<String> allAssignmentsForEachPair = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((assignmentsForEachPair = reader.readLine()) != null) {
                    allAssignmentsForEachPair.add(assignmentsForEachPair);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allAssignmentsForEachPair;
    }

    public static ArrayList<ElvesPair> getElvesPairs(ArrayList<String> elvesPairList){
        ArrayList<ElvesPair> elvesPairs = new ArrayList<>();
        for(String elvesPair : elvesPairList){
            String[] ranges = elvesPair.split(",");
            ElvesPair elvesP = new ElvesPair(new Elf(ranges[0]), new Elf(ranges[1]));
            elvesPairs.add(elvesP);
        }
        return elvesPairs;
    }

    public static boolean isOverLapping(String rangeOfIDs1, String rangeOfIDs2) {

        int Range1Start = Integer.parseInt(rangeOfIDs1.split("-")[0]);
        int Range1end= Integer.parseInt(rangeOfIDs1.split("-")[1]);

        int Range2Start = Integer.parseInt(rangeOfIDs2.split("-")[0]);
        int Range2end = Integer.parseInt(rangeOfIDs2.split("-")[1]);

        return ((Range2Start <= Range1Start && Range1end <= Range2end) ||
                (Range1Start <= Range2Start && Range2end <= Range1end));
    }


    public static int totalOfOverlappingPairs(ArrayList<ElvesPair> elvesPairs, InterceptionCondition condition){
        int totalOverlaps = 0;
        for( ElvesPair elvesPair : elvesPairs ){
            if( condition.doesConditionSatisfy(elvesPair.getElf1().getRangeOfIDs(), elvesPair.getElf2().getRangeOfIDs())){
                totalOverlaps += 1;
            }
        }
        return totalOverlaps;
    }

    public static int pipeLine(String fileName){
        return totalOfOverlappingPairs(
                getElvesPairs(
                        readFile(fileName)
                ), CampCleanup::isOverLapping
        );
    }

    //--PartTwo--
    public static boolean isOverLapping_2(String rangeOfIDs1, String rangeOfIDs2) {

        int Range1Start = Integer.parseInt(rangeOfIDs1.split("-")[0]);
        int Range1end= Integer.parseInt(rangeOfIDs1.split("-")[1]);

        int Range2Start = Integer.parseInt(rangeOfIDs2.split("-")[0]);
        int Range2end = Integer.parseInt(rangeOfIDs2.split("-")[1]);

        return ( Range1end >= Range2Start && Range1Start <= Range2end );
    }

   /* public static int totalOfOverlappingPairs_2(ArrayList<ElvesPair> elvesPairs){
        int totalOverlaps = 0;
        for( ElvesPair elvesPair : elvesPairs ){
            if( isOverLapping_2(elvesPair.getElf1().getRangeOfIDs(), elvesPair.getElf2().getRangeOfIDs())){
                totalOverlaps += 1;
            }
        }

        return totalOverlaps;
    }*/

    public static int pipeLine_2(String fileName){
        return totalOfOverlappingPairs(
                getElvesPairs(
                        readFile(fileName)
                ),CampCleanup::isOverLapping_2
        );
    }
}
