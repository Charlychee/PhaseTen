package com;

import java.util.ArrayList;
import java.util.Arrays;

public class Phase {
    public static final Phase[] ORIGINAL_PHASES = {
            new Phase(new int[]{}, new int[]{3, 3}, new int[]{}),
            new Phase(new int[]{4}, new int[]{3}, new int[]{}),
            new Phase(new int[]{4}, new int[]{4}, new int[]{}),
            new Phase(new int[]{7}, new int[]{}, new int[]{}),
            new Phase(new int[]{8}, new int[]{}, new int[]{}),
            new Phase(new int[]{9}, new int[]{}, new int[]{}),
            new Phase(new int[]{}, new int[]{4, 4}, new int[]{}),
            new Phase(new int[]{}, new int[]{}, new int[]{7}),
            new Phase(new int[]{}, new int[]{5, 2}, new int[]{}),
            new Phase(new int[]{}, new int[]{5, 3}, new int[]{}),
    };

    /** Information about the runs needed in this phase. */
    private ArrayList<Integer> runs;

    /** Information about the number sets needed in this phase. */
    private ArrayList<Integer> numSets;

    /** Information about the even/odd sets needed in this phase. */
    private ArrayList<Integer> evenOdds;

    /** Information about the color sets needed in this phase. */
    private ArrayList<Integer> colorSets;

    Phase(int... numbers) {
        int totalRuns = numbers[0];
        int numSetIndex = 1 + totalRuns;
        int totalNumSets = numbers[numSetIndex];
        int evenOddIndex = 1 + numSetIndex + totalNumSets;
        int totalEvenOdds = numbers[evenOddIndex];
        int colorSetIndex = 1 + evenOddIndex + totalEvenOdds;
        int totalColorSets = numbers[colorSetIndex];
        runs = (ArrayList)Arrays.asList(Arrays.copyOfRange(numbers, 1, totalRuns + 1));
        numSets = (ArrayList)Arrays.asList(Arrays.copyOfRange(numbers, numSetIndex + 1, numSetIndex + 1 + totalNumSets));
        evenOdds = (ArrayList)Arrays.asList(Arrays.copyOfRange(numbers, evenOddIndex + 1, evenOddIndex + 1 + totalEvenOdds));
        colorSets = (ArrayList)Arrays.asList(Arrays.copyOfRange(numbers, colorSetIndex + 1, colorSetIndex + 1 + totalColorSets));
    }

    public Phase(int[] _runs, int[] _numSets, int[] _evenOdds, int[] _colorSets) {
        runs = (ArrayList)Arrays.asList(_runs);
        numSets = (ArrayList)Arrays.asList(_numSets);
        evenOdds = (ArrayList)Arrays.asList(_evenOdds);
        colorSets = (ArrayList)Arrays.asList(_colorSets);
    }

    public Phase(int[] _runs, int[] _numSets, int[] _colorSets) {
        runs = (ArrayList)Arrays.asList(_runs);
        numSets = (ArrayList)Arrays.asList(_numSets);
        colorSets = (ArrayList)Arrays.asList(_colorSets);
    }
}
