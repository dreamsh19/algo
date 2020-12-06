import java.util.*;

public class Solution3 {
    int R, C;
    String[][] relation;
    HashSet<int[]> candidateKeys;

    class ValueList {
        String[] values;
        int curIdx;

        ValueList(int n) {
            values = new String[n];
            curIdx = 0;
        }

        ValueList(String[] values) {
            this.values = values.clone();
        }

        void add(String value) {
            values[curIdx++] = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ValueList valueList = (ValueList) o;
            return Arrays.equals(values, valueList.values);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(values);
        }
    }

    boolean isKey(int[] columnList) {
        HashSet<ValueList> set = new HashSet<>();
        int n = columnList.length;
        String[] values = new String[n];
        for (String[] row : relation) {
            int i = 0;
            for (int c : columnList) {
                values[i++] = row[c];
            }
            set.add(new ValueList(values));
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

    HashSet<BitMask> bitMaskSet;

    class BitMask implements Cloneable {
        int value;
        int size;

        BitMask(int value, int size) {
            this.value = value;
            this.size = size;
        }

        boolean contains(BitMask o) {
            return (o.value & value) == o.value;
        }

        boolean isOverflow() {
            return value >= (1 << size);
        }

        void inc() {
            value++;
        }

        boolean isMinimal() {
            for (BitMask bitmask : bitMaskSet) {
                if (this.contains(bitmask)) return false;
            }
            return true;
        }

        ArrayList<Integer> toList() {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if ((value & (1 << i)) != 0) list.add(i);
            }
            return list;
        }

        boolean isKey() {
            ArrayList<Integer> colList = toList();
            HashSet<ValueList> set = new HashSet<>();
            for (String[] row : relation) {
                ValueList valueList = new ValueList(colList.size());
                for (int col : colList) {
                    valueList.add(row[col]);
                }
                if(!set.add(valueList)) return false;
            }
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BitMask bitMask = (BitMask) o;
            return value == bitMask.value &&
                    size == bitMask.size;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, size);
        }

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


    public int solution_(String[][] relation) {
        this.relation = relation;
        R = relation.length;
        C = relation[0].length;
        bitMaskSet = new HashSet<>();

        for (BitMask bitmask = new BitMask(1, C); !bitmask.isOverflow(); bitmask.inc()) {
            if (bitmask.isMinimal() && bitmask.isKey()) {
                bitMaskSet.add((BitMask) bitmask.clone());
            }
        }

        return bitMaskSet.size();
    }

}
