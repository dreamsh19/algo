import java.util.Arrays;

public class Solution23 {
    public boolean solution(String[] phone_book) {
        int len = phone_book.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                String p1 = phone_book[i];
                String p2 = phone_book[j];
                if (p1.startsWith(p2) || p2.startsWith(p1)) return false;
            }
        }
        return true;
    }

    public boolean solution_(String[] phone_book) {
        Arrays.sort(phone_book);
        int len = phone_book.length;
        for (int i = 0; i < len - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) return false;
        }
        return true;
    }
}
