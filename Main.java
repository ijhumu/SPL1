//package graph_programs;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Read from input file
       /* String filePath = "C:\\Users\\jhumu\\IdeaProjects\\RouteDesigner2\\src\\inputs";
        File file = new File(filePath);

        Scanner fileScanner = new Scanner(file);

        int v = fileScanner.nextInt();
        int e = fileScanner.nextInt();

        Graph g = new Graph(v);

        for(int i=0;i<e;i++){
            int s = fileScanner.nextInt();  //source index
            int d = fileScanner.nextInt();  //destination index
            int w = fileScanner.nextInt();  // weight
            g.addEdge(s,d,w);
        }
        fileScanner.close();

        g.printGraph();

        Scanner inputScanner = new Scanner(System.in);
        g.DFS(0);

        System.out.println("Dijkstra : ShortestPath");
        System.out.println("Enter Source: ");
        int s = inputScanner.nextInt();

        g.dijkstra(s);*/

        Gui g = new Gui();
    }
}




