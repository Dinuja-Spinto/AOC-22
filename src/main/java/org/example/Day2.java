package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Day2 {
    //PART1 const
    public enum OpponentChoice {
        ROCK("A"),
        PAPER("B"),
        Scissors("C");

        private final String code;

        OpponentChoice(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public enum ResponseChoice {
        ROCK("X"),
        PAPER("Y"),
        Scissors("Z");

        private final String code;

        ResponseChoice(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    //PART2 const
    public enum RoundNeedsToEnd {
        LOSE("X"),
        DRAW("Y"),
        WIN("Z");

        private final String code;

        RoundNeedsToEnd(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static void main(String[] args) {
        String fileName = "/day2Input.txt";
        System.out.println("Total Score Part 1: " + pipeLine(fileName));

        System.out.println("Total Score Part 2: " + pipeLine_2(fileName));
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

            if ((elf1.equals(OpponentChoice.ROCK.getCode()) && elf2.equals(ResponseChoice.PAPER.getCode())) || (elf1.equals(OpponentChoice.PAPER.getCode()) && elf2.equals(ResponseChoice.Scissors.getCode()))
                    || (elf1.equals(OpponentChoice.Scissors.getCode()) && elf2.equals(ResponseChoice.ROCK.getCode()))) {
                outComeOfEachRound.add(6);
            } else if ((elf1.equals(OpponentChoice.ROCK.getCode()) && elf2.equals(ResponseChoice.Scissors.getCode())) || (elf1.equals(OpponentChoice.PAPER.getCode()) && elf2.equals(ResponseChoice.ROCK.getCode()))
                    || (elf1.equals(OpponentChoice.Scissors.getCode()) && elf2.equals(ResponseChoice.PAPER.getCode()))) {
                outComeOfEachRound.add(0);
            } else if ((elf1.equals(OpponentChoice.ROCK.getCode()) && elf2.equals(ResponseChoice.ROCK.getCode())) || (elf1.equals(OpponentChoice.PAPER.getCode()) && elf2.equals(ResponseChoice.PAPER.getCode()))
                    || (elf1.equals(OpponentChoice.Scissors.getCode()) && elf2.equals(ResponseChoice.Scissors.getCode()))) {
                outComeOfEachRound.add(3);
            }

        }

        return outComeOfEachRound;
    }

    public static ArrayList<Integer> findSelectedShapeScoreOfEachRound(ArrayList<ArrayList<String>> eachRoundInput) {
        ArrayList<Integer> selectedShapeScoreOfEachRound = new ArrayList<>();

        for (ArrayList<String> input : eachRoundInput) {
            String shape = input.get(1);
            if (Objects.equals(ResponseChoice.ROCK.getCode(), shape)) {
                selectedShapeScoreOfEachRound.add(1);
            } else if (Objects.equals(ResponseChoice.PAPER.getCode(), shape)) {
                selectedShapeScoreOfEachRound.add(2);
            } else if (Objects.equals(ResponseChoice.Scissors.getCode(), shape)) {
                selectedShapeScoreOfEachRound.add(3);
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
            if (Objects.equals(RoundNeedsToEnd.LOSE.getCode(), shape)) {
                outcomeOfEachRoundPart2.add(0);
            } else if (Objects.equals(RoundNeedsToEnd.DRAW.getCode(), shape)) {
                outcomeOfEachRoundPart2.add(3);
            } else if (Objects.equals(RoundNeedsToEnd.WIN.getCode(), shape)) {
                outcomeOfEachRoundPart2.add(6);
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
            if (Objects.equals(RoundNeedsToEnd.WIN.getCode(), roundOutCome)) {
                if (Objects.equals(OpponentChoice.ROCK.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(2);
                } else if (Objects.equals(OpponentChoice.PAPER.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(3);
                } else if (Objects.equals(OpponentChoice.Scissors.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(1);
                }
            } else if (Objects.equals(RoundNeedsToEnd.LOSE.getCode(), roundOutCome)) {
                if (Objects.equals(OpponentChoice.ROCK.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(3);
                } else if (Objects.equals(OpponentChoice.PAPER.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(1);
                } else if (Objects.equals(OpponentChoice.Scissors.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(2);
                }
            } else if (Objects.equals(RoundNeedsToEnd.DRAW.getCode(), roundOutCome)) {
                if (Objects.equals(OpponentChoice.ROCK.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(1);
                } else if (Objects.equals(OpponentChoice.PAPER.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(2);
                } else if (Objects.equals(OpponentChoice.Scissors.getCode(), oppoChoose)) {
                    outComeOfSelectShapeEachRound.add(3);
                }
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