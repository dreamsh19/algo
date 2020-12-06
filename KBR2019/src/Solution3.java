import java.util.*;

public class Solution3 {
    int R, C;
    String[][] relation;
    HashSet<int[]> candidateKeys;

    class KeySet {
        int n;
        String[] values;

        KeySet(String[] values) {
            n = values.length;
            this.values = new String[n];
            System.arraycopy(values, 0, this.values, 0, n);
//            this.values = values;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeySet keySet = (KeySet) o;
            return n == keySet.n &&
                    Arrays.equals(values, keySet.values);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(n);
            result = 31 * result + Arrays.hashCode(values);
            return result;
        }
    }

    boolean isKey(int[] columnList) {
        HashSet<KeySet> set = new HashSet<>();
        int n = columnList.length;
        String[] values = new String[n];
        for (String[] row : relation) {
            int i = 0;
            for (int c : columnList) {
                values[i++] = row[c];
            }
            set.add(new KeySet(values));
        }
        return set.size() == R;
    }

    boolean contains(int[] a1, int[] a2) {
        int l1 = a1.length, l2 = a2.length;
        if (l1 > l2) return false;

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int a : a1) set1.add(a);
        for (int a : a2) set2.add(a);

        set1.removeAll(set2);

        return set1.isEmpty();

    }

    boolean isCandidateKey(int[] columnList) {
        for (int[] candidateKey : candidateKeys) {
            if (contains(candidateKey, columnList)) return false;
        }
        return true;
    }

    void combination(int[] arr, int[] result, int startIdx, int r) {
        int n = arr.length;
        int resultIdx = result.length - r;
        if (n - startIdx == r) {
            System.arraycopy(arr, startIdx, result, resultIdx, r);
            r = 0;
        }
        if (r == 0) {
            if (isKey(result) && isCandidateKey(result)) candidateKeys.add(result.clone());
            return;
        }
        result[resultIdx] = arr[startIdx];
        combination(arr, result, startIdx + 1, r - 1);
        combination(arr, result, startIdx + 1, r);

    }

    public int solution(String[][] relation) {
        this.relation = relation;
        R = relation.length;
        C = relation[0].length;
        candidateKeys = new HashSet<>();

        int[] arr = new int[C];
        for (int i = 0; i < C; i++) arr[i] = i;

        for (int r = 1; r <= C; r++) {
            combination(arr, new int[r], 0, r);
        }
        return candidateKeys.size();
    }

    HashSet<Integer> bitMaskSet;

    public int solution_(String[][] relation) {
        this.relation = relation;
        R = relation.length;
        C = relation[0].length;
        int bitMaskMax = 1 << C;
        bitMaskSet = new HashSet<>();
        for (int bitMask = 0; bitMask < bitMaskMax; bitMask++) {
            if (isMinimal(bitMask) && isKey(bitMask)) {
                bitMaskSet.add(bitMask);
            }
        }
        return bitMaskSet.size();
    }

    boolean isMinimal(int bitMask) {
        for (int mask : bitMaskSet) {
            if ((mask & bitMask) == mask) return false;
        }
        return true;
    }

    boolean isKey(int bitMask) {
        ArrayList<Integer> colList = new ArrayList<>();
        for (int col = 0; col < C; col++) {
            if ((bitMask & (1 << col)) > 0) colList.add(col);
        }
        HashSet<KeySet> set = new HashSet<>();
        for (String[] row : relation) {
            String[] values = new String[colList.size()];
            int j = 0;
            for (int col : colList) {
                values[j++] = row[col];
            }

            set.add(new KeySet(values));
        }
        return set.size()==R;
    }

}
