package org.example.Day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class NoSpace {
    public static void main(String[] args) {
        String fileName = "/day7Input.txt";
        //part1
        System.out.println("Sum of the total sizes of those directories: "+pipeLine(fileName));
        //part2
        System.out.println("Total size of most deletable directory: "+pipeLine2(fileName));
    }

    public static ArrayList<String> readFile(String fileName) {
        InputStream inputStream = NoSpace.class.getResourceAsStream(fileName);
        String line;
        ArrayList<String> terminalOutput = new ArrayList<>();
        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                while ((line = reader.readLine()) != null) {
                    terminalOutput.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return terminalOutput;
    }

    public static List<Directory> getAllDirectories(ArrayList<String> terminalLine) {
        Directory rootDirectory = new Directory(null, "/");

        Directory currentDirectory = rootDirectory;
        List<Directory> allDirectories = new ArrayList<>();
        allDirectories.add(rootDirectory);

        for (String line : terminalLine) {

            String[] lineItems = line.split(" ");
            if ("$".equals(lineItems[0])) {
                if ("cd".equals(lineItems[1])) {
                    if ("/".equals(lineItems[2])) {
                        currentDirectory = rootDirectory;
                    } else if ("..".equals(lineItems[2])) {
                        currentDirectory = currentDirectory.getParent();
                    } else {
                        currentDirectory = currentDirectory.getDirectory(lineItems[2]);
                    }
                }
            } else if ("dir".equals(lineItems[0])) {
                Directory directory = new Directory(currentDirectory, lineItems[1]);
                currentDirectory.addFile(directory);
                allDirectories.add(directory);
            } else {
                currentDirectory.addFile(new File(lineItems[1], Long.parseLong(lineItems[0])));
            }

        }

        return allDirectories;
    }
    //part1
    public static long getTotalSizeOfDirectories(List<Directory> allDirectories){
        long totalSize = 0;
        for (Directory directory: allDirectories) {
            long dirSize = directory.size();
            if(dirSize < 100000){
                totalSize += dirSize;
            }
        }
        return totalSize;
    }

    public static long pipeLine(String fileName){
        return getTotalSizeOfDirectories(
                getAllDirectories(
                        readFile(fileName)
                )
        );
    }

    //part2
    public static long getMostDeletableFileSize(List<Directory> allDirectories){

        long sizeOfRoot = allDirectories.get(0).size();
        long spaceLeft = 70000000 - sizeOfRoot;
        long spaceToRemove = 30000000 - spaceLeft;

        List<Long> deletableFileSizes = new ArrayList<>();

        for (Directory de : allDirectories) {
            if (de.size() > spaceToRemove) {
                deletableFileSizes.add(de.size());
            }
        }

        Collections.sort(deletableFileSizes);

        return deletableFileSizes.get(0);
    }
    public static long pipeLine2(String fileName){
        return getMostDeletableFileSize(
                getAllDirectories(
                        readFile(fileName)
                )
        );
    }
}
