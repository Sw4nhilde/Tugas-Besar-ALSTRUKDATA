package OtherPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.*;

public class MorseTranslatorSwing{
    public static void translator(String[] args) {
        // Inisialisasi frame
        JFrame frame = new JFrame("Penerjemah Teks ke Morse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Ukuran frame lebih besar dari kontainer
        frame.setLayout(new GridBagLayout()); // Menggunakan GridBagLayout agar container di tengah

        // Panel utama sebagai container
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(400, 400)); // Ukuran container
        container.setBackground(Color.LIGHT_GRAY); // Warna latar belakang container
        container.setLayout(new BorderLayout()); // Layout di dalam container

        // Panel input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel inputLabel = new JLabel("Masukkan Teks: ");
        inputPanel.add(inputLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField inputField = new JTextField(20);
        inputPanel.add(inputField, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JButton translateButton = new JButton("Terjemahkan");
        inputPanel.add(translateButton, gbc);

        // Panel output
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());

        JLabel outputLabel = new JLabel("Hasil Terjemahan Morse:");
        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea outputArea = new JTextArea(8, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.BOLD, 18)); // Ukuran font diperbesar
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        outputArea.setMargin(new Insets(10, 10, 10, 10)); // Margin untuk memberi jarak pada teks

        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel untuk tombol keluar (Exit)
        JPanel exitPanel = new JPanel();
        JButton exitButton = new JButton("Keluar");
        exitPanel.add(exitButton);

        // Tambahkan panel ke container
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(outputPanel, BorderLayout.CENTER);
        container.add(exitPanel, BorderLayout.SOUTH);

        // Tambahkan container ke frame
        frame.add(container);

        // Buat tabel kode Morse
        Hashtable<Character, String> morseTable = new Hashtable<>();
        morseTable.put('A', ".-");
        morseTable.put('B', "-...");
        morseTable.put('C', "-.-.");
        morseTable.put('D', "-..");
        morseTable.put('E', ".");
        morseTable.put('F', "..-.");
        morseTable.put('G', "--.");
        morseTable.put('H', "....");
        morseTable.put('I', "..");
        morseTable.put('J', ".---");
        morseTable.put('K', "-.-");
        morseTable.put('L', ".-..");
        morseTable.put('M', "--");
        morseTable.put('N', "-.");
        morseTable.put('O', "---");
        morseTable.put('P', ".--.");
        morseTable.put('Q', "--.-");
        morseTable.put('R', ".-.");
        morseTable.put('S', "...");
        morseTable.put('T', "-");
        morseTable.put('U', "..-");
        morseTable.put('V', "...-");
        morseTable.put('W', ".--");
        morseTable.put('X', "-..-");
        morseTable.put('Y', "-.--");
        morseTable.put('Z', "--..");
        morseTable.put('0', "-----");
        morseTable.put('1', ".----");
        morseTable.put('2', "..---");
        morseTable.put('3', "...--");
        morseTable.put('4', "....-");
        morseTable.put('5', ".....");
        morseTable.put('6', "-....");
        morseTable.put('7', "--...");
        morseTable.put('8', "---..");
        morseTable.put('9', "----.");
        morseTable.put(' ', "/"); // Simbol spasi untuk pemisah antar kata

        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText().toUpperCase();
                StringBuilder morseCode = new StringBuilder();
                boolean hasInvalidChar = false;

                for (int i = 0; i < inputText.length(); i++) {
                    char c = inputText.charAt(i);
                    if (morseTable.containsKey(c)) {
                        morseCode.append(morseTable.get(c)).append(" ");
                    } else {
                        hasInvalidChar = true;
                        morseCode.append(" [Karakter '").append(c).append("' tidak dikenali] ");
                    }
                }

                outputArea.setText(morseCode.toString().trim());

                if (hasInvalidChar) {
                    JOptionPane.showMessageDialog(frame, 
                        "Beberapa karakter tidak dapat diterjemahkan.", 
                        "Peringatan", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Action listener untuk tombol Keluar (Exit)
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Keluar dari aplikasi
            }
        });

        frame.setVisible(true);
    }
}
