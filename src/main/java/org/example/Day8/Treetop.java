package org.example.Day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Treetop {
    public static void main(String[] args) {
        String fileName = "/day8TestInput.txt";
        //part1
        //System.out.println("Sum of the total sizes of those directories: "+pipeLine(fileName));
        //part2
        //System.out.println("Total size of most deletable directory: "+pipeLine2(fileName));
        System.out.println(calculateTreesVisibleInInterior(captureTreeHeight(readFile(fileName))));
    }

    public static ArrayList<String> readFile(String fileName) {
        InputStream inputStream = Treetop.class.getResourceAsStream(fileName);
        String line;
        ArrayList<String> treesHeights = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = reader.readLine()) != null) {
                    treesHeights.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return treesHeights;
    }

    public static ArrayList<ArrayList<Integer>> captureTreeHeight(ArrayList<String> treeHeights) {
        ArrayList<ArrayList<Integer>> treeHeightsGrid = new ArrayList<>();
        for (String treeLine : treeHeights) {
            ArrayList<Integer> treeRow = new ArrayList<>();
            for (char digit : treeLine.toCharArray()) {
                int value = Character.getNumericValue(digit);
                treeRow.add(value);
            }
            treeHeightsGrid.add(treeRow);
        }
        return treeHeightsGrid;
    }

    public static int calculateTreesVisibleOnTheEdge(ArrayList<ArrayList<Integer>> treeHeightsGrid){
        int numOfTreeRows = treeHeightsGrid.size();
        int numOfTreeColumns = treeHeightsGrid.get(0).size();
        return (numOfTreeRows*2 + numOfTreeColumns*2)-4;
    }

    public static int calculateTreesVisibleInInterior(ArrayList<ArrayList<Integer>> treeHeightsGrid){
        int count = 0;
        int numOfTreeRows = treeHeightsGrid.size();
        int numOfTreeColumns = treeHeightsGrid.get(0).size();

        for(int i=1; i< numOfTreeRows-1; i++){

            for(int j=1; j<numOfTreeColumns-1; j++){
                boolean right = true;
                boolean left = true;
                boolean up = true;
                boolean down = true;

                int treeHeight = treeHeightsGrid.get(i).get(j);
                //Up
                int u = i-1;
                while (u >= 0){
                    if (treeHeight <= treeHeightsGrid.get(u).get(j)) {
                        up = false;
                        break;
                    }
                    u--;
                }

                //Down
                int d = i+1;
                while (d < numOfTreeColumns){
                    if (treeHeight <= treeHeightsGrid.get(d).get(j)) {
                        down = false;
                        break;
                    }
                    d++;
                }

                //Right
                int r = j+1;
                while (r < numOfTreeRows){
                    if (treeHeight <= treeHeightsGrid.get(i).get(r)) {
                        right = false;
                        break;
                    }
                    r++;
                }

                //left
                int l = j-1;
                while (l >= 0){
                    if (treeHeight <= treeHeightsGrid.get(i).get(l)) {
                        left = false;
                        break;
                    }
                    l--;
                }

                if( up || down || right || left ){
                    count++;
                }
            }
        }

        return count;
    }
}
