
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

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

    private static void Initialize(int[][] graph)
    {
        for (int i = 0; i < graph.length; ++i)
        {
            for (int j = 0; j < graph.length; ++j)
            {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static class Node
    {
        public int m_node = 0;
        public int m_cost = 0;
        public Node(int node, int cost)
        {
            m_node = node;
            m_cost = cost;
        }
    }

    private static ArrayList<Integer> GetShortestPath(int[][] graph)
    {
        int n = graph.length;
        boolean[] visited = new boolean[n];

        int e = 5;
        int infinity = Integer.MAX_VALUE;

        PriorityQueue<Node> p_q = new PriorityQueue<Node>((x, y) -> x.m_cost - y.m_cost);
        p_q.add(new Node(0, 0));
        while (!p_q.isEmpty() || p_q.peek().m_cost != e)
        {
            Node cur = p_q.remove();

            for (int i = 0; i < n; ++i)
            {
                if (i != cur.m_node && !visited[i] && graph[cur.m_node][i] != infinity)
                {
                    p_q.add(new Node(i + cur.m_cost, cur.m_node));
                    visited[i] = true;
                }
            }
        }
        return new ArrayList<>();
    }

    public static int answer(int[] banana_list)
    {
        int n = banana_list.length;

        int s = 13;
        int[][] graph = new int[][]{
                {0,7,2,3,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {7,0,3,-1,4,-1,-1,-1,-1,-1,-1,-1,-1},
                {2,3,0,-1,4,-1,-1,-1,1,-1,-1,-1,-1},
                {3,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,2},
                {-1,4,4,-1,0,-1,5,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,0,-1,2,-1,-1,-1,5,-1},
                {-1,-1,-1,-1,5,-1,0,-1,3,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,2,-1,0,2,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,3,2,0,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,0,6,4,4},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,6,0,4,4},
                {-1,-1,-1,-1,-1,5,-1,-1,-1,4,4,0,-1},
                {-1,-1,-1,2,-1,-1,-1,-1,-1,4,4,-1,0}
        };
        
        return n;
    }
}
