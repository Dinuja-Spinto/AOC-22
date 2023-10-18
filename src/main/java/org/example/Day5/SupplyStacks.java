package org.example.Day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStacks {
    public static void main(String[] args) {
        
        String fileName = "/day5Input.txt";

        //part1
        System.out.println("After the rearrangement procedure completes, the crate ends up on top of each stack: "+pipeLine(fileName));
    }

    private static ArrayList<Stack<String>> getTestStacks() {
        ArrayList<Stack<String>> stackListTest = new ArrayList<>();

        Stack<String> stack1Test = new Stack<>();
        stack1Test.push("Z");
        stack1Test.push("N");

        Stack<String> stack2Test = new Stack<>();
        stack2Test.push("M");
        stack2Test.push("C");
        stack2Test.push("D");

        Stack<String> stack3Test = new Stack<>();
        stack3Test.push("P");

        stackListTest.add(stack1Test);
        stackListTest.add(stack2Test);
        stackListTest.add(stack3Test);
        return stackListTest;
    }

    private static ArrayList<Stack<String>> getStacks() {
        ArrayList<Stack<String>> stackList = new ArrayList<>();

        Stack<String> stack1 = new Stack<>();
        stack1.push("B");
        stack1.push("P");
        stack1.push("N");
        stack1.push("Q");
        stack1.push("H");
        stack1.push("D");
        stack1.push("R");
        stack1.push("T");

        Stack<String> stack2 = new Stack<>();
        stack2.push("W");
        stack2.push("G");
        stack2.push("B");
        stack2.push("J");
        stack2.push("T");
        stack2.push("V");

        Stack<String> stack3 = new Stack<>();
        stack3.push("N");
        stack3.push("R");
        stack3.push("H");
        stack3.push("D");
        stack3.push("S");
        stack3.push("V");
        stack3.push("M");
        stack3.push("Q");

        Stack<String> stack4 = new Stack<>();
        stack4.push("P");
        stack4.push("Z");
        stack4.push("N");
        stack4.push("M");
        stack4.push("C");

        Stack<String> stack5 = new Stack<>();
        stack5.push("D");
        stack5.push("Z");
        stack5.push("B");

        Stack<String> stack6 = new Stack<>();
        stack6.push("V");
        stack6.push("C");
        stack6.push("W");
        stack6.push("Z");

        Stack<String> stack7 = new Stack<>();
        stack7.push("G");
        stack7.push("Z");
        stack7.push("N");
        stack7.push("C");
        stack7.push("V");
        stack7.push("Q");
        stack7.push("L");
        stack7.push("S");

        Stack<String> stack8 = new Stack<>();
        stack8.push("L");
        stack8.push("G");
        stack8.push("J");
        stack8.push("M");
        stack8.push("D");
        stack8.push("N");
        stack8.push("V");

        Stack<String> stack9 = new Stack<>();
        stack9.push("T");
        stack9.push("P");
        stack9.push("M");
        stack9.push("F");
        stack9.push("Z");
        stack9.push("C");
        stack9.push("G");

        stackList.add(stack1);
        stackList.add(stack2);
        stackList.add(stack3);
        stackList.add(stack4);
        stackList.add(stack5);
        stackList.add(stack6);
        stackList.add(stack7);
        stackList.add(stack8);
        stackList.add(stack9);

        return stackList;
    }

    public static ArrayList<String> readFile(String fileName) {
        InputStream inputStream = SupplyStacks.class.getResourceAsStream(fileName);
        String movement;
        ArrayList<String> allMovement = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((movement = reader.readLine()) != null) {
                    allMovement.add(movement);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allMovement;
    }

    public static ArrayList<int[]> getMoves(ArrayList<String> instructions){
        ArrayList<int[]> moves = new ArrayList<>();
        for (String instruction: instructions) {
            moves.add(fetchNumbersFromInstruction(instruction));
        }
        return moves;
    }

    public static int[] fetchNumbersFromInstruction(String instruction){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(instruction);
        int noOfItems = 0;
        int fromStack = 0;
        int toStack = 0;

        int count = 0;
        while (matcher.find() && count < 3) {
            int number = Integer.parseInt(matcher.group());
            if (count == 0) {
                noOfItems = number;
            } else if (count == 1) {
                fromStack = number;
            } else if (count == 2) {
                toStack = number;
            }
            count++;
        }
        return new int[]{noOfItems, fromStack,toStack};
    }


    public static ArrayList<Stack<String>> getStacksAfterRearrange(ArrayList<Stack<String>> stackListTest, ArrayList<int[]> instructions ){
        for (int[] instruction: instructions) {
            int noOfItems = instruction[0];
            int fromStack = instruction[1];
            int toStack = instruction[2];

            Stack<String> from = stackListTest.get(fromStack-1);
            Stack<String> to = stackListTest.get(toStack-1);

            for(int i = 0; i < noOfItems; i++){
                to.push(from.pop());
            }

        }
        return stackListTest;
    }

    public static String getTopOfEachStack(ArrayList<Stack<String>> reArrangedStacks){
        StringBuilder topOfEach= new StringBuilder();
        for (Stack<String> stack: reArrangedStacks) {
            topOfEach.append(stack.peek());
        }
        return topOfEach.toString();
    }

    public static String pipeLine(String fileName){
        return getTopOfEachStack(
                getStacksAfterRearrange( getStacks(),
                        getMoves(
                                readFile(fileName)
                        )

                )
        );
    }

}
