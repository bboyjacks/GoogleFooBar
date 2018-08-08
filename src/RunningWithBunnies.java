import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;

public class RunningWithBunnies {

    private static ArrayList<ArrayList<Integer>> PrintAllSubsets(int[] arr)
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int n = (int)Math.pow(2, arr.length);
        for (int i = 0; i < n; ++i)
        {
            String bin_i = Integer.toBinaryString(i);
            int bin_l = bin_i.length();
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < arr.length && j < bin_l; ++j)
            {
                if (bin_i.charAt(bin_l - 1 - j) == '1')
                {
                    subset.add(arr[j]);
                }
            }
        }
        return result;
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

    private static void PrintTimes(int[][] times)
    {
        for (int i = 0; i < times.length; ++i)
        {
            for (int j = 0; j < times.length; ++j)
            {
                System.out.print(times[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
//        PrintAllSubsets(new int[]{1, 2, 3, 4, 5});
        int[][] times = {
                {0, 2, 2, 2, -1},
                {9, 0, 2, 2, -1},
                {9, 3, 0, 2, -1},
                {9, 3, 2, 0, -1},
                {9, 3, 2, 2, 0}
        };

        Floyd(times);

        PrintTimes(times);
    }
}
