import java.awt.desktop.SystemSleepEvent;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;

public class RunningWithBunnies {

    private static ArrayList<ArrayList<Integer>> GetAllSubsets(int[] arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int n = (int) Math.pow(2, arr.length);
        for (int i = 0; i < n; ++i) {
            String bin_i = Integer.toBinaryString(i);
            int bin_l = bin_i.length();
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < arr.length && j < bin_l; ++j) {
                if (bin_i.charAt(bin_l - 1 - j) == '1') {
                    subset.add(arr[j]);
                }
            }
            Permutation(subset, 0, result);
        }
        return result;
    }

    private static void Permutation(ArrayList<Integer> subset, int start, ArrayList<ArrayList<Integer>> result)
    {
        if (start >= subset.size())
        {
            result.add(new ArrayList<>(subset));
        }
        else
        {
            for (int i = start; i < subset.size(); ++i)
            {
                Swap(subset, start, i);
                Permutation(subset, start + 1, result);
                Swap(subset, start, i);
            }
        }
    }

    private static void Swap(ArrayList<Integer> subset, int start, int i)
    {
        Integer temp = subset.get(i);
        subset.set(i, subset.get(start));
        subset.set(start, temp);
    }

    private static void Floyd(int[][] times)
    {
        int n = times.length;
        for (int k = 0; k < n; ++k)
        {
            for (int i = 0; i < n; ++i)
            {
                for (int j = 0; j < n; ++j)
                {
                    if (times[i][k] + times[k][j] < times[i][j])
                    {
                        times[i][j] = times[i][k] + times[k][j];
                    }
                }
            }
        }
    }

    private static boolean IsNegativeCycle(int[][] times)
    {
        boolean neg_cycle = false;
        for (int i = 0; i < times.length; ++i)
        {
            if (times[i][i] < 0)
                neg_cycle = true;
        }
        return neg_cycle;
    }

    public static int[] answer(int[][] times, int time)
    {
        //        PrintAllSubsets(new int[]{1, 2, 3, 4, 5});
        if (times.length <= 2)
            return new int[]{};

        int n_b = times.length - 2;
        Floyd(times);

        if (IsNegativeCycle(times))
        {
            int[] bunnies = new int[n_b];
            for (int i = 0; i < n_b; ++i)
                bunnies[i] = i  ;
            return bunnies;
        }
        else
        {
            int[] bunnies = new int[n_b];
            for (int i = 0; i < n_b; ++i)
                bunnies[i] = i + 1;
            ArrayList<ArrayList<Integer>> paths = GetAllSubsets(bunnies);
            ArrayList<Integer> current_winner = new ArrayList<>();
            for (ArrayList<Integer> path : paths)
            {
                int next = 0;
                int prev = next;
                int path_cost = 0;

                for (Integer i : path)
                {
                    prev = next;
                    path_cost += times[next][i];
                    next = i;
                }
                path_cost += times[next][n_b +1];

                if (path_cost <= time && current_winner.size() < path.size())
                {
                    current_winner = path;
                    if (path.size() == n_b)
                        break;
                }
            }
            Collections.sort(current_winner);
            int[] result = new int[current_winner.size()];
            for (int i = 0; i < current_winner.size(); ++i)
                result[i] = current_winner.get(i) - 1;
            return result;
        }
    }

    public static void main(String[] args)
    {
//        int[][] times = {
//                {0, 2, 2, 2, -1},
//                {9, 0, 2, 2, -1},
//                {9, 3, 0, 2, -1},
//                {9, 3, 2, 0, -1},
//                {9, 3, 2, 2, 0}
//        };
//
//        int[] answer1 = answer(times, 1);

        int[][] case3 = {{0, 2, 2, 2, -1},
        {9, 0, 2, 2, 0},
        {9, 3, 0, 2, 0},
        {9, 3, 2, 0, 0},
        {-1, 3, 2, 2, 0}};

        int[] case3_ans = answer(case3, -500);
        System.out.print("Expected: [0, 1, 2]\nCalculated: [");
        for (int i = 0; i < case3_ans.length; ++i)
        {
            System.out.print(case3_ans[i]);
            System.out.print(", ");
        }
        System.out.println("]");


        int[][] case10 = {{0, 10, 10, 1, 10},
                {10, 0, 10, 10, 1},
                {10, 1, 0, 10, 10},
                {10, 10, 1, 0, 10},
                {1, 10, 10, 10, 0}
        };
        int[] case10_ans = answer(case10, 6);
        System.out.print("Expected: [0, 1, 2]\nCalculated: [");
        for (int i = 0; i < case10_ans.length; ++i)
        {
            System.out.print(case10_ans[i]);
            System.out.print(", ");
        }
        System.out.println("]");
    }
}
