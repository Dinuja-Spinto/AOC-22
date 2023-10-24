package org.example.Day7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class NoSpace {

    public enum TerminalCmd{
        CURRENT_DIRECTORY("cd"),
        ROOT("/"),
        DIRECTORY_NAME("dir"),
        CMD_PROMPT("$"),
        NAVIGATE_UP("..");
        private final String cmd;
        TerminalCmd(String cmd) {
            this.cmd = cmd;
        }

        public String getCmd(){
            return cmd;
        }
    }

    public enum Space{
        TOTAL_DISK_SPACE(70000000),
        NEED_UNUSED_SPACE(30000000);

        private final long space;

        Space(long space) {
            this.space = space;
        }

        public long getSpace(){
            return space;
        }
    }

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
            if (TerminalCmd.CMD_PROMPT.getCmd().equals(lineItems[0])) {
                if (TerminalCmd.CURRENT_DIRECTORY.getCmd().equals(lineItems[1])) {
                    if (TerminalCmd.ROOT.getCmd().equals(lineItems[2])) {
                        currentDirectory = rootDirectory;
                    } else if (TerminalCmd.NAVIGATE_UP.getCmd().equals(lineItems[2])) {
                        currentDirectory = currentDirectory.getParent();
                    } else {
                        currentDirectory = currentDirectory.getDirectory(lineItems[2]);
                    }
                }
            } else if (TerminalCmd.DIRECTORY_NAME.getCmd().equals(lineItems[0])) {
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
        long spaceLeft = Space.TOTAL_DISK_SPACE.getSpace() - sizeOfRoot;
        long spaceToRemove = Space.NEED_UNUSED_SPACE.getSpace() - spaceLeft;

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
