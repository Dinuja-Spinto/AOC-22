package org.example.Day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class TuningTrouble {

    public static final int DIF_NO_OF_LAST_CHAR = 4;

    public static void main(String[] args) {
        String fileName = "/day6Input.txt";
        System.out.println(pipeLine(fileName));
    }

    public static String readFile(String fileName){
        InputStream inputStream = TuningTrouble.class.getResourceAsStream(fileName);
        String dataStream;
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                dataStream = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataStream;
    }

    public static int charactersToBeProcessedBeforeFirstMarker(String dataStream) {
        StringBuilder marker = new StringBuilder();

        int numOfCharToProcess=0;
        //sliding window
        for (int i = 0; i < DIF_NO_OF_LAST_CHAR; i++) {
            marker.append(dataStream.charAt(i));
        }

        for (int i = DIF_NO_OF_LAST_CHAR; i < dataStream.length(); i++) {
            marker.append(dataStream.charAt(i));
            marker.deleteCharAt(0);
            if(hasNoDuplicateChars(marker.toString())){
                numOfCharToProcess = i+1;
                break;
            }
        }

        return numOfCharToProcess;
    }

    public static boolean hasNoDuplicateChars(String input) {
        Set<Character> duplicateChars = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (duplicateChars.contains(c)) {
                return false;
            }
            duplicateChars.add(c);
        }
        return true;
    }

    public static int pipeLine(String fileName){
        return charactersToBeProcessedBeforeFirstMarker(
                readFile(fileName)
        );
    }

}
