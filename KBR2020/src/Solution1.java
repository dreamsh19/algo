class Solution1 {

    int getZipLen(String s, int tokSize) {
        int len = s.length();
        if (len < tokSize) return len;

        String token = s.substring(0, tokSize);
        int count = 1;
        int i;
        for (i = tokSize; i + tokSize <= len; i += tokSize) {
            if (token.equals(s.substring(i, i + tokSize))) count++;
            else break;
        }
        return (count == 1 ? 0 : (int) (Math.log10(count) + 1)) + tokSize + getZipLen(s.substring(i), tokSize);
    }


    public int solution(String s) {
        int len = s.length();
        int answer = len;

        for (int tokSize = 1; tokSize <= len / 2; tokSize++) {
            answer = Math.min(answer, getZipLen(s, tokSize));
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