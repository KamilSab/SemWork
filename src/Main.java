import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Считывание файла тестовых данных
        Scanner scanner = new Scanner(new File("src/TestsData.txt"));
        List<String> list = new ArrayList<String>();
        while (scanner.hasNext()){
            list.add(scanner.next());
        }
        scanner.close();
        list = Files.readAllLines(new File("src/TestsData.txt").toPath(), Charset.defaultCharset());

        testsW(list);
    }

    // Файл представляет собой строки, каждая из которых является матрицой смежности
    // Количество элементов в каждой строке является квадратом какого-либо числа. Например, если это количество кратно 10ти, то это матрица смежности графа из 10ти вершин
    static public void generationTestsData() throws IOException {
        int vertexCount = 10;
        FileWriter fileWriter = new FileWriter("src/TestsData.txt");
        for (int i = 0; i < 90; ++i) {
            Graph graph = new Graph(vertexCount);
            graph.generationGraph();
            for (int j = 0; j < graph.graph.size(); ++j) {
                for (int k = 0; k < graph.graph.get(j).size(); ++k) {
                    fileWriter.append(Integer.toString(graph.graph.get(j).get(k)))
                            .append(' ');
                }
            }
            fileWriter.append('\n');
            vertexCount += 1;
        }
        fileWriter.flush();
        fileWriter.close();
    }

    // Метод работы с данными из файла
    static public void testsW(List<String> tests) {
        List<String[]> testsArray = new ArrayList<>();

        // Этот цикл представляет каждую строку файла в виде массива, деля его по пробелам
        for (int i = 0; i < tests.size(); ++i) {
            String[] vertexes = tests.get(i).split(" ");
            testsArray.add(vertexes);
        }

        // Этот цикл применяет алгоритм Левита к каждому графу
        for (int i = 0; i < testsArray.size(); ++i) {
            Graph graph = new Graph((int) Math.sqrt(testsArray.get(i).length));
            // Представление каждой строки в виде матрицы смежности
            graph.toGraph(testsArray.get(i));
            // Применение алгоритма левита
            graph.shortestPath(0);
        }
    }
}
