package OtherPackage;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class GraphSimulatorSwing {
    static class Graph {
        private Map<String, List<String>> adjacencyList;

        public Graph() {
            adjacencyList = new HashMap<>();
        }

        public void addNode(String node) {
            adjacencyList.putIfAbsent(node, new ArrayList<>());
        }

        public void addEdge(String node1, String node2) {
            adjacencyList.putIfAbsent(node1, new ArrayList<>());
            adjacencyList.putIfAbsent(node2, new ArrayList<>());
            adjacencyList.get(node1).add(node2);
            adjacencyList.get(node2).add(node1);
        }

        public void removeNode(String node) {
            adjacencyList.values().forEach(list -> list.remove(node));
            adjacencyList.remove(node);
        }

        public void removeEdge(String node1, String node2) {
            if (adjacencyList.containsKey(node1)) {
                adjacencyList.get(node1).remove(node2);
            }
            if (adjacencyList.containsKey(node2)) {
                adjacencyList.get(node2).remove(node1);
            }
        }

        public String displayGraph() {
            StringBuilder graphStructure = new StringBuilder();
            for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
                graphStructure.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
            }
            return graphStructure.toString();
        }

        public List<String> shortestPath(String start, String end) {
            if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(end)) {
                return null;
            }

            Queue<List<String>> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            queue.add(Arrays.asList(start));
            visited.add(start);

            while (!queue.isEmpty()) {
                List<String> path = queue.poll();
                String lastNode = path.get(path.size() - 1);

                if (lastNode.equals(end)) {
                    return path;
                }

                for (String neighbor : adjacencyList.getOrDefault(lastNode, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        queue.add(newPath);
                    }
                }
            }
            return null;
        }

        public int countConnectedComponents() {
            Set<String> visited = new HashSet<>();
            int components = 0;

            for (String node : adjacencyList.keySet()) {
                if (!visited.contains(node)) {
                    bfsVisit(node, visited);
                    components++;
                }
            }

            return components;
        }

        private void bfsVisit(String start, Set<String> visited) {
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                for (String neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    public static void sim(String[] args) {
        Graph graph = new Graph();

        JFrame frame = new JFrame("Graph Simulator");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 1, 5, 5));

        JButton addNodeButton = new JButton("Tambah Node");
        addNodeButton.addActionListener(e -> {
            String node = JOptionPane.showInputDialog(frame, "Masukkan nama node:");
            if (node != null && !node.trim().isEmpty()) {
                graph.addNode(node.trim());
                displayArea.setText(graph.displayGraph());
            }
        });

        JButton addEdgeButton = new JButton("Tambah Edge");
        addEdgeButton.addActionListener(e -> {
            String node1 = JOptionPane.showInputDialog(frame, "Masukkan node pertama:");
            String node2 = JOptionPane.showInputDialog(frame, "Masukkan node kedua:");
            if (node1 != null && node2 != null && !node1.trim().isEmpty() && !node2.trim().isEmpty()) {
                graph.addEdge(node1.trim(), node2.trim());
                displayArea.setText(graph.displayGraph());
            }
        });

        JButton removeNodeButton = new JButton("Hapus Node");
        removeNodeButton.addActionListener(e -> {
            String node = JOptionPane.showInputDialog(frame, "Masukkan nama node yang ingin dihapus:");
            if (node != null && !node.trim().isEmpty()) {
                graph.removeNode(node.trim());
                displayArea.setText(graph.displayGraph());
            }
        });

        JButton removeEdgeButton = new JButton("Hapus Edge");
        removeEdgeButton.addActionListener(e -> {
            String node1 = JOptionPane.showInputDialog(frame, "Masukkan node pertama:");
            String node2 = JOptionPane.showInputDialog(frame, "Masukkan node kedua:");
            if (node1 != null && node2 != null && !node1.trim().isEmpty() && !node2.trim().isEmpty()) {
                graph.removeEdge(node1.trim(), node2.trim());
                displayArea.setText(graph.displayGraph());
            }
        });

        JButton shortestPathButton = new JButton("Pencarian Jalur Terpendek");
        shortestPathButton.addActionListener(e -> {
            String start = JOptionPane.showInputDialog(frame, "Masukkan node awal:");
            String end = JOptionPane.showInputDialog(frame, "Masukkan node tujuan:");
            if (start != null && end != null && !start.trim().isEmpty() && !end.trim().isEmpty()) {
                List<String> path = graph.shortestPath(start.trim(), end.trim());
                if (path != null) {
                    JOptionPane.showMessageDialog(frame, "Jalur terpendek: " + path);
                } else {
                    JOptionPane.showMessageDialog(frame, "Tidak ada jalur yang ditemukan.");
                }
            }
        });

        JButton connectedComponentsButton = new JButton("Hitung Komponen Terhubung");
        connectedComponentsButton.addActionListener(e -> {
            int components = graph.countConnectedComponents();
            JOptionPane.showMessageDialog(frame, "Jumlah komponen terhubung: " + components);
        });

        JButton displayGraphButton = new JButton("Tampilkan Graf");
        displayGraphButton.addActionListener(e -> displayArea.setText(graph.displayGraph()));

        JButton exitButton = new JButton("Keluar");
        exitButton.addActionListener(e -> frame.dispose());

        buttonPanel.add(addNodeButton);
        buttonPanel.add(addEdgeButton);
        buttonPanel.add(removeNodeButton);
        buttonPanel.add(removeEdgeButton);
        buttonPanel.add(shortestPathButton);
        buttonPanel.add(connectedComponentsButton);
        buttonPanel.add(displayGraphButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}