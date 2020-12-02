import java.util.ArrayList;
import java.util.HashMap;

public class Solution1 {

    HashMap<String, String> uid2nick;

    class Message {
        String uid;
        String action;

        Message(String action, String uid) {
            this.action = action;
            this.uid = uid;
        }

        @Override
        public String toString() {
            return uid2nick.get(uid) + (action.equals("Enter") ? "님이 들어왔습니다." : "님이 나갔습니다.");
        }
    }

    public String[] solution(String[] record) {
        uid2nick = new HashMap<>();
        ArrayList<Message> messages = new ArrayList<>();
        for (String r : record) {
            String[] tokens = r.split(" ");
            String action = tokens[0];
            String uid = tokens[1];

            if (action.equals("Leave")) {
                messages.add(new Message(action, uid));
            } else {
                String nickname = tokens[2];
                uid2nick.put(uid, nickname);
                if (action.equals("Enter")) {
                    messages.add(new Message(action, uid));
                }
            }
        }
        String[] results = new String[messages.size()];
        int idx = 0;
        for (Message msg : messages) {
            results[idx++] = msg.toString();
        }
        return results;
    }
}
