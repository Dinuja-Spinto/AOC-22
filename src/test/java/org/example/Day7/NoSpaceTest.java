package org.example.Day7;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.Day5.SupplyStacks.readFile;
import static org.example.Day7.NoSpace.*;
import static org.junit.jupiter.api.Assertions.*;

class NoSpaceTest {
    String fileName = "/day7TestInput.txt";
    @Test
    void readFileTest() {
        ArrayList<String> actual = readFile("/day7TestInput2.txt");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("$ cd /");
        expected.add("$ ls");
        expected.add("dir a");

        assertEquals(expected,actual);

    }

    @Test
    void getAllDirectoriesTest() {
        assertEquals("/",getAllDirectories(readFile(fileName)).get(0).getName());
    }

    @Test
    void getTotalSizeOfDirectoriesTest() {
       long actual = getTotalSizeOfDirectories(getAllDirectories(readFile(fileName)));
       long expected = 95437;
       assertEquals(expected,actual);
    }

    @Test
    void pipeLineTest() {
        assertEquals(95437,pipeLine(fileName));
    }

    @Test
    void getMostDeletableFileSizeTest() {
        long actual = getMostDeletableFileSize(getAllDirectories(readFile(fileName)));
        long expected = 24933642;
        assertEquals(expected,actual);
    }

    @Test
    void pipeLine2Test() {
        assertEquals(24933642,pipeLine2(fileName));
    }
}