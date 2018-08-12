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

    private static int NChooseR(int n, int r)
    {
        if (r > n)
            return 0;

        if (n == r)
            return 1;

        if (n < 1 || r < 1)
            return 0;

        BigInteger n_fac = BigInteger.valueOf(1);
        for (int i = n; i > r; --i)
        {
            n_fac = n_fac.multiply(BigInteger.valueOf(i));
        }

        BigInteger n_fac_minus_r = BigInteger.valueOf(1);
        for (int i = 1; i <= n - r; i++)
        {
            n_fac_minus_r = n_fac_minus_r.multiply(BigInteger.valueOf(i));
        }

        BigInteger result = n_fac.divide(n_fac_minus_r);
        return result.intValue();
    }

    private static void GenPairs(int[][] pairs, int n)
    {
        int index = 0;
        for (int i = 0; i < n; ++i)
        {
            for (int j = i + 1; j < n; ++j)
            {
                pairs[index][0] = i;
                pairs[index++][1] = j;
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

        int[][] pairs = new int[NChooseR(n, 2)][2];
        GenPairs(pairs, n);

        for (int i = 0; i < pairs.length; ++i)
        {
            int f_i = pairs[i][0];
            int s_i = pairs[i][1];

            boolean is_loop_f = IsPairLoop(banana_list[f_i], banana_list[s_i]);
            if (is_loop_f)
            {
                n -= 2;
            }

            if (n <= 0)
            {
                n = 0;
                break;
            }
        }

        return n;
    }
}
