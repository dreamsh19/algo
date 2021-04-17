import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Objects;

public class Solution3 {
    int R, C;
    String[][] relation;
    HashSet<HashSet<Integer>> candidateKeys;

    class ValueList {
        String[] values;
        int curIdx;

        ValueList(int n) {
            values = new String[n];
            curIdx = 0;
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

    boolean isKey(HashSet<Integer> colList) {
        HashSet<ValueList> set = new HashSet<>();
        for (String[] row : relation) {
            ValueList valueList = new ValueList(colList.size());
            for (int col : colList) {
                valueList.add(row[col]);
            }
            if (!set.add(valueList)) return false;
        }
        return true;
    }

    boolean isMinimal(HashSet<Integer> colSet) {
        for (HashSet<Integer> candidateKey : candidateKeys) {
            if (colSet.containsAll(candidateKey)) return false;
        }
        return true;
    }

    void combination(int n, HashSet<Integer> result, int level) {
        if (level == n) {
            if (isMinimal(result) && isKey(result))
                candidateKeys.add((HashSet<Integer>) result.clone());
            return;
        }

        combination(n, result, level + 1);
        result.add(level);
        combination(n, result, level + 1);
        result.remove(level);
    }

    public int solution(String[][] relation) {
        this.relation = relation;
        R = relation.length;
        C = relation[0].length;
        candidateKeys = new HashSet<>();

        combination(C, new HashSet<>(), 0);
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
                if (!set.add(valueList)) return false;
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
