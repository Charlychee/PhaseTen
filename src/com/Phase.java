package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Phase {
    /** The ten phases of the original Phase 10 game. */
    public static final Phase[] ORIGINAL_PHASES = {
            null, // placeholder for index 0
            new Phase(1, 2, 3, 3, 0, 0, 0),
            new Phase(2, 1, 3, 1, 4, 0, 0),
            new Phase(3, 1, 4, 1, 4, 0, 0),
            new Phase(4, 0, 1, 7, 0, 0),
            new Phase(5, 0, 1, 8, 0, 0),
            new Phase(6,0, 1, 9, 0, 0),
            new Phase(7, 2, 4, 4, 0, 0, 0),
            new Phase(8, 0, 0, 0, 1, 7),
            new Phase(9, 2, 2, 5, 0, 0, 0),
            new Phase(10, 2, 3, 5, 0, 0, 0),
    };

    /** Information about the runs needed in this phase. */
    private ArrayList<Integer> runs;

    /** Information about the number sets needed in this phase. */
    private ArrayList<Integer> numSets;

    /** Information about the even/odd sets needed in this phase. */
    private ArrayList<Integer> evenOdds;

    /** Information about the color sets needed in this phase. */
    private ArrayList<Integer> colorSets;

    /** The number of this current phase. */
    private int phaseNumber;

    /**
     *  Creates a phase with number _PHASENUMBER with the given number of numberSets, runs, evenOdds, and colorSets.
     *  The first argument of numbers gives the number of numberSets n, followed by n arguments specifying
     *  the sizes of each numberSet. The remaining arguments follow the same pattern for runs,
     *  evenOdds, and colorSets.
     *  */
    public Phase(int _phaseNumber, Integer... numbers) {
        phaseNumber = _phaseNumber;
        int totalNumSets = numbers[0];
        int runIndex = 1 + totalNumSets;
        int totalRuns = numbers[runIndex];
        int evenOddIndex = 1 + runIndex + totalRuns;
        int totalEvenOdds = numbers[evenOddIndex];
        int colorSetIndex = 1 + evenOddIndex + totalEvenOdds;
        int totalColorSets = numbers[colorSetIndex];
        numSets = new ArrayList<>();
        Collections.addAll(numSets, Arrays.copyOfRange(numbers, 1, totalNumSets + 1));
        runs = new ArrayList<>();
        Collections.addAll(runs, Arrays.copyOfRange(numbers, runIndex + 1, runIndex + 1 + totalRuns));
        evenOdds = new ArrayList<>();
        Collections.addAll(evenOdds, Arrays.copyOfRange(numbers, evenOddIndex + 1, evenOddIndex + 1 + totalEvenOdds));
        colorSets = new ArrayList<>();
        Collections.addAll(colorSets, Arrays.copyOfRange(numbers, colorSetIndex + 1, colorSetIndex + 1 + totalColorSets));
    }

    /** Returns a string representation of this phase. */
    public String toString() {
        String str = "Phase " + phaseNumber + ":\n";
        for (int set : numSets) {
            str += "Number set of size " + set + "\n";
        }
        for (int run : runs) {
            str += "Run of " + run + "\n";
        }
        for (int set : evenOdds) {
            str += "Even or odd set of size " + set + "\n";
        }
        for (int set : colorSets) {
            str += "Color set of size " + set + "\n";
        }
        return str;
    }

    /** Returns a copy of the arraylist specifying the runs needed for this phase. */
    public ArrayList<Integer> getRuns() {
        return new ArrayList<>(runs);
    }

    /** Returns a copy of the arraylist specifying the numSets needed for this phase. */
    public ArrayList<Integer> getNumSets() {
        return new ArrayList<>(numSets);
    }

    /** Returns a copy of the arraylist specifying the evenOdds needed for this phase. */
    public ArrayList<Integer> getEvenOdds() {
        return new ArrayList<>(evenOdds);
    }

    /** Returns a copy of the arraylist specifying the colorSets needed for this phase. */
    public ArrayList<Integer> getColorSets() {
        return new ArrayList<>(colorSets);
    }

    /** Returns the phaseNumber. */
    public int getPhaseNumber() {
        return phaseNumber;
    }
}
