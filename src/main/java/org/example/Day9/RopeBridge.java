package org.example.Day9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RopeBridge {

    public static void main(String[] args) {
        String fileName = "/day9TestInput.txt";
        //part1
        //System.out.println(""+pipeLine(fileName));
        //part2
        //System.out.println(""+pipeLine2(fileName));
        System.out.println(movesCoverByT(captureMoves(readFile(fileName))));
    }

    public static ArrayList<String> readFile(String fileName) {
        InputStream inputStream = RopeBridge.class.getResourceAsStream(fileName);
        String line;
        ArrayList<String> moves = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = reader.readLine()) != null) {
                    moves.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return moves;
    }

    public static ArrayList<Moves> captureMoves(ArrayList<String> motions){
        ArrayList<Moves> moves = new ArrayList<>();
        for (String move: motions) {
            String[] motionsMove = move.split(" ");
            moves.add(new Moves( motionsMove[0], Integer.parseInt(motionsMove[1])));
        }
        return moves;
    }

    public static int movesCoverByT(ArrayList<Moves> moves){
        int totalHMoves=0;
        for (Moves move: moves) {
            if(move.getSteps() > 3){
                totalHMoves += ( move.getSteps()- 1 );
            }

        }
        return totalHMoves;
    }
}
