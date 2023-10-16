package org.example.Day3;

public class ElvesGroup {
    Rucksack elvOne;
    Rucksack elvTwo;
    Rucksack elvThree;

    public ElvesGroup() {
    }

    public ElvesGroup(Rucksack elvOne, Rucksack elvTwo, Rucksack elvThree) {
        this.elvOne = elvOne;
        this.elvTwo = elvTwo;
        this.elvThree = elvThree;
    }

    public Rucksack getElvOne() {
        return elvOne;
    }

    public void setElvOne(Rucksack elvOne) {
        this.elvOne = elvOne;
    }

    public Rucksack getElvTwo() {
        return elvTwo;
    }

    public void setElvTwo(Rucksack elvTwo) {
        this.elvTwo = elvTwo;
    }

    public Rucksack getElvThree() {
        return elvThree;
    }

    public void setElvThree(Rucksack elvThree) {
        this.elvThree = elvThree;
    }
}
