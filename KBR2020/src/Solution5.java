import java.util.Arrays;
import java.util.HashMap;

class Solution5 {

    HashMap<Integer, Node> db;
    HashMap<Integer, Node> db_reverse;

    class Node {
        int size;
        HashMap<Character, Node> dict;

        Node() {
            size = 0;
            dict = new HashMap<Character, Node>();
        }

        void addString(String s) {
            Node thisNode = this;
            for (char c : s.toCharArray()) {
                thisNode.size++;
                thisNode.dict.putIfAbsent(c, new Node());
                thisNode = thisNode.dict.get(c);
            }
        }

        int getCount(String s) {
            try {
                Node node = this;
                int i = 0;
                char c;
                while ((c = s.charAt(i++)) != '?') {
                    node = node.dict.get(c);
                }
                return node.size;
            } catch (NullPointerException e) {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "\nNode{" +
                    "size=" + size +
                    ", dict=" + dict +
                    "}";
        }
    }

    void addToDB(String word, boolean isReverse) {
        int len = word.length();
        HashMap<Integer, Node> db_ = isReverse ? db_reverse : db;

        db_.putIfAbsent(len, new Node());
        Node node = db_.get(len);
        node.addString(word);

    }

    String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    int getCount(String q) {
        boolean isPostfix = q.endsWith("?");
        int qLen = q.length();

        Node db_len = isPostfix ? db.get(qLen) : db_reverse.get(qLen);
        q = isPostfix ? q : reverse(q);
        return db_len == null ? 0 : db_len.getCount(q);
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        int ans_idx = 0;
        db = new HashMap<Integer, Node>();
        db_reverse = new HashMap<Integer, Node>();


        for (String word : words) {
            addToDB(word, false);
            addToDB(reverse(word), true);
        }
        for (String q : queries) {
            answer[ans_idx++] = getCount(q);
        }
        return answer;
    }


    static final char BEFORE_A = 'a' - 1, AFTER_Z = 'z' + 1;
    myString[] db_;
    myString[] db_reverse_;
    int n;

    class myString implements Comparable<myString> {

        String s;
        int length;

        myString(String s) {
            this.s = s;
            length = s.length();
        }

        @Override
        public int compareTo(myString o) {
            return length == o.length ? s.compareTo(o.s) : length - o.length;
        }

        @Override
        public String toString() {
            return s;
        }
    }

    int findString(myString[] db, String s) {
        myString target = new myString(s);
        int l = 0, r = n - 1;
        int idx = n;
        while (l <= r) {
            int m = (l + r) / 2;
            if (target.compareTo(db[m]) > 0) {
                l = m + 1;
            } else {
                idx = m;
                r = m - 1;
            }
        }
        return idx;
    }

    int getCount_(String q) {
        boolean isPostfix = q.endsWith("?");

        myString[] db = isPostfix ? db_ : db_reverse_;
        q = isPostfix ? q : reverse(q);

        int start = findString(db, q.replace('?', BEFORE_A));
        int end = findString(db, q.replace('?', AFTER_Z));

        return end - start;
    }


    public int[] solution_(String[] words, String[] queries) {
        n = words.length;

        db_ = new myString[n];
        db_reverse_ = new myString[n];
        for (int i = 0; i < n; i++) {
            db_[i] = new myString(words[i]);
            db_reverse_[i] = new myString(reverse(words[i]));
        }
        Arrays.sort(db_);
        Arrays.sort(db_reverse_);

        int[] answer = new int[queries.length];
        int ans_idx = 0;
        for (String q : queries) {
            answer[ans_idx++] = getCount_(q);
        }
        return answer;
    }
}

