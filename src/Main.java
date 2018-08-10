
public class Main {

    private static void PrintInfo(String expected, int[] answer)
    {
        System.out.print(expected);
        for (int i = 0; i < answer.length; ++i)
        {
            System.out.print(answer[i]);
            if (i != answer.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    private static void RunWithBunnies()
    {
        int[][] times = {
                {0, 2, 2, 2, -1},
                {9, 0, 2, 2, -1},
                {9, 3, 0, 2, -1},
                {9, 3, 2, 0, -1},
                {9, 3, 2, 2, 0}
        };

        int[] answer1 = RunningWithBunnies.answer(times, 1);
        PrintInfo("1. Expected: [1, 2]\n\tCalculated: [", answer1);

        int[][] case3 = {{0, 2, 2, 2, -1},
                {9, 0, 2, 2, 0},
                {9, 3, 0, 2, 0},
                {9, 3, 2, 0, 0},
                {-1, 3, 2, 2, 0}};

        int[] case3_ans = RunningWithBunnies.answer(case3, -500);
        PrintInfo("2. Expected: [0, 1, 2]\n\tCalculated: [", case3_ans);


        int[][] case10 = {{0, 10, 10, 1, 10},
                {10, 0, 10, 10, 1},
                {10, 1, 0, 10, 10},
                {10, 10, 1, 0, 10},
                {1, 10, 10, 10, 0}
        };
        int[] case10_ans = RunningWithBunnies.answer(case10, 6);
        PrintInfo("3. Expected: [0, 1, 2]\n\tCalculated: [", case10_ans);
    }

    public static void main(String[] args)
    {
        RunWithBunnies();
    }
}
