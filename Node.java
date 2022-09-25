import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Node implements Serializable {
    private int x;
    private int y;
    private Color color;

    public Node(){
        this.color=color.BLUE;
        x=y=0;
    }
    public Node(int a, int b) {
        this.color=color.BLUE;
        x=a;
        y=b;
    }
    public void setX(int a) {
        x=a;
    }
    public void setY(int b) {
        y=b;
    }
    public void setColor(Color c) {
        this.color=c;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isEmpty() {
        if(this.x==0 && this.y==0)
            return true;
        else return false;
    }
    public String getColor() {
        return color.toString();
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 15, 15);

    }
}

class NodeList<Node> implements Serializable{
    private ArrayList<Node> nodeList;
    public NodeList() {
        nodeList=new ArrayList<>();
    }
    public void add(Node d) {
        nodeList.add(d);
    }
    public int size() {
        return nodeList.size();
    }
    public Node get(int i){
        return nodeList.get(i);
    }
    public ArrayList<Node> getList(){
        return nodeList;
    }
    public Iterator<Node> iterator() {
        ArrayList<Node> list=new ArrayList<>();
        for(int i=0;i<nodeList.size();i++) {
            list.add(nodeList.get(i));
        }
        return list.iterator();
    }
    public void clear() {nodeList.clear();}
}

