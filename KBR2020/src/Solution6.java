/*
외벽 점검
문제 설명
레스토랑을 운영하고 있는 스카피는 레스토랑 내부가 너무 낡아 친구들과 함께 직접 리모델링 하기로 했습니다. 레스토랑이 있는 곳은 스노우타운으로 매우 추운 지역이어서 내부 공사를 하는 도중에 주기적으로 외벽의 상태를 점검해야 할 필요가 있습니다.

레스토랑의 구조는 완전히 동그란 모양이고 외벽의 총 둘레는 n미터이며, 외벽의 몇몇 지점은 추위가 심할 경우 손상될 수도 있는 취약한 지점들이 있습니다. 따라서 내부 공사 도중에도 외벽의 취약 지점들이 손상되지 않았는 지, 주기적으로 친구들을 보내서 점검을 하기로 했습니다. 다만, 빠른 공사 진행을 위해 점검 시간을 1시간으로 제한했습니다. 친구들이 1시간 동안 이동할 수 있는 거리는 제각각이기 때문에, 최소한의 친구들을 투입해 취약 지점을 점검하고 나머지 친구들은 내부 공사를 돕도록 하려고 합니다. 편의 상 레스토랑의 정북 방향 지점을 0으로 나타내며, 취약 지점의 위치는 정북 방향 지점으로부터 시계 방향으로 떨어진 거리로 나타냅니다. 또, 친구들은 출발 지점부터 시계, 혹은 반시계 방향으로 외벽을 따라서만 이동합니다.

외벽의 길이 n, 취약 지점의 위치가 담긴 배열 weak, 각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열 dist가 매개변수로 주어질 때, 취약 지점을 점검하기 위해 보내야 하는 친구 수의 최소값을 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 1 이상 200 이하인 자연수입니다.
weak의 길이는 1 이상 15 이하입니다.
서로 다른 두 취약점의 위치가 같은 경우는 주어지지 않습니다.
취약 지점의 위치는 오름차순으로 정렬되어 주어집니다.
weak의 원소는 0 이상 n - 1 이하인 정수입니다.
dist의 길이는 1 이상 8 이하입니다.
dist의 원소는 1 이상 100 이하인 자연수입니다.
친구들을 모두 투입해도 취약 지점을 전부 점검할 수 없는 경우에는 -1을 return 해주세요.

입출력 예
n	weak	dist	result
12	[1, 5, 6, 10]	[1, 2, 3, 4]	2
12	[1, 3, 4, 9, 10]	[3, 5, 7]	1
입출력 예에 대한 설명
입출력 예 #1

원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.

외벽점검-1.jpg

친구들을 투입하는 예시 중 하나는 다음과 같습니다.

4m를 이동할 수 있는 친구는 10m 지점에서 출발해 시계방향으로 돌아 1m 위치에 있는 취약 지점에서 외벽 점검을 마칩니다.
2m를 이동할 수 있는 친구는 4.5m 지점에서 출발해 6.5m 지점에서 외벽 점검을 마칩니다.
그 외에 여러 방법들이 있지만, 두 명보다 적은 친구를 투입하는 방법은 없습니다. 따라서 친구를 최소 두 명 투입해야 합니다.

입출력 예 #2

원형 레스토랑에서 외벽의 취약 지점의 위치는 다음과 같습니다.

외벽점검-2.jpg

7m를 이동할 수 있는 친구가 4m 지점에서 출발해 반시계 방향으로 점검을 돌면 모든 취약 지점을 점검할 수 있습니다. 따라서 친구를 최소 한 명 투입하면 됩니다.
 */

import java.util.Collections;
import java.util.HashSet;
import java.util.ArrayList;

class Solution6 {
    int numPoints;
    int[] diff;
    HashSet<ArrayList<Integer>> permutations;

    int nextIdx(int worker, int from) {
        int sum = 0;
        int i;
        for (i = from; i < numPoints; i++) {
            sum += diff[i];
            if (sum > worker) return i + 1;
        }
        return numPoints;
    }


    void permute(ArrayList<Integer> dist, int l, int r) {
        if (l == r) {
            permutations.add((ArrayList<Integer>) dist.clone());
        } else {
            for (int i = l; i <= r; i++) {
                Collections.swap(dist, l, i);
                permute(dist, l + 1, r);
                Collections.swap(dist, l, i);
            }
        }
    }

    void shift(int[] arr) {
        int tmp = arr[0];
        int i;
        for (i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[i] = tmp;
    }

    public int solution(int n, int[] weak, int[] dist) {
        numPoints = weak.length;
        ArrayList<Integer> totalWorkers = new ArrayList<>();
        for (int d : dist) {
            totalWorkers.add(d);
        }
        Collections.sort(totalWorkers, Collections.reverseOrder());

        diff = new int[numPoints];

        for (int i = 0; i < numPoints - 1; i++) {
            diff[i] = weak[i + 1] - weak[i];
        }
        diff[numPoints - 1] = n + weak[0] - weak[numPoints - 1];

        ArrayList<Integer> workingList = new ArrayList<>();
        for (int workerNum = 0; workerNum < dist.length; workerNum++) {
            workingList.add(totalWorkers.get(workerNum));
            permutations = new HashSet<>();
            permute(workingList, 0, workerNum);
            for (int shiftCount = 0; shiftCount < numPoints; shiftCount++) {
                for (ArrayList<Integer> workers : permutations) {
                    int idx = 0;
                    for (int worker : workers) {
                        idx = nextIdx(worker, idx);
                        if (idx == numPoints) return workerNum + 1;
                    }

                }
                shift(diff);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int T = 3;
        int[] ns = {12, 12, 12};
        int[][] weak = {{1, 5, 6, 10}, {1, 3, 4, 9, 10}, {1, 3, 5, 7, 9}};
        int[][] dist = {{1, 2, 3, 4}, {3, 5, 7}, {1, 1, 1}};

        for (int i = 0; i < T; i++) {
            Solution6 sol = new Solution6();
            int res = sol.solution(ns[i], weak[i], dist[i]);
            System.out.println("RES: " + res);
        }
    }
}