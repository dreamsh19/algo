import java.util.HashSet;

public class Solution28 {

    HashSet<String> permutationResult;
    HashSet<Integer> primeSet;

    boolean isPrime(int n) {

        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) return false;
        }
        return n > 1;
    }

    void permutation(char[] arr, int l) {
        String result = new String(arr).substring(0, l);
        permutationResult.add(result);

        if (l == arr.length) return;

        for (int i = l; i <= arr.length - 1; i++) {
            swap(arr, l, i);
            permutation(arr, l + 1);
            swap(arr, l, i);
        }
    }

    void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int solution(String numbers) {
        permutationResult = new HashSet<>();
        permutation(numbers.toCharArray(), 0);
        permutationResult.remove("");

        primeSet = new HashSet<>();
        for(String s : permutationResult){
            int num = Integer.parseInt(s);
            if(isPrime(num)) primeSet.add(num);
        }

        return primeSet.size();
    }
}
