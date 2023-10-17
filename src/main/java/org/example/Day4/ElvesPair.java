package org.example.Day4;

public class ElvesPair {
    Elf elf1;
    Elf elf2;

    public ElvesPair(Elf elf1, Elf elf2) {
        this.elf1 = elf1;
        this.elf2 = elf2;
    }

    public ElvesPair() {
    }

    public Elf getElf1() {
        return elf1;
    }

    public void setElf1(Elf elf1) {
        this.elf1 = elf1;
    }

    public Elf getElf2() {
        return elf2;
    }

    public void setElf2(Elf elf2) {
        this.elf2 = elf2;
    }
}
