public class Solution23 {
    public boolean solution(String[] phone_book) {
        int len = phone_book.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                String p1 = phone_book[i];
                String p2 = phone_book[j];
                if(p1.startsWith(p2) || p2.startsWith(p1)) return false;
            }
        }
        return true;
    }
}
