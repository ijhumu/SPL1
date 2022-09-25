import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.io.*;
import java.util.*;

public class Gui extends JFrame {


        //classes
        Panel panel = new Panel();
        JFrame frame = new JFrame();
        Edge edge = new Edge();
        Node node = new Node();

        EdgeList<Edge> edgeList = new EdgeList();
        NodeList<Node> nodeList = new NodeList();

        //Mouselistners
        ClickListner1 cl1 = new ClickListner1();
        ClickListner2 cl2 = new ClickListner2();
        ClickListner4 cl4 = new ClickListner4();
        ClickListner3 cl3 = new ClickListner3();
        ClickListner5 cl5 = new ClickListner5();
        ClickListner6 cl6 = new ClickListner6();
        ClickListner7 cl7 = new ClickListner7();
        ClickListner8 cl8 = new ClickListner8();
        ClickListner9 cl9 = new ClickListner9();
        ClickListner10 cl10 = new ClickListner10();
        ClickListner11 cl11 = new ClickListner11();


        //buttons
        JRadioButton rbtnAddVertex = new JRadioButton("Add Area");
        JRadioButton rbtnAddEdge = new JRadioButton("Add Path");
        JRadioButton rbtnweight = new JRadioButton("Change a Cost :");
        JRadioButton rbtntime = new JRadioButton("Change a Time :");
        JRadioButton rbtnShortestPath = new JRadioButton("Cost Efficient Route");
        JRadioButton rbtnShortestTime = new JRadioButton("Time Efficient Route ");
        JRadioButton rbtnlimtime = new JRadioButton("Travel within limited Time:");
        JRadioButton rbtnlimcost = new JRadioButton("Travel within limited Cost:");
        JRadioButton rbtncovering =  new JRadioButton("Route covering all area:");
        JRadioButton rbtntimecircular = new JRadioButton("Time Efficient Circular Route:");
        JRadioButton rbtncostcircular = new JRadioButton("Cost Efficient Circular Route");
        JButton btnSave=new JButton("Save Graph");
        JButton btnLoad=new JButton("Load Graph");

        JTextArea jWeight = new JTextArea(5, 20);
        JTextArea jTime = new JTextArea(5, 20);
        JTextArea jLimTime = new JTextArea(5, 20);
        JTextArea jLimCost = new JTextArea(5, 20);
        JTextArea jSave = new JTextArea(5, 20);
        JTextArea jLoad = new JTextArea(5, 20);


        JLabel label = new JLabel();

        Node node1;
        Node node2;

        public Gui() {
                setFrame();
        }

        public void setFrame() {

                this.setSize(1000, 1000);
                this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
                this.getContentPane().setLayout(null);
                this.setBackground(Color.LIGHT_GRAY);
                this.addPanel();
                this.setButtons();
                this.setlabel();
                this.setVisible(true);
        }

        public void paint(Graphics g) {
                super.paint(g);
                //draw the nodes
                if (nodeList!= null && nodeList.size() > 0) {
                        for (int i = 0; i < nodeList.size(); i++) {
                                nodeList.get(i).draw(g);
                        }
                }

                //draw the edges
                if (edgeList!= null && edgeList.size() > 0) {
                        for (int i = 0; i < edgeList.size(); i++) {
                                edgeList.get(i).draw(g);
                        }
                }
        }

        public void addPanel() {
                panel.setBackground(Color.WHITE);
                panel.setBounds(330, 20, 640, 800);
                panel.setVisible(true);
                this.add(panel);
        }



        public void setButtons() {
                jWeight.setBounds(200, 268, 40, 16);
                this.add(jWeight);

                jTime.setBounds(200, 318, 40, 16);
                this.add(jTime);

                jLimTime.setBounds(200, 468, 40, 16);
                this.add(jLimTime);

                jLimCost.setBounds(200, 518, 40, 16);
                this.add(jLimCost);

                jSave.setBounds(200, 608, 50, 26);
                this.add(jSave);

                jLoad.setBounds(200, 678, 50, 26);
                this.add(jLoad);

                rbtnAddVertex.setBounds(10, 150, 200, 50);
                this.add(rbtnAddVertex);

                rbtnAddEdge.setBounds(10, 200, 200, 50);
                this.add(rbtnAddEdge);

                rbtnweight.setBounds(10, 250, 160, 50);
                this.add(rbtnweight);

                rbtntime.setBounds(10, 300, 160, 50);
                this.add(rbtntime);

                rbtnShortestPath.setBounds(10, 350, 200, 50);
                this.add(rbtnShortestPath);

                rbtnShortestTime.setBounds(10, 400, 200, 50);
                this.add(rbtnShortestTime);

                rbtnlimtime.setBounds(10, 450, 160, 50);
                this.add(rbtnlimtime);

                rbtnlimcost.setBounds(10, 500, 160, 50);
                this.add(rbtnlimcost);

                rbtncovering.setBounds(10, 550, 160, 50);
                this.add(rbtncovering);

               /* rbtntimecircular.setBounds(10, 550, 160, 50);
                this.add(rbtntimecircular);

                rbtncostcircular.setBounds(10, 600, 160, 50);
                this.add(rbtncostcircular);*/

                btnSave.setBounds(10, 600, 160, 50);
                this.add(btnSave);
                btnSave.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Boolean isSaved = false;
                                try {
                                        saveGraph();
                                        isSaved = true;
                                } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                }
                                if(isSaved) {
                                        setLabel(Color.GREEN,"Successfully Graph Saved");
                                } else {
                                        setLabel(Color.RED,"Error");
                                }

//                                int save = 0;
//                                if (edgeList.size() > 0) {
//
//                                        try {
//                                                save  = Integer.parseInt(jSave.getText());
//                                        } catch (NumberFormatException nfe) {
//                                                setLabel(Color.RED, nfe.getMessage());
//                                        }
//                                }
                        }
                });

                btnLoad.setBounds(10, 670, 160, 50);
                this.add(btnLoad);
                btnLoad.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                removeMouseAdapters();
                                Boolean isLoaded = false;
                                try {
                                        isLoaded = drawGraph();
                                } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                }
                                if(isLoaded) {
                                        setLabel(Color.GREEN, "Graph Load successful");
                                } else {
                                        setLabel(Color.RED, "Error");
                                }

//                                int load = 0;
//                                if (edgeList.size() > 0) {
//
//                                        try {
//                                               load  = Integer.parseInt(jLoad.getText());
//                                        } catch (NumberFormatException nfe) {
//                                                setLabel(Color.RED, nfe.getMessage());
//                                        }
//
//                                }
                        }
                });


                ButtonGroup bg = new ButtonGroup();
                bg.add(rbtnAddEdge);
                bg.add(rbtnAddVertex);
                bg.add(rbtnShortestPath);
                bg.add(rbtnShortestTime);
                bg.add(rbtnweight);
                bg.add(rbtntime);
                bg.add(rbtnlimtime);
                bg.add(rbtnlimcost);
                bg.add(rbtntimecircular);
                bg.add(rbtncostcircular);
                bg.add(rbtncovering);



                //add nodes
                rbtnAddVertex.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                setLabel(Color.BLUE, "click inside the white box to create vertex.");
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.removeMouseListener(cl3);
                                panel.addMouseListener(cl1);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);

                                nodeDefaultColor();
                                edgeDefaultColor();
                                repaint();
                        }
                });
                //add edges
                rbtnAddEdge.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                setLabel(Color.BLUE, "click on a vertex.");

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl4);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.addMouseListener(cl2);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);

                                nodeDefaultColor();
                                edgeDefaultColor();
                                repaint();
                        }
                });
                //weight
                rbtnweight.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                setLabel(Color.RED, "Enter weight you want and select an area that you wish to change");
                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.addMouseListener(cl4);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);
                        }
                });

                rbtntime.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                setLabel(Color.RED, "Enter time you want and select an area that you wish to change");
                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl4);
                                panel.addMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);
                        }
                });


                //shortest path
                rbtnShortestPath.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                edgeDefaultColor();
                                setLabel(Color.RED, "Click on a starting vertex and the click on the destination.");

                                node1 = new Node();
                                node2 = new Node();

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.addMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);

                        }
                });
                // shortest time
                rbtnShortestTime.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                edgeDefaultColor();
                                setLabel(Color.RED, "Click on a starting vertex and the click on the destination.");

                                node1 = new Node();
                                node2 = new Node();

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.addMouseListener(cl10);
                                panel.removeMouseListener(cl11);
                        }
                });


                rbtnlimtime.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                edgeDefaultColor();
                                setLabel(Color.RED, "Enter starting vertex");

                                node1 = new Node();
                                node2 = new Node();

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.addMouseListener(cl6);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);

                        }
                });


                rbtnlimcost.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                edgeDefaultColor();
                                setLabel(Color.RED, "Enter starting vertex");

                                node1 = new Node();
                                node2 = new Node();

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.addMouseListener(cl7);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);


                        }
                });

                rbtntimecircular.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                edgeDefaultColor();
                                setLabel(Color.RED, "Click on a starting vertex and the click on the destination.");

                                node1 = new Node();
                                node2 = new Node();

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.addMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);

                        }
                });

                rbtncostcircular.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                edgeDefaultColor();
                                setLabel(Color.RED, "Click on a starting vertex and the click on the destination.");

                                node1 = new Node();
                                node2 = new Node();

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.addMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.removeMouseListener(cl11);
                        }
                });

                rbtncovering.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                edgeDefaultColor();
                                //setLabel(Color.RED, "Click on a starting vertex and the click on the destination.");

                                Graph g = GraphDataHelper.createGraphData(nodeList, edgeList);
                                ArrayList<ArrayList<Integer>> startEndNodeList = g.kruskalMST();
                                drawMSTPath(startEndNodeList);

                                node1 = new Node();
                                node2 = new Node();

                                panel.removeMouseListener(cl1);
                                panel.removeMouseListener(cl2);
                                panel.removeMouseListener(cl4);
                                panel.removeMouseListener(cl3);
                                panel.removeMouseListener(cl5);
                                panel.removeMouseListener(cl6);
                                panel.removeMouseListener(cl7);
                                panel.removeMouseListener(cl8);
                                panel.removeMouseListener(cl9);
                                panel.removeMouseListener(cl10);
                                panel.addMouseListener(cl11);
                        }
                });

        }

        public void nodeDefaultColor() {
                if(nodeList == null) {
                        return;
                }
                for (int i = 0; i < nodeList.size(); i++) {
                        nodeList.get(i).setColor(Color.BLUE);
                }
        }

        public void edgeDefaultColor() {
                if(edgeList == null) {
                        return;
                }
                for (int i = 0; i < edgeList.size(); i++) {
                        edgeList.get(i).setColor(Color.BLUE);
                }
        }

        public void setlabel() {
                label.setBounds(350, 670, 300, 30);
                label.setForeground(Color.RED);
                panel.add(label);
        }

        public void setLabel(Color c, String message) {
                label.setForeground(c);
                label.setText(message);
        }

        public void removeMouseAdapters() {
                panel.removeMouseListener(cl1);
                panel.removeMouseListener(cl2);
                panel.removeMouseListener(cl4);
                panel.removeMouseListener(cl3);
                panel.removeMouseListener(cl5);
                panel.removeMouseListener(cl6);
                panel.removeMouseListener(cl7);
                panel.removeMouseListener(cl8);
                panel.removeMouseListener(cl9);
                panel.removeMouseListener(cl10);
                panel.removeMouseListener(cl10);

        }

        //node
        class ClickListner1 extends MouseAdapter {

                ClickListner1() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        Node node = new Node();
                        int x = e.getX() + 325;
                        int y = e.getY() + 45;
                        node.setColor(Color.RED);
                        node.setX(x);
                        node.setY(y);
                        nodeList.add(node);
                        repaint();
                }
        }

        //edge
        class ClickListner2 extends MouseAdapter {
                int i = 0;

                ClickListner2() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        Node nodes = new Node();
                        int x = e.getX() + 325;
                        int y = e.getY() + 45;

                        //user clicked on specific nodes
                        for (i = 0; i < nodeList.size(); i++) {
                                int z1 = nodeList.get(i).getX(), z2 = nodeList.get(i).getY();

                                if (((x <= z1 + 10) && (x >= z1 - 10)) && ((y <= z2 + 10) && (y >= z2 - 10))) {
                                        nodeList.get(i).setColor(Color.GREEN);
                                        repaint();
                                        if (edge.hasA()) {
                                                nodes.setX(z1);//change back to z1,z2
                                                nodes.setY(z2);
                                                edge.setB(nodes);
                                                break;
                                        } else {
                                                nodes.setX(z1);
                                                nodes.setY(z2);
                                                edge.setA(nodes);
                                                break;
                                        }
                                }
                        }
                        //check a,b exist
                        if (edge.hasA() && edge.hasB()) {
                                edge.calcWeight();
                                edge.calcTime();
                                edgeList.add(edge);
                                setLabel(Color.GREEN,"Edge Added successfully!!!");
                                edge = new Edge();
                                nodeDefaultColor();//change color back
                                repaint();
                        }
                }
        }

        class ClickListner4 extends MouseAdapter {
                int x, y, i = 0;

                public ClickListner4() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        x = e.getX() + 325;
                        y = e.getY() + 45;

                        System.out.println(x);
                        System.out.println(y);
                        int newWeight = 0;
                        if (edgeList.size() > 0) {

                                try {
                                        newWeight = Integer.parseInt(jWeight.getText());
                                } catch (NumberFormatException nfe) {
                                        setLabel(Color.RED, nfe.getMessage());
                                }

                                for (i = 0; i < edgeList.size(); i++) {

                                        if (edgeList.get(i).checkEdge(x, y) && newWeight != 0) {
                                                edgeList.get(i).setWeight(newWeight);
                                                setLabel(Color.GREEN, "New Cost added successfully.");
                                                repaint();
                                                break;
                                        } else {
                                                setLabel(Color.RED, "Error adding cost, please check the instructions.");
                                        }
                                }

                        }


                        repaint();
                }
        }


        class ClickListner5 extends MouseAdapter {
                int x, y, i = 0;

                public ClickListner5() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        x = e.getX() + 325;
                        y = e.getY() + 45;

                        System.out.println(x);
                        System.out.println(y);
                        int newTime = 0;
                        if (edgeList.size() > 0) {

                                try {
                                        newTime = Integer.parseInt(jTime.getText());
                                } catch (NumberFormatException nfe) {
                                        setLabel(Color.RED, nfe.getMessage());
                                }

                                for (i = 0; i < edgeList.size(); i++) {

                                        if (edgeList.get(i).checkEdge(x, y) && newTime != 0) {
                                                edgeList.get(i).setTime(newTime);
                                                setLabel(Color.GREEN, "New Time added successfully.");
                                                repaint();
                                                break;
                                        } else {
                                                setLabel(Color.RED, "Error adding Time, please check the instructions.");
                                        }
                                }

                        }


                        repaint();
                }
        }


        //shortest path
        class ClickListner3 extends MouseAdapter {
                public ClickListner3() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        edgeDefaultColor();
                        int x = e.getX() + 325;
                        int y = e.getY() + 45;
                        int i;
                        //node2 = new Node();
                        for (i = 0; i < nodeList.size(); i++) {
                                int z1 = nodeList.get(i).getX(), z2 = nodeList.get(i).getY();

                                if (((x <= z1 + 10) && (x >= z1 - 10)) && ((y <= z2 + 10) && (y >= z2 - 10))) {

                                        if (node1.isEmpty()) {
                                                node1 = nodeList.get(i);
                                                nodeList.get(i).setColor(Color.GREEN);
                                                setLabel(Color.RED, "Click on destination vertex to find the shortest path.");
                                        } else if (node2.isEmpty()) {
                                                node2 = nodeList.get(i);
                                                nodeList.get(i).setColor(Color.GREEN);
                                        }
                                        else {
                                                node1.setColor(Color.BLUE);
                                                node1 = node2;
                                                node2 = nodeList.get(i);
                                                nodeList.get(i).setColor(Color.GREEN);
                                        }
                                        repaint();
                                        break;
                                }

                        }
                        if(!node1.isEmpty() && !node2.isEmpty()){
                                Graph g = GraphDataHelper.createGraphData(nodeList, edgeList);
                                int source = GraphDataHelper.getNodeIndex(nodeList, node1); //here coordinate
                                int destination = GraphDataHelper.getNodeIndex(nodeList, node2);
                                ArrayList<Object> minPathResult = g.getDijkstraMinPath(source, destination,true);
                                int minCost = (int) minPathResult.get(0);
                                ArrayList<Integer> nodeIndicesList = (ArrayList<Integer>) minPathResult.get(1); //0 index cost,1 index path
                                System.out.println(minCost);
                                System.out.println(nodeIndicesList);
                                drawResultPath(nodeIndicesList, minCost,0);
                        }
                }
        }

        class ClickListner10 extends MouseAdapter {
                public ClickListner10() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        edgeDefaultColor();
                        int x = e.getX() + 325;
                        int y = e.getY() + 45;
                        int i;
                        //node2 = new Node();
                        for (i = 0; i < nodeList.size(); i++) {
                                int z1 = nodeList.get(i).getX(), z2 = nodeList.get(i).getY();

                                if (((x <= z1 + 10) && (x >= z1 - 10)) && ((y <= z2 + 10) && (y >= z2 - 10))) {

                                        if (node1.isEmpty()) {
                                                node1 = nodeList.get(i);
                                                nodeList.get(i).setColor(Color.GREEN);
                                                setLabel(Color.RED, "Click on destination vertex to find the shortest time.");
                                        } else if (node2.isEmpty()) {
                                                node2 = nodeList.get(i);
                                                nodeList.get(i).setColor(Color.GREEN);
                                        }
                                        else {
                                                node1.setColor(Color.BLUE);
                                                node1 = node2;
                                                node2 = nodeList.get(i);
                                                nodeList.get(i).setColor(Color.GREEN);
                                        }
                                        repaint();
                                        break;
                                }

                        }
                        if(!node1.isEmpty() && !node2.isEmpty()){
                                Graph g = GraphDataHelper.createGraphData(nodeList, edgeList);
                                int source = GraphDataHelper.getNodeIndex(nodeList, node1); //here coordinate
                                int destination = GraphDataHelper.getNodeIndex(nodeList, node2);
                                ArrayList<Object> minPathResult = g.getDijkstraMinPath(source, destination,false);
                                int minTime = (int) minPathResult.get(0);
                                ArrayList<Integer> nodeIndicesList = (ArrayList<Integer>) minPathResult.get(1); //0 index cost,1 index path
                                System.out.println(minTime);
                                System.out.println(nodeIndicesList);
                                drawResultPath(nodeIndicesList,0, minTime);
                        }
                }
        }


        //limited time
        class ClickListner6 extends MouseAdapter {
                int x, y, i = 0;

                public ClickListner6() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        x = e.getX() + 325;
                        y = e.getY() + 45;

                        System.out.println(x);
                        System.out.println(y);

                        int timeLimit = 0;
                        try {
                                timeLimit = Integer.parseInt(jLimTime.getText());
                        } catch (NumberFormatException nfe) {
                                setLabel(Color.RED, nfe.getMessage());
                        }

                        for (i = 0; i < nodeList.size(); i++) {
                                nodeList.get(i).setColor(Color.BLUE);
                        }
                        repaint();
                        for (i = 0; i < nodeList.size(); i++) {
                                int z1 = nodeList.get(i).getX(), z2 = nodeList.get(i).getY();

                                if (((x <= z1 + 10) && (x >= z1 - 10)) && ((y <= z2 + 10) && (y >= z2 - 10))) {
                                        node1 = nodeList.get(i);
                                        nodeList.get(i).setColor(Color.GREEN);
                                        repaint();
                                        break;
                                }

                        }
                        if(!node1.isEmpty()){
                                Graph g = GraphDataHelper.createGraphData(nodeList, edgeList);
                                int source = GraphDataHelper.getNodeIndex(nodeList, node1); //here coordinate
                                System.out.println("source index: " + source);
                                ArrayList<Integer> resultNodeList = g.getPossibleNodeList(source, timeLimit, true);
                                System.out.println(resultNodeList);
                                drawResultNodes(resultNodeList);
                        }

                        repaint();
                }
        }

        //limited cost
        class ClickListner7 extends MouseAdapter {
                int x, y, i = 0;

                public ClickListner7() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        x = e.getX() + 325;
                        y = e.getY() + 45;

                        System.out.println(x);
                        System.out.println(y);


                        int costLimit = 0;
                        try {
                                costLimit = Integer.parseInt(jLimCost.getText());
                        } catch (NumberFormatException nfe) {
                                setLabel(Color.RED, nfe.getMessage());
                        }

                        for (i = 0; i < nodeList.size(); i++) {
                                nodeList.get(i).setColor(Color.BLUE);
                        }
                        repaint();
                        for (i = 0; i < nodeList.size(); i++) {
                                int z1 = nodeList.get(i).getX(), z2 = nodeList.get(i).getY();

                                if (((x <= z1 + 10) && (x >= z1 - 10)) && ((y <= z2 + 10) && (y >= z2 - 10))) {
                                        node1 = nodeList.get(i);
                                        nodeList.get(i).setColor(Color.GREEN);
                                        repaint();
                                        break;
                                }

                        }
                        if(!node1.isEmpty()){
                                Graph g = GraphDataHelper.createGraphData(nodeList, edgeList);
                                int source = GraphDataHelper.getNodeIndex(nodeList, node1); //here coordinate
                                System.out.println("source index: " + source);
                                ArrayList<Integer> resultNodeList = g.getPossibleNodeList(source, costLimit, false);
                                System.out.println(resultNodeList);
                                drawResultNodes(resultNodeList);
                        }

                        repaint();
                }
        }
        //time efficient circular
        class ClickListner8 extends MouseAdapter {
                int x, y, i = 0;

                public ClickListner8() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        x = e.getX() + 325;
                        y = e.getY() + 45;

                        System.out.println(x);
                        System.out.println(y);


                        repaint();
                }
        }
        //cost efficient circular
        class ClickListner9 extends MouseAdapter {
                int x, y, i = 0;

                public ClickListner9() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        x = e.getX() + 325;
                        y = e.getY() + 45;

                        System.out.println(x);
                        System.out.println(y);


                        repaint();
                }
        }

        class ClickListner11 extends MouseAdapter {
                public ClickListner11() {
                        super();
                }

                public void mouseClicked(MouseEvent e) {
                        edgeDefaultColor();
                        int x = e.getX() + 325;
                        int y = e.getY() + 45;
                        int i;
                        //node2 = new Node();

                }
        }




        private int getIndexOf(Edge e) {
                int x1,x2,y1,y2,a,b,c,d,index=-1;//a=x1 b=x2 c=y1 d=y2 where abcd are point of edge from edgelist
                x1=e.getPointA().getX();
                x2=e.getPointB().getX();
                y1=e.getPointA().getY();
                y2=e.getPointB().getY();
                for(int i=0;i<edgeList.size();i++) {
                        a=edgeList.get(i).getPointA().getX();
                        b=edgeList.get(i).getPointB().getX();
                        c=edgeList.get(i).getPointA().getY();
                        d=edgeList.get(i).getPointB().getY();
                        if(a==x1&&b==x2&&c==y1&&d==y2) {
                                index=i;
                                break;
                        }
                        if(a==x2&&b==x1&&c==y2&&d==y1) {
                                index= i;
                                break;
                        }
                }
                return index;
        }

        private void drawMSTPath(ArrayList<ArrayList<Integer>> startEndNodeList)
        {
                if(startEndNodeList == null) {
                        setLabel(Color.RED,"No way to visit all nodes");
                        repaint();
                        return;
                }
                int totalCost = 0;
                for(int i=0;i<startEndNodeList.size();i++) {
                        int nodeIndex1 = startEndNodeList.get(i).get(0);
                        int nodeIndex2 = startEndNodeList.get(i).get(1);
                        Node pathNode1 = nodeList.get(nodeIndex1);
                        Node pathNode2 = nodeList.get(nodeIndex2);
                        try {
                                int index= getIndexOf(new Edge(pathNode1,pathNode2));
                                edgeList.get(index).setColor(Color.RED);
                                totalCost += edgeList.get(index).getWeight();
                                setLabel(Color.GREEN, "Cost to visit all nodes: " + totalCost);
                        }
                        catch(IndexOutOfBoundsException e){
                                setLabel(Color.RED,"Error!!!"+e.getMessage());
                        }
                }

                repaint();
        }

        private void drawResultPath(ArrayList<Integer> nodeIndicesList, int cost, int time)
        {
                for(int i=0;i<nodeIndicesList.size() - 1;i++) {
                        int nodeIndex1 = nodeIndicesList.get(i);
                        int nodeIndex2 = nodeIndicesList.get(i+1);
                        Node pathNode1 = nodeList.get(nodeIndex1);
                        Node pathNode2 = nodeList.get(nodeIndex2);
                        try {
                                int index= getIndexOf(new Edge(pathNode1,pathNode2));
                                edgeList.get(index).setColor(Color.RED);

                        }
                        catch(IndexOutOfBoundsException e){
                                setLabel(Color.RED,"Error!!!"+e.getMessage());
                        }
                }

                repaint();

                if(cost>=Integer.MAX_VALUE || time>=Integer.MAX_VALUE){
                        setLabel(Color.RED, "Not found");
                }
                else{
                        if(time==0) {
                                setLabel(Color.GREEN, "Shortest path found, cost: " + cost);
                        }
                        else {
                                setLabel(Color.GREEN, "Shortest time found, time: " + time);
                        }
                }

        }

        private void drawResultNodes(ArrayList<Integer> nodeIndicesList)
        {
                //TODO
                for(int i=0;i<nodeIndicesList.size();i++) {
                        int nodeIndex = nodeIndicesList.get(i);
                        Node visitedNode = nodeList.get(nodeIndex);
                        try {
                                visitedNode.setColor(Color.ORANGE);
                        }
                        catch(IndexOutOfBoundsException e){
                                setLabel(Color.RED,"Error!!!"+e.getMessage());
                        }
                }
                repaint();
                setLabel(Color.GREEN,"Number of node can be visited: " + nodeIndicesList.size());

        }

        private void saveData(Object object, String path) throws IOException {
                try {
                        FileOutputStream fos = new FileOutputStream(path);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        // write object to file
                        oos.writeObject(object);
                        System.out.println("Done");
                        // closing resources
                        oos.close();
                        fos.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public Object readData(String path) throws IOException {
                ObjectInputStream objectinputstream = null;
                try {
                        FileInputStream streamIn = new FileInputStream(path);
                        objectinputstream = new ObjectInputStream(streamIn);
                        return objectinputstream.readObject();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        if(objectinputstream != null){
                                objectinputstream.close();
                        }
                }
                return null;
        }
        private void saveGraph() throws IOException {

                String fileName = jSave.getText();
                String path1 = "edgeList_" + fileName + ".txt";
                String path2 = "nodeList_" + fileName +".txt";
                saveData(edgeList, path1);
                saveData(nodeList, path2);
        }

        private void loadGraph() throws IOException {
                String fileName = jLoad.getText();
                String path1 = "edgeList_" + fileName + ".txt";
                String path2 = "nodeList_" + fileName + ".txt";
                nodeList = (NodeList<Node>)readData(path2);
                edgeList = (EdgeList<Edge>)readData(path1);
        }
        private boolean drawGraph() throws IOException {
                //clear all
                if(edgeList != null) {
                        edgeList.clear();
                }
                if(nodeList != null) {
                        nodeList.clear();
                }

                edge = new Edge();
                node = new Node();

                node1 = new Node();
                node2 = new Node();

                repaint();

                loadGraph();
                nodeDefaultColor();
                edgeDefaultColor();
                repaint();

                if(nodeList == null) {
                        return  false;
                } else {
                        return true;
                }
        }

        class Panel extends JPanel {
                public Panel() {
                        this.setBounds(230, 10, 440, 600);
                        this.setBackground(Color.GRAY);
                        this.setVisible(true);

                }
        }
}
