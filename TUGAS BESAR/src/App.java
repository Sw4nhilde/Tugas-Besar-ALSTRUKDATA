import OtherPackage.GraphSimulatorSwing;
import OtherPackage.MazeGameSwing;
import OtherPackage.MorseTranslatorSwing;
import OtherPackage.PendaftaranMahasiswa;
import OtherPackage.ToDoList;
import java.awt.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Miscellaneous Programme");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));

        JButton option1Button = new JButton("MAZE GAME");
        option1Button.addActionListener(e -> {
            displayArea.append("Option 1: MazeGameSwing.maze() called\n");
            JFrame optionFrame = new JFrame("MazeGame");
            optionFrame.setSize(300, 200);
            optionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionFrame.add(new JLabel("Muhammad Eka Mandiri Sujanto (1237050079)"), BorderLayout.CENTER);
            optionFrame.setVisible(true);
            MazeGameSwing.maze(args);
        });

        JButton option2Button = new JButton("TO-DO-LIST");
        option2Button.addActionListener(e -> {
            displayArea.append("Option 2: ToDoList.TDL() called\n");
            JFrame optionFrame = new JFrame("To Do List");
            optionFrame.setSize(300, 200);
            optionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionFrame.add(new JLabel("Nabilah Nuril Zahra M. Karim 1237050120"), BorderLayout.CENTER);
            optionFrame.setVisible(true);
            ToDoList.TDL(args);
        });

        JButton option3Button = new JButton("MORSE TRANSLATOR");
        option3Button.addActionListener(e -> {
            displayArea.append("Option 3: MorseTranslatorSwing.translator() called\n");
            JFrame optionFrame = new JFrame("Morse Translator");
            optionFrame.setSize(300, 200);
            optionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionFrame.add(new JLabel("Muhammad Irsyad Mustaqim 1237050050"), BorderLayout.CENTER);
            optionFrame.setVisible(true);
            MorseTranslatorSwing.translator(args);
        });

        JButton option4Button = new JButton("GRAPH SIMULATOR");
        option4Button.addActionListener(e -> {
            displayArea.append("Option 4: GraphSimulatorSwing.sim() called\n");
            JFrame optionFrame = new JFrame("Graph Simulator");
            optionFrame.setSize(300, 200);
            optionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionFrame.add(new JLabel("Nadia Puspa Dewi 1237050146"), BorderLayout.CENTER);
            optionFrame.setVisible(true);
            GraphSimulatorSwing.sim(args);
        });

        JButton option5Button = new JButton("PENDAFTARAN MAHASISWA");
        option5Button.addActionListener(e -> {
            displayArea.append("Option 5: PendaftaranMahasiswa.main() called\n");
            JFrame optionFrame = new JFrame("Pendaftaran Mahasiswa");
            optionFrame.setSize(300, 200);
            optionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            optionFrame.add(new JLabel("Raflhy Nur Ramadhan 1237050004"), BorderLayout.CENTER);
            optionFrame.setVisible(true);
            PendaftaranMahasiswa.main(args);
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            displayArea.append("Exiting application...\n");
            System.exit(0);
        });

        buttonPanel.add(option1Button);
        buttonPanel.add(option2Button);
        buttonPanel.add(option3Button);
        buttonPanel.add(option4Button);
        buttonPanel.add(option5Button);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }
}
