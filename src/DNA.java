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

        // Go through the whole DNA to check for repeats
        int max = sequence.length() - length;
        for(int i = length; i < max; i++){
            // If you find a match, check for consecutive matches
            if(strHash == seqHash){
                // Shift over the new hash so you can check the sequence in front of you
                newSeqHash = hash(sequence, i, length);
                numRepeats = 1;

                // While you are still equal, continue to shift over by the length of the STR and add to repeats
                while(strHash == newSeqHash){
                    // Only update seqHash in the case where newSeqHash is a match
                    seqHash = hash(sequence, i, length);
                    numRepeats += 1;
                    // If you can check the next sequence w/o going out of bounds do so, otherwise exit out
                    if(i + length < sequence.length()){
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

            // Shift over the window by one letter
            // First subtract out the first letter from hash
            int firstL = letters[sequence.charAt(i - length)];
            seqHash = seqHash - firstL * (1 << 2*length - 2);
            // Multiply by radix to shift over
            seqHash *= R;
            // Add the value of the last letter
            seqHash += letters[sequence.charAt(i)];

        }
        return maxRepeats;
    }

    // Creates hash for the first time and when you are scanning ahead
    public static int hash(String s, int start, int length) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash*R + letters[s.charAt(start + i)]);
        }
        return hash;
    }
}
