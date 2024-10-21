/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Isha Gupta
 *</p>
 */

public class DNA {
    // public static final long P = 54321102419;
    public static final int R = 4;
    static char[] letters = new char[84];


    public static int STRCount(String sequence, String STR) {
        letters['A'] = 'A';
        letters['C'] = 'C';
        letters['G'] = 'G';
        letters['T'] = 'T';

        int length = STR.length();
        int strHash = hash(STR, length);
        int hash = hash(sequence.substring(0, length-1), length);

        for(int i = length; i < sequence.length(); i++){

        }

        return 0;
    }

    // Creates has for the first time
    public static int hash(String s, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash*R + letters[s.charAt(i)]);
        }
        return hash;
    }
}
