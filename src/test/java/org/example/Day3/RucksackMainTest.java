package org.example.Day3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.Day1.ElfCarryingCalories;
import static org.example.Day3.RucksackMain.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RucksackMainTest {

    @Test
    void readFileTest() {
        ArrayList<String> actual = readFile("/day3TestInput.txt");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("vJrwpWtwJgWrhcsFMMfFFhFp");
        expected.add("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        expected.add("PmmdzqPrVvPwwTWBwg");
        expected.add("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
        expected.add("ttgJtRGJQctTZtZT");
        expected.add("CrZsJsPPZsGzwwsLwLmpwMDw");

        assertEquals(expected,actual);
    }

    @Test
    void splitItemOfCompartmentsTest() {
        ArrayList<String> rucks = readFile("/day3TestInput.txt");
        ArrayList<Rucksack> rackSucks = new ArrayList<>();
        Rucksack rucksack1 = new Rucksack();
        rucksack1.setCompartmentOne("vJrwpWtwJgWr");
        rucksack1.setCompartmentTwo("hcsFMMfFFhFp");
        Rucksack rucksack2 = new Rucksack();
        rucksack2.setCompartmentOne("jqHRNqRjqzjGDLGL");
        rucksack2.setCompartmentTwo("rsFMfFZSrLrFZsSL");
        Rucksack rucksack3 = new Rucksack();
        rucksack3.setCompartmentOne("PmmdzqPrV");
        rucksack3.setCompartmentTwo("vPwwTWBwg");
        rackSucks.add(rucksack1);
        rackSucks.add(rucksack2);
        rackSucks.add(rucksack3);

        ArrayList<Rucksack> actual = splitItemOfCompartments(rucks);
        Rucksack rucksackT1 = actual.get(2);

        assertEquals(rucksack3.getCompartmentOne(),rucksackT1.getCompartmentOne());

    }

    @Test
    void findSameItemInCompartmentsTest() {
        ArrayList<String> rucks = readFile("/day3TestInput.txt");
        ArrayList<Rucksack> rucksacks = splitItemOfCompartments(rucks);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("p");
        expected.add("L");
        expected.add("P");
        expected.add("v");
        expected.add("t");
        expected.add("s");

        assertEquals(expected,findSameItemInCompartments(rucksacks));
    }

    @Test
    void setPriorityForEachItemTest() {
        ArrayList<String> rucks = readFile("/day3TestInput.txt");
        ArrayList<Rucksack> rucksacks = splitItemOfCompartments(rucks);
        ArrayList<String> sameItem = findSameItemInCompartments(rucksacks);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(16);
        expected.add(38);
        expected.add(42);
        expected.add(22);
        expected.add(20);
        expected.add(19);

        assertEquals(expected,setPriorityForEachItem(sameItem));
    }

    @Test
    void calculatePriorityTest() {
        assertEquals(1,calculatePriority("a"));
    }

    @Test
    void sumOfPriorityOfTheItemTest() {
        ArrayList<String> rucks = readFile("/day3TestInput.txt");
        ArrayList<Rucksack> rucksacks = splitItemOfCompartments(rucks);
        ArrayList<String> sameItem = findSameItemInCompartments(rucksacks);
        ArrayList<Integer> Priority = setPriorityForEachItem(sameItem);

        assertEquals(157,sumOfPriorityOfTheItem(Priority));
    }

    @Test
    void pipeLineTest() {
        assertEquals(157,pipeLine("/day3TestInput.txt"));
    }

    @Test
    void groupElvesTest() {
        ArrayList<String> rucks = readFile("/day3TestInput.txt");
        ArrayList<Rucksack> rucksacks = splitItemOfCompartments(rucks);

        ArrayList<ElvesGroup> elvesGroups = new ArrayList<>();

        Rucksack rucksack1 = new Rucksack();
        rucksack1.setCompartmentOne("vJrwpWtwJgWr");
        rucksack1.setCompartmentTwo("hcsFMMfFFhFp");
        Rucksack rucksack2 = new Rucksack();
        rucksack2.setCompartmentOne("jqHRNqRjqzjGDLGL");
        rucksack2.setCompartmentTwo("rsFMfFZSrLrFZsSL");
        Rucksack rucksack3 = new Rucksack();
        rucksack3.setCompartmentOne("PmmdzqPrV");
        rucksack3.setCompartmentTwo("vPwwTWBwg");

        ElvesGroup elvesGroup = new ElvesGroup(rucksack1,rucksack2,rucksack3);
        elvesGroups.add(elvesGroup);

        ArrayList<ElvesGroup> elvesGr = groupElves(rucksacks);

        assertEquals(elvesGroup.getElvOne().getCompartmentOne(),elvesGr.get(0).getElvOne().getCompartmentOne());

    }

    @Test
    void findCommanItemOfGroupTest() {
        ArrayList<String> rucks = readFile("/day3TestInput.txt");
        ArrayList<Rucksack> rucksacks = splitItemOfCompartments(rucks);
        ArrayList<ElvesGroup> elvesGr = groupElves(rucksacks);

        ArrayList<String> ActualCommanItem = findCommanItemOfGroup(elvesGr);

        ArrayList<String> ExpectedCommanItem = new ArrayList<>();
        ExpectedCommanItem.add("r");
        ExpectedCommanItem.add("Z");

        assertEquals(ExpectedCommanItem,ActualCommanItem);

    }

    @Test
    void pipeline_2Test() {
        assertEquals(70,pipeline_2("/day3TestInput.txt"));
    }
}