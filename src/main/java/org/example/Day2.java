package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Day2 {
    public static void main(String[] args) {
        String fileName = "/day2Input.txt";
        System.out.println("Total Score Part 1: "+pipeLine(fileName));

        System.out.println("Total Score Part 2: "+pipeLine_2(fileName));
    }

    //Part1
    public static ArrayList<ArrayList<String>> readFile(String fileName) {
        InputStream inputStream = Day2.class.getResourceAsStream(fileName);
        ArrayList<ArrayList<String>> eachRoundInput = new ArrayList<>();

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] inputs = line.split(" ");
                    ArrayList<String> row = new ArrayList<>();
                    row.add(inputs[0]);
                    row.add(inputs[1]);
                    // Add the row to the 2D ArrayList
                    eachRoundInput.add(row);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return eachRoundInput;
    }

    public static ArrayList<Integer> findOutComeScoreOfEachRound(ArrayList<ArrayList<String>> eachRoundInput) {
        ArrayList<Integer> outComeOfEachRound = new ArrayList<>();

        for (ArrayList<String> input : eachRoundInput) {
            int j = 0;
            String elf1 = input.get(j);
            String elf2 = input.get(++j);

            if ((elf1.equals("A") && elf2.equals("Y")) || (elf1.equals("B") && elf2.equals("Z"))
                    || (elf1.equals("C") && elf2.equals("X"))) {
                outComeOfEachRound.add(6);
            } else if ((elf1.equals("A") && elf2.equals("Z")) || (elf1.equals("B") && elf2.equals("X"))
                    || (elf1.equals("C") && elf2.equals("Y"))) {
                outComeOfEachRound.add(0);
            } else if ((elf1.equals("A") && elf2.equals("X")) || (elf1.equals("B") && elf2.equals("Y"))
                    || (elf1.equals("C") && elf2.equals("Z"))) {
                outComeOfEachRound.add(3);
            }

        }

        return outComeOfEachRound;
    }

    public static ArrayList<Integer> findSelectedShapeScoreOfEachRound(ArrayList<ArrayList<String>> eachRoundInput) {
        ArrayList<Integer> selectedShapeScoreOfEachRound = new ArrayList<>();

        for (ArrayList<String> input : eachRoundInput) {
            String shape = input.get(1);
            switch (shape) {
                case "X":
                    selectedShapeScoreOfEachRound.add(1);
                    break;
                case "Y":
                    selectedShapeScoreOfEachRound.add(2);
                    break;
                case "Z":
                    selectedShapeScoreOfEachRound.add(3);
                    break;
            }
        }

        return selectedShapeScoreOfEachRound;
    }

    public static ArrayList<Integer> findTotalScoreEachRound(ArrayList<Integer> outComeOfEachRound,
                                                             ArrayList<Integer> selectedShapeScoreOfEachRound) {

        if (outComeOfEachRound.size() != selectedShapeScoreOfEachRound.size()) {
            throw new IllegalArgumentException("ArrayLists must have the same size.");
        }

        ArrayList<Integer> totalScoreEachRound = new ArrayList<>();

        for (int i = 0; i < outComeOfEachRound.size(); i++) {
            int sum = outComeOfEachRound.get(i) + selectedShapeScoreOfEachRound.get(i);
            totalScoreEachRound.add(sum);
        }

        return totalScoreEachRound;

    }

    public static int findTotalScore(ArrayList<Integer> totalScoreEachRound) {
        int totalScore = 0;

        for (int score : totalScoreEachRound) {
            totalScore += score;
        }

        return totalScore;
    }

    public static int pipeLine(String fileName) {
        ArrayList<ArrayList<String>> inputData = readFile(fileName);
        return findTotalScore(
                findTotalScoreEachRound(findOutComeScoreOfEachRound(inputData),
                        findSelectedShapeScoreOfEachRound(inputData))
        );
    }


    //part2
    public static ArrayList<Integer> findOutComeScoreOfEachRoundPart2(ArrayList<ArrayList<String>> eachRoundInput) {
        ArrayList<Integer> outcomeOfEachRoundPart2 = new ArrayList<>();

        for (ArrayList<String> input : eachRoundInput) {
            String shape = input.get(1);
            switch (shape) {
                case "X":
                    outcomeOfEachRoundPart2.add(0);
                    break;
                case "Y":
                    outcomeOfEachRoundPart2.add(3);
                    break;
                case "Z":
                    outcomeOfEachRoundPart2.add(6);
                    break;
            }
        }

        return outcomeOfEachRoundPart2;
    }

    public static ArrayList<Integer> findSelectedShapeScoreOfEachRoundPart2(ArrayList<ArrayList<String>> eachRoundInput) {
        ArrayList<Integer> outComeOfSelectShapeEachRound = new ArrayList<>();

        for (ArrayList<String> input : eachRoundInput) {
            int j = 0;
            String oppoChoose = input.get(j);
            String roundOutCome = input.get(++j);
            switch (roundOutCome) {
                case "Z":
                    switch (oppoChoose) {
                        case "A":
                            outComeOfSelectShapeEachRound.add(2);
                            break;
                        case "B":
                            outComeOfSelectShapeEachRound.add(3);
                            break;
                        case "C":
                            outComeOfSelectShapeEachRound.add(1);
                            break;
                    }
                    break;
                case "X":
                    switch (oppoChoose) {
                        case "A":
                            outComeOfSelectShapeEachRound.add(3);
                            break;
                        case "B":
                            outComeOfSelectShapeEachRound.add(1);
                            break;
                        case "C":
                            outComeOfSelectShapeEachRound.add(2);
                            break;
                    }
                    break;
                case "Y":
                    switch (oppoChoose) {
                        case "A":
                            outComeOfSelectShapeEachRound.add(1);
                            break;
                        case "B":
                            outComeOfSelectShapeEachRound.add(2);
                            break;
                        case "C":
                            outComeOfSelectShapeEachRound.add(3);
                            break;
                    }
                    break;
            }
        }
        return outComeOfSelectShapeEachRound;
    }

    public static int pipeLine_2(String fileName) {
        ArrayList<ArrayList<String>> inputData = readFile(fileName);
        return findTotalScore(
                findTotalScoreEachRound(findOutComeScoreOfEachRoundPart2(inputData),
                        findSelectedShapeScoreOfEachRoundPart2(inputData))
        );
    }
}