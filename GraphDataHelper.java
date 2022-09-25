public class GraphDataHelper {

    static Graph createGraphData(NodeList<Node> nodeList, EdgeList<Edge> edgeList)
    {
        Graph g = new Graph(nodeList.size());

        for(int i=0;i<edgeList.size();i++){
            int s = -1;
            int d = -1;
            int w = edgeList.get(i).getWeight();
            int t = edgeList.get(i).getTime();

            int x1 = edgeList.get(i).getPointA().getX() - 5;
            int x2 = edgeList.get(i).getPointB().getX() - 5;
            int y1 = edgeList.get(i).getPointA().getY() - 5;
            int y2 = edgeList.get(i).getPointB().getY() - 5;

            for(int j=0; j<nodeList.size(); j++)
            {
                if(nodeList.get(j).getX() == x1 && nodeList.get(j).getY() == y1)
                {
                    s = j;
                }
                else if(nodeList.get(j).getX() == x2 && nodeList.get(j).getY() == y2)
                {
                    d = j;
                }
            }
            g.addEdge(s,d,w,t);
        }
        return g;
    }

    static int getNodeIndex(NodeList<Node> nodeList, Node node)
    {
        for(int i=0; i<nodeList.size(); i++)
        {
            if(nodeList.get(i).equals(node))
            {
                return i;
            }
        }
        return -1;
    }
}
