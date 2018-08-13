
import java.util.ArrayList;
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

    private static void Initialize(int[][] graph) {
        for (int i = 0; i < graph.length; ++i) {
            for (int j = 0; j < graph.length; ++j) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
    }


    private static class Node
    {
        public int id = 0;
        public int cost = 0;
        public int parent = 0;
        public Node(int _id, int _cost, int _parent)
        {
            id = _id;
            cost = _cost;
            parent = _parent;
        }
    }

    private static int[] GetShortestPath(int[][] graph)
    {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] in_p_q = new boolean[n];
        int[] p_m = new int[n];

        for (int i = 0; i < p_m.length; ++i)
            p_m[i] = -1;

        int e = 5;
        int infinity = -1;

        PriorityQueue<Node> p_q = new PriorityQueue<Node>((x, y) -> y.cost - x.cost);
        p_q.add(new Node(0, 0, 0));
        in_p_q[0] = true;

        while (!p_q.isEmpty())
        {
            Node cur = p_q.remove();
            p_m[cur.id] = cur.parent;
            if (cur.id == e)
                break;
            visited[cur.id] = true;
            in_p_q[cur.id] = false;

            for (int i = 0; i < n; ++i)
            {
                if (i != cur.id && graph[cur.id][i] != infinity)
                {
                    int new_cost = cur.cost + graph[cur.id][i];
                    if (!visited[i])
                    {
                        if (in_p_q[i])
                        {
                            for (Node s_n : p_q)
                            {
                                if (s_n.id == i)
                                {
                                    if (new_cost > s_n.cost)
                                    {
                                        s_n.cost = new_cost;
                                        s_n.parent = cur.id;
                                    }
                                }
                            }
                        }
                        else
                        {
                            in_p_q[i] = true;
                            p_q.add(new Node(i, new_cost, cur.id));
                        }

                    }
                }
            }
        }
        return p_m;
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

        GetShortestPath(graph);

        return n;
    }
}
