import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;

public class Edge implements Serializable{
    private int x1,y1,x2,y2;
    private int weight;
    private int time;
    Color color=Color.BLUE;
    Node node;

    public Edge() {
        x1=x2=y1=y2=0;
        weight=0;
        node=new Node();
    }
    public Edge(Node a, Node b) {
        x1=a.getX()+5;
        x2=b.getX()+5;
        y1=a.getY()+5;
        y2=b.getY()+5;
        this.calcWeight();
    }

    public void setEdge(Node a,Node b) {
        x1=a.getX();
        y1=a.getY();
        x2=b.getX();
        y2=b.getY();
    }

    public void setA(Node a) {
        x1=a.getX()+5;
        y1=a.getY()+5;
    }

    public void setB(Node b) {
        x2=b.getX()+5;
        y2=b.getY()+5;
    }

    public void setColor(Color c) {
        this.color=c;
    }
    public int calcWeight() {
        weight=(int)Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
        return weight;
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight=weight;
    }

    public int getTime(){
        return time;
    }
    public void setTime(int time){
        this.time=time;
    }

    public int calcTime(){
        time = (int)Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
        return time;
    }

    public void draw(Graphics g) {

        Graphics2D g2dLine = (Graphics2D) g;
        g2dLine.setColor(color);
        g2dLine.setStroke(new BasicStroke(3));
        g2dLine.drawLine(x1, y1, x2, y2);

//        int midPointX=(int)((x2+x1)/2);
//        int midPointY=(int)((y2+y1)/2);
//        Graphics2D g2dText = (Graphics2D) g.create();;
//        g2dText.setColor(Color.BLACK);
//        String test = "w="+(weight)+","+"t="+(time);
//        double angle = Math.atan((double)(y1-y2)/(x1-x2));
//        g2dText.rotate(angle,x1,y1);
//        g2dText.drawString(test, midPointX-5, midPointY-5);

        String test = "m="+(weight)+","+"t="+(time);

        int midPointX=(int)((x2+x1)/2);
        int midPointY=(int)((y2+y1)/2);

        Graphics2D g2dText = (Graphics2D) g.create();

        FontMetrics fm = g2dText.getFontMetrics();
        int x = (midPointX - fm.stringWidth(test) / 2);
        int y = (midPointY - fm.getHeight());

        double angle = Math.atan((double)(y1-y2)/(x1-x2));
        g2dText.rotate(angle, x, y);

        g2dText.setColor(Color.BLACK);

        g2dText.drawString(test, midPointX-fm.stringWidth(test)/2, midPointY-fm.getHeight());

        // g2d.drawString(Integer.toString(time), midPointX+15, midPointY+15);
        // System.out.println(g2d.drawString(Integer.toString(weight), midPointX, midPointY)+","+g2d.drawString(Integer.toString(weight), midPointX+5, midPointY+5));



    }

    public Node getPointA() {
        Node A=new Node(x1,y1);
        return A;
    }

    public Node getPointB() {
        Node B=new Node(x2,y2);
        return B;
    }

    public boolean hasA() {
        return (x1>0 && y1>0);
    }

    public boolean hasB() {
        return (x2>0 && y2>0);
    }

    public boolean equalsA(int x,int y) {
        return (x==x1 && y==y1);
    }

    public boolean equalsB(int x,int y) {
        return (x==x2 && y==y2);
    }

    public boolean equalsEdge(int x,int y) {
        return(this.equalsA(x, y)||this.equalsB(x, y));
    }

    // public double calcSlope() {
    //   return slope=(double)(y2-y1)/(x2-x1);
    // }
    // check clicked edge or not
    public float pDistance(float x, float y, float x1, float y1, float x2, float y2) {

        float A = x - x1; // position of point rel one end of line
        float B = y - y1;
        float C = x2 - x1; // vector along line
        float D = y2 - y1;
        float E = -D; // orthogonal vector
        float F = C;

        float dot = A * E + B * F;
        float len_sq = E * E + F * F;

        return (float) ((float) Math.abs(dot) / Math.sqrt(len_sq));
    }
    public boolean checkEdge(int x, int y) {
        //this.calcSlope();
        float dist = pDistance(x, y, this.x1, this.y1, this.x2, this.y2);
        System.out.println(dist);
        return dist < 20;
    }

    public boolean equal(int x,int y) {
        int c=((y)-y1)*(x2-x1);
        int d=(y2-y1)*((x)-x1);
        if(c>d-20 && c<d+20) return true;
        else return false;
    }


}
class EdgeList<Edge> implements Serializable {
    private ArrayList<Edge> edgeList;

    public EdgeList() {
        edgeList=new ArrayList<>();
    }

    public void add(Edge e) {
        edgeList.add(e);
    }

    public Edge get(int i) {
        return edgeList.get(i);
    }

    public ArrayList<Edge> getList(){
        return edgeList;
    }

    public int getIndex(Edge e) {
        return edgeList.indexOf(e);
    }


    public int size() {
        return edgeList.size();
    }

    public void clear() {edgeList.clear();}
}


