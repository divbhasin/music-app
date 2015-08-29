package ddra.com.musicapp;

import android.graphics.Bitmap;

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
    public static final int chordSteps[][] = {{4, 3}, {3, 4}, {4, 4},
            {3, 3}, {4, 3, 3}, {4, 3, 4}, {3, 4, 3},
            {3, 3, 4}, {3, 3, 3}, {3, 4, 4}, {4, 4, 3},
            {4, 4, 2}};


    public static final String noteName[] = {"Whole", "Dotted Half", "Half", "Dotted Quarter",
            "Quarter", "Dotted Eighth", "Eighth", "Dotted Sixteenth", "Sixteenth", "Triplet Quarter",
            "Triplet Eighth"};
    public static final int noteDuration[] = {96, 72, 48, 36, 24, 18, 12, 9, 6, 16, 8};
    public static final Bitmap noteSymbol[] = {}; //TODO: add bitmaps for each note

    public static final int noteID[] = {R.raw.piano_0, R.raw.piano_1, R.raw.piano_2, R.raw.piano_3, R.raw.piano_4,
            R.raw.piano_5, R.raw.piano_6, R.raw.piano_7, R.raw.piano_8, R.raw.piano_9, R.raw.piano_10,
            R.raw.piano_11, R.raw.piano_12,R.raw.piano_13, R.raw.piano_14, R.raw.piano_15, R.raw.piano_16,
            R.raw.piano_17, R.raw.piano_18, R.raw.piano_19, R.raw.piano_20, R.raw.piano_21, R.raw.piano_22,
            R.raw.piano_23, R.raw.piano_24};
}
