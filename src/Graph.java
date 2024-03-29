import java.util.*;

public class Graph {
    List<List<Integer>> graph = new ArrayList<>();
    private int vertexCount;
    private final int INF = Integer.MAX_VALUE;
    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        for (int i = 0; i < vertexCount; ++i) {
            graph.add(new ArrayList<>());
        }
    }

    // Метод для геренации случайного графа
    public void generationGraph() {
        Random random = new Random();
        for (int i = 0; i < vertexCount; ++i) {
            for (int j = 0; j < vertexCount; ++j) {
                graph.get(i).add(random.nextInt(100) + 1);
            }
        }
    }

    // Метод для представления строки-матрицы в виде списка списков, то есть обычной матрицы
    // Метод делит строку на подстроки в зависимости от того, сколько вершин в графе и вставялет эти элементы по порядку в "матрицу"
    // Подробнее о элементах в файле в классе Main, над методом "generationTestsData"
    public void toGraph(String[] vertexes) {
        int vertexCount = (int) Math.sqrt(vertexes.length);
        int vertexsesElemIndex = 0;
        for (int i = 0; i < vertexCount; ++i) {
            for (int j = 0; j < vertexCount; ++j) {
                graph.get(i).add(Integer.parseInt(vertexes[vertexsesElemIndex]));
                vertexsesElemIndex++;
            }
        }
    }

    // Метод, реализующий алгоритм Левита
    public void shortestPath(int s) {
        int iterations = 0;
        long startTime = System.nanoTime();
        int[] shortestDist = new int[vertexCount];
        Arrays.fill(shortestDist, INF);
        shortestDist[s] = 0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] inQueue = new boolean[vertexCount];
        queue.offer(s);
        inQueue[s] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            inQueue[u] = false;

            for (int i = 0; i < vertexCount; ++i) {
                if (graph.get(u).get(i)  == 0) {
                    continue;
                }
                iterations += 1;
                int v = i;
                int weight = graph.get(u).get(i);

                if (shortestDist[u] != INF && shortestDist[v] > shortestDist[u] + weight) {
                    shortestDist[v] = shortestDist[u] + weight;
                    if (!inQueue[v]) {
                        queue.offer(v);
                        inQueue[v] = true;
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.print((endTime - startTime) + " ");

    }
}
