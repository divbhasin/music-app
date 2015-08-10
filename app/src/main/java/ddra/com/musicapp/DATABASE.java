package ddra.com.musicapp;

/**
 * ALL EXERCISE DATA HERE
 */
public class DATABASE {
    public static final String intervalName[] = {"Perfect First",
            "Minor Second", "Major Second",
            "Minor Third", "Major Third",
            "Perfect Fourth", "Tritone",
            "Perfect Fifth", "Minor Sixth",
            "Major Sixth", "Minor Seventh",
            "Major Seventh", "Perfect Eighth"};
    public static final String intervalAbbre[] = {"P1", "m2", "M2", "m3", "M3", "P4", "TT", "P5", "m6", "M6", "m7", "M7", "P8"};

    public static final String scaleName[] = {"Major (Ionian)", "Dorian",
            "Phrygian", "Lydian", "Mixolydian",
            "Natural Minor (Aeolian)", "Locrian"};
    public static final int scaleSteps[][] = {{2, 2, 1, 2, 2, 2, 1}, {2, 1, 2, 2, 2, 1, 2},
            {1, 2, 2, 2, 1, 2, 2}, {2, 2, 2, 1, 2, 2, 1},
            {2, 2, 1, 2, 2, 1, 2}, {2, 1, 2, 2, 1, 2, 2},
            {1, 2, 2, 1, 2, 2, 2}};

    public static final String chordName[] = {"Major Triad", "Minor Triad",
            "Augmented Triad", "Diminished Triad", "Dominant Seventh",
            "Major Seventh", "Minor Seventh", "Half-Diminished Seventh",
            "Diminished Seventh", "Minor-Major Seventh",
            "Augmented-Major Seventh", "Augmented Seventh"};
    public static final String chordAbbre[] = {"maj", "min", "aug", "dim", "dom7", "maj7", "min7", "m7(b5)", "dim7", "mmaj7", "maj7(#5)", "7(#5)"};
    public static final int chordSteps[][] = {{4, 3, 0}, {3, 4, 0}, {4, 4, 0},
            {3, 3, 0}, {4, 3, 3}, {4, 3, 4}, {3, 4, 3},
            {3, 3, 4}, {3, 3, 3}, {3, 4, 4}, {4, 4, 3},
            {4, 4, 2}};


}
