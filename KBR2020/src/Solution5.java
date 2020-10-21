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

}

