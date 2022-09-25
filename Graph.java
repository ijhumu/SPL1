//package graph_programs;

import java.util.*;
import java.util.ArrayList;

public class Graph {

    static int adjMat[][];
    static int adjMatTime[][];
    int vertices;


    Graph(int nodes){
        vertices = nodes;
        adjMat = new int[vertices][vertices];
        adjMatTime = new int[vertices][vertices];
    }
    void addEdge(int source,int destination, int wgt, int time){
        adjMat[source][destination]=wgt;
        adjMat[destination][source]=wgt;
        adjMatTime[source][destination]=time;
        adjMatTime[destination][source]=time;
    }
    void printGraph(){
        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){
                System.out.print(adjMat[i][j]+" ");
            }
            System.out.println();
        }
    }

    void dfs(int node,boolean visited[]){
        visited[node]=true;
        System.out.println(node +" ");
        for(int i=0;i<adjMat[node].length;i++){
            if(adjMat[node][i] != 0 && (!visited[i])) {
                dfs(i,visited);
            }
        }
    }
    void DFS(int V){
        boolean visited[] = new boolean[vertices];
        System.out.println("DFS traversal :");
        dfs(V,visited);
    }

    public int minDistance(int dist[], boolean b[]){
        int min = Integer.MAX_VALUE, index = 1;
        for(int i=0;i<vertices;i++){
            if(b[i] == false && dist[i]<=min){
                min=dist[i];
                index = i;
            }
        }
        return index;
    }
    static void printPath(int currentVertex,int path[]){
        if(currentVertex == -1){
            return;
        }
        printPath(path[currentVertex],path);
        System.out.print(currentVertex+" ");
    }
    void printMinValue(int dist[],int src,int path[]){
        System.out.println("Vertex \tSource \tDistance \t\t Path");
        int i;
        for( i=0;i<vertices;i++){
            if(i != src) {
                System.out.print(i + " \t\t " + src + " \t\t " + dist[i]+" \t\t\t\t ");
                printPath(i, path);
                System.out.println();

            }
        }

    }

    public void dijkstra(int src) {

        int dist[] = new int[vertices];
        boolean b[] = new boolean[vertices];
        int path[] = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            b[i] = false;
        }
        dist[src] = 0;
        path[src] = -1;

        for (int i = 0; i < vertices; i++) {
            int u = minDistance(dist, b);
            b[u] = true;

            for (int j = 0; j < vertices; j++) {
                if (((!b[j] && adjMat[u][j] != 0) && (dist[u] != Integer.MAX_VALUE)) && dist[u] + adjMat[u][j] < dist[j]) {
                    path[j]=u;
                    dist[j] = dist[u] + adjMat[u][j];

                }
            }
            printMinValue(dist,src,path);
        }
    }

    void getMinPath(int currentVertex,int parent[], ArrayList<Integer> minPath){
        if(currentVertex == -1){
            return;
        }
        getMinPath(parent[currentVertex],parent,minPath);
        minPath.add(currentVertex);
    }

    public ArrayList<Object> getDijkstraMinPath(int src, int dest,boolean isCost)
    {
        ArrayList<Integer> minPath = new ArrayList<>();

        int dist[] = new int[vertices];
        boolean b[] = new boolean[vertices];
        int parent[] = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            b[i] = false;
        }
        dist[src] = 0;
        parent[src] = -1;

        for (int i = 0; i < vertices; i++) {
            int u = minDistance(dist, b);
            b[u] = true;

            for (int j = 0; j < vertices; j++) {
                if (((!b[j] && adjMat[u][j] != 0) && (dist[u] != Integer.MAX_VALUE)) && dist[u] + adjMat[u][j] < dist[j] && isCost) {
                    parent[j]=u;
                    dist[j] = dist[u] + adjMat[u][j];

                }
                else if(((!b[j] && adjMatTime[u][j] != 0) && (dist[u] != Integer.MAX_VALUE)) && dist[u] + adjMatTime[u][j] < dist[j]) {
                    parent[j]=u;
                    dist[j] = dist[u] + adjMatTime[u][j];

                }
            }
        }

        getMinPath(dest, parent, minPath);


        //prepare result with distance and min path
        ArrayList<Object> result = new ArrayList<>();
        result.add(dist[dest]);
        result.add(minPath);
        return result;
    }

    void dfs(int cur, int currentCost, int limit, int adj[][], boolean visited[], ArrayList<Integer> ansList)
    {
        visited[cur] = true;
        for(int i=0; i<vertices; i++)
        {
            if(visited[i] == false && adj[cur][i] > 0 && currentCost + adj[cur][i] <= limit)
            {
                ansList.add(i);
                dfs(i, currentCost + adj[cur][i], limit, adj, visited, ansList);
            }
        }
    }
    public ArrayList<Integer> getPossibleNodeList(int src, int limit, boolean isTime)
    {
        ArrayList<Integer> result = new ArrayList<>();
        boolean visited[] = new boolean[vertices];
        if(isTime) {
            dfs(src, 0, limit, adjMatTime, visited, result);
        }
        else {
            dfs(src, 0, limit, adjMat, visited, result);
        }
        return result;
    }

    // Kruskal

    // Find set of vertex i
    int find(int i, int parent[])
    {
        while (parent[i] != i)
            i = parent[i];
        return i;
    }

    // Does union of i and j. It returns
    // false if i and j are already in same
    // set.
    void union1(int i, int j, int parent[])
    {
        int a = find(i, parent);
        int b = find(j, parent);
        parent[a] = b;
    }

    public ArrayList<ArrayList<Integer>> kruskalMST()
    {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int costMatrix[][] = new int[vertices][vertices];
        for(int i=0; i<vertices; i++)
        {
            for(int j=0; j<vertices;j++)
            {
                costMatrix[i][j] = adjMat[i][j];
                if(costMatrix[i][j] == 0)
                {
                    costMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int parent[] = new int[vertices];
        int mincost = 0; // Cost of min MST.

        // Initialize sets of disjoint sets.
        for (int i = 0; i < vertices; i++)
            parent[i] = i;

        // Include minimum weight edges one by one
        int edge_count = 0;
        while (edge_count < vertices - 1) {
            int min = Integer.MAX_VALUE, a = -1, b = -1;
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (find(i, parent) != find(j, parent) && costMatrix[i][j] < min) {
                        min = costMatrix[i][j];
                        a = i;
                        b = j;
                    }
                }
            }
            if(a < 0 || b < 0) {
                return null;
            }
            union1(a, b, parent);
            edge_count++;
            mincost += min;

            ArrayList<Integer> startEndNode = new ArrayList<>();
            startEndNode.add(a);
            startEndNode.add(b);
            result.add(startEndNode);
        }
        System.out.println("Minimum cost= " + mincost);
        return result;
    }
}

