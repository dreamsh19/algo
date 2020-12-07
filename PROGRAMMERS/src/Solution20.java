import java.util.Arrays;

public class Solution20 {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int n = people.length;
        int light = 0, heavy = n - 1;
        while(light<heavy){
            if(people[light]+people[heavy] <= limit){
                light++;
            }
            heavy--;
            answer++;
        }
        if(light == heavy) answer++;

        return answer;
    }
}
