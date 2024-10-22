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
    public static final long P = 54321102419L;
    public static final int R = 4;
    static char[] letters = new char[85];


    public static int STRCount(String sequence, String STR) {
        // Insert all DNA letters into the letters map
        letters['A'] = 'A';
        letters['C'] = 'C';
        letters['G'] = 'G';
        letters['T'] = 'T';


        // Init your hashes
        int length = STR.length();
        int strHash = hash(STR, length);
        long seqHash = hash(sequence.substring(0, length), length);
        int numRepeats = 0;
        int maxRepeats = 0;
        String testSeq = sequence.substring(0, length);


        for(int i = length; i < sequence.length() - 1; i++){
            // If you find a match, check for consecutive matches
            if(strHash == seqHash){
                // Shift over the hash
                seqHash = hash(sequence.substring(i, i + length), length);
               // i += length;
                numRepeats = 1;

                // While ur still equal, continue to shift over by the length of the STR
                while(strHash == seqHash){
                    i += length;
                    seqHash = hash(sequence.substring(i, i + length), length);
                    numRepeats += 1;
                }
                // Updates maxRepeats if needed
                if(numRepeats > maxRepeats)
                    maxRepeats = numRepeats;

                // Need to move to next window after you find an invalid sequence
                i++;
            }
            // Shift over by one(subtract first, x radix, add last)
            // Seqhash - (value of first letter) * R^length-1
            int firstL = letters[sequence.charAt(i - length)];
            seqHash = (long)(seqHash + P - firstL * Math.pow(R, length-1)) % P;
            // Multiply by radix and add value of last letter
            seqHash *= R;
            seqHash += letters[sequence.charAt(i)] % P;

        }

        return maxRepeats;
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
