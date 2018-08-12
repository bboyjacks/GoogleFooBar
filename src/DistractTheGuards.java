import java.math.BigInteger;
import java.util.HashMap;

public class DistractTheGuards {

    private static int GCD(int first, int second)
    {
        return second == 0 ? first : GCD(second, first % second);
    }

    private static boolean IsPairLoop(int first, int second)
    {
        if (first == second)
            return false;

        if (first > second)
            IsPairLoop(second, first);

        if (first == 0)
            return true;

        int d = GCD(first, second);
        return Integer.bitCount(first / d + second / d) != 1;
    }

    public static void GenWeights(int[][] graph, int[] banana_list)
    {
        for (int i = 0; i < graph.length; ++i)
        {
            for (int j = 0; j < graph.length; ++j)
            {
                if (IsPairLoop(banana_list[i], banana_list[j]))
                {
                    graph[i][j] = Integer.MAX_VALUE;
                }
                else
                {
                    graph[i][j] = 0;
                }
            }
        }
    }

    public static int answer(int[] banana_list)
    {
        int n = banana_list.length;
        if (n < 1)
            return 0;
        if (n == 1)
            return 1;

        int[][] graph = new int[n][n];
        GenWeights(graph, banana_list);

        for (int i = 0; i < banana_list.length; ++i)
        {
            for (int j = 0; j < banana_list.length; ++j)
            {
                System.out.print(graph[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        return n;
    }
}
