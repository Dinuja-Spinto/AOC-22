package org.example.Day8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Treetop {
    public static void main(String[] args) {
        String fileName = "/day8Input.txt";
        //part1
        System.out.println("Trees visible from outside the grid: "+pipeLine(fileName));
        //part2
        System.out.println("Highest scenic score possible for any tree: "+pipeLine2(fileName));
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

    //--part1--
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
                /*boolean right = true;
                boolean left = true;
                boolean up = true;
                boolean down = true;*/

                //Use bitWise Operations
                // Left Right Down Up
                //  1 1 1 1
                int visible= 0xF; //1111

                int treeHeight = treeHeightsGrid.get(i).get(j);
                //Up
                int u = i-1;
                while (u >= 0){
                    if (treeHeight <= treeHeightsGrid.get(u).get(j)) {
                        //up = false;
                        int mask = 1 << (0);
                        visible ^= mask;
                        break;
                    }
                    u--;
                }

                //Down
                int d = i+1;
                while (d < numOfTreeColumns){
                    if (treeHeight <= treeHeightsGrid.get(d).get(j)) {
                        //down = false;
                        int mask = 1 << (1);
                        visible ^= mask;
                        break;
                    }
                    d++;
                }

                //Right
                int r = j+1;
                while (r < numOfTreeRows){
                    if (treeHeight <= treeHeightsGrid.get(i).get(r)) {
                        //right = false;
                        int mask = 1 << (2);
                        visible ^= mask;
                        break;
                    }
                    r++;
                }

                //left
                int l = j-1;
                while (l >= 0){
                    if (treeHeight <= treeHeightsGrid.get(i).get(l)) {
                        //left = false;
                        int mask = 1 << (3);
                        visible ^= mask;
                        break;
                    }
                    l--;
                }

                /*if( up || down || right || left ){
                    count++;
                }*/
                if(visible != 0){
                    count++;
                }
            }
        }

        return count;
    }

    public static int totalVisibleTrees(ArrayList<ArrayList<Integer>> treeHeightsGrid){
        return calculateTreesVisibleOnTheEdge(treeHeightsGrid) + calculateTreesVisibleInInterior(treeHeightsGrid);
    }

    public static int pipeLine(String fileName){
        return totalVisibleTrees(
                captureTreeHeight(
                        readFile(fileName)
                )
        );
    }

    //--part2--
    public static int calculateHighestScenicScore(ArrayList<ArrayList<Integer>> treeHeightsGrid){
        int highestScenicScore = 0;
        int scenicScore;
        int numOfTreeRows = treeHeightsGrid.size();
        int numOfTreeColumns = treeHeightsGrid.get(0).size();

        for(int i=1; i< numOfTreeRows-1; i++){

            for(int j=1; j<numOfTreeColumns-1; j++){

                int treeHeight = treeHeightsGrid.get(i).get(j);
                //Up
                int u = i-1;
                int upScore = 0;
                while (u >= 0){
                    upScore++;
                    if (treeHeight <= treeHeightsGrid.get(u).get(j)) {
                        break;
                    }
                    u--;
                }

                //Down
                int d = i+1;
                int downScore = 0;
                while (d < numOfTreeColumns){
                    downScore++;
                    if (treeHeight <= treeHeightsGrid.get(d).get(j)) {
                        break;
                    }
                    d++;
                }

                //Right
                int r = j+1;
                int rightScore = 0;
                while (r < numOfTreeRows){
                    rightScore++;
                    if (treeHeight <= treeHeightsGrid.get(i).get(r)) {
                        break;
                    }
                    r++;
                }

                //left
                int l = j-1;
                int leftScore = 0;
                while (l >= 0){
                    leftScore++;
                    if (treeHeight <= treeHeightsGrid.get(i).get(l)) {
                        break;
                    }
                    l--;
                }

                scenicScore = downScore * upScore * leftScore * rightScore;

                if(scenicScore > highestScenicScore){
                    highestScenicScore = scenicScore;
                }

            }
        }

        return highestScenicScore;
    }

    public static int pipeLine2(String fileName){
        return calculateHighestScenicScore(
                captureTreeHeight(
                        readFile(fileName)
                )
        );
    }
}
