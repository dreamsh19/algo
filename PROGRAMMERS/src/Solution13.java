import java.util.ArrayList;


public class Solution13 {

    void addElement(ArrayList<Integer> arr, int e) {

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) > e) {
                arr.add(i, e);
                return;
            }
        }
        arr.add(e);
    }

    public int[] solution(String[] operations) {
        ArrayList<Integer> q = new ArrayList<>();
        for (String op : operations) {
            boolean isD = op.charAt(0) == 'D';
            int number = Integer.parseInt(op.substring(2));
            if (isD) {
                if (q.isEmpty()) continue;
                if (number == 1) {
                    q.remove(q.size() - 1);
                } else {
                    q.remove(0);
                }
            } else {
                addElement(q, number);
            }
        }
        if (q.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{q.get(q.size() - 1), q.get(0)};
        }
    }
}
