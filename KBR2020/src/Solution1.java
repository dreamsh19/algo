
class Solution1 {
    public int solution(String s) {
        int len = s.length();
        int answer = len;

        for (int token_size = 1; token_size <= len / 2; token_size++) {
            int ans_temp = 0;
            int start = 0;
            String token = s.substring(start, start + token_size);
            String next = token;
            while (true) {
                int count = 0;
                while (token.equals(next)) {
                    count++;
                    start += token_size;
                    if (start + token_size > len) break;
                    next = s.substring(start, start + token_size);
                }
                token = next;
                if (count > 1) {
                    ans_temp += token_size + (int) (Math.log10(count) + 1);
                } else {
                    ans_temp += token_size;
                }
                if (start + token_size > len){
                    ans_temp += len-start;
                    break;
                }
            }
            answer = Math.min(ans_temp, answer);
        }
        return answer;
    }


    public static void main(String[] args) {
        String s = "ababcdcdababcdcd";
        Solution1 sol = new Solution1();
        int answer = sol.solution(s);
        System.out.println(answer);
    }
}