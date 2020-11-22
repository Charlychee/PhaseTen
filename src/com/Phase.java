package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Phase {
    /** The ten phases of the original Phase 10 game. */
    public static final Phase[] ORIGINAL_PHASES = {
            new Phase(2, 3, 3, 0, 0, 0),
            new Phase(1, 3, 1, 4, 0, 0),
            new Phase(1, 4, 1, 4, 0, 0),
            new Phase(0, 1, 7, 0, 0),
            new Phase(0, 1, 8, 0, 0),
            new Phase(0, 1, 9, 0, 0),
            new Phase(2, 4, 4, 0, 0, 0),
            new Phase(0, 0, 0, 1, 7),
            new Phase(2, 2, 5, 0, 0, 0),
            new Phase(2, 3, 5, 0, 0, 0),
    };

    /** Information about the runs needed in this phase. */
    private ArrayList<Integer> runs;

    /** Information about the number sets needed in this phase. */
    private ArrayList<Integer> numSets;

    /** Information about the even/odd sets needed in this phase. */
    private ArrayList<Integer> evenOdds;

    /** Information about the color sets needed in this phase. */
    private ArrayList<Integer> colorSets;

    /**
     *  Creates a phase with the given number of numberSets, runs, evenOdds, and colorSets.
     *  The first argument gives the number of numberSets n, followed by n arguments specifying
     *  the sizes of each numberSet. The remaining arguments follow the same pattern for runs,
     *  evenOdds, and colorSets.
     *  */
    public Phase(Integer... numbers) {
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

    /** Returns a string explaining the necessary player card piles for this phase. */
    public String toString() {
        String phaseString = "";
        if (numSets.size() != 0) {
            phaseString += numSets.size() + " number set(s) of size " + numSets.toString() +"\n";
        }
        if (runs.size() != 0) {
            phaseString += runs.size() + " run(s) of size " + runs.toString() + "\n";
        }
        if (evenOdds.size() != 0) {
            phaseString += evenOdds.size() + " even/odd(s) of size " + evenOdds.toString() + "\n";
        }
        if (colorSets.size() != 0) {
            phaseString += colorSets.size() + " color set(s) of size " + colorSets.toString();
        }
        phaseString = phaseString.replaceAll("[\\[\\]]", "");
        return phaseString.trim();
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
}
