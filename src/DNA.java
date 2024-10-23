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
    // public static final long P = 54321102419L;
    public static final int R = 4;
    static char[] letters = new char[85];


    public static int STRCount(String sequence, String STR) {
        // Insert all DNA letters into the letters map
        letters['A'] = 0;
        letters['C'] = 1;
        letters['G'] = 2;
        letters['T'] = 3;

        // Init your hashes
        int length = STR.length();
        int strHash = hash(STR, 0, length);
        int seqHash = hash(sequence, 0, length);
        int newSeqHash = 0;
        int numRepeats = 0;
        int maxRepeats = 0;
        String testSeq = sequence.substring(0, length);

        for(int i = length; i < sequence.length() - length; i++){
            // If you find a match, check for consecutive matches
            if(strHash == seqHash){
                // Shift over the hash
                newSeqHash = hash(sequence, i, length);
                numRepeats = 1;

                // While ur still equal, continue to shift over by the length of the STR and add to repeats
                while(strHash == newSeqHash){
                    seqHash = hash(sequence, i, length);
                    numRepeats += 1;
                    // If you can check the next sequence do so otherwise exit out
                    if(i + length < sequence.length()) {
                        i += length;
                        newSeqHash = hash(sequence, i, length);
                    }
                    else
                        break;
                }
                // Updates maxRepeats if needed
                if(numRepeats > maxRepeats)
                    maxRepeats = numRepeats;

            }
            // Shift over by one(subtract first, x radix, add last)
            // SeqHash - (value of first letter) * R^length-1
            int firstL = letters[sequence.charAt(i - length)];
            //seqHash = (long)(seqHash + P - firstL * Math.pow(R, length-1)) % P;
            seqHash = (seqHash - firstL * (1 << 2*length - 2));
            // Multiply by radix and add value of last letter
            seqHash *= R;
            seqHash += letters[sequence.charAt(i)];

        }
        return maxRepeats;
    }

    // Creates has for the first time
    // enter in length, index you start, and full sequence
    public static int hash(String s, int start, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash*R + letters[s.charAt(start + i)]);
        }
        return hash;
    }
}
