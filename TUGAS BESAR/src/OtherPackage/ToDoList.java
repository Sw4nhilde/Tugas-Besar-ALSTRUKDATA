package OtherPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

class Tugas implements Comparable<Tugas> {
    String deskripsi;
    String mataKuliah;
    LocalDateTime deadline;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Tugas(String deskripsi, String mataKuliah, String deadline) {
        this.deskripsi = deskripsi;
        this.mataKuliah = mataKuliah;
        this.deadline = LocalDateTime.parse(deadline, formatter); 
    }

    @Override
    public int compareTo(Tugas tugasLain) {
        return this.deadline.compareTo(tugasLain.deadline);
    }

    @Override
    public String toString() {
        return mataKuliah + " (" + deskripsi + ", Deadline: " + deadline.format(formatter) + ")";
    }
}

public class ToDoList extends JFrame {
    private PriorityQueue<Tugas> antreanTugas = new PriorityQueue<>();
    private HashMap<String, List<Tugas>> petaMataKuliah = new HashMap<>();
    private DefaultListModel<String> modelDaftarTugas = new DefaultListModel<>();
    private JList<String> daftarTugas = new JList<>(modelDaftarTugas);
    private JTextField fieldTugas, fieldMataKuliah, fieldDeadline;

    public ToDoList() {
        setTitle("Aplikasi To-Do List dengan Deadline");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelInput = new JPanel(new GridLayout(4, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Tambah Tugas Baru"));

        panelInput.add(new JLabel("Mata Kuliah:"));
        fieldMataKuliah = new JTextField();
        panelInput.add(fieldMataKuliah);

        panelInput.add(new JLabel("Deskripsi Tugas:"));
        fieldTugas = new JTextField();
        panelInput.add(fieldTugas);

        panelInput.add(new JLabel("Deadline (yyyy-MM-dd HH:mm):"));
        fieldDeadline = new JTextField();
        panelInput.add(fieldDeadline);

        JButton tombolTambah = new JButton("Tambah Tugas");
        panelInput.add(tombolTambah);

        JButton tombolSelesai = new JButton("Tandai Selesai");
        panelInput.add(tombolSelesai);

        add(panelInput, BorderLayout.NORTH);

        JPanel panelDaftar = new JPanel(new BorderLayout());
        panelDaftar.setBorder(BorderFactory.createTitledBorder("Daftar Tugas Berdasarkan Deadline"));
        panelDaftar.add(new JScrollPane(daftarTugas), BorderLayout.CENTER);
        add(panelDaftar, BorderLayout.CENTER);

        JPanel panelCari = new JPanel(new BorderLayout());
        panelCari.setBorder(BorderFactory.createTitledBorder("Cari Tugas Berdasarkan Mata Kuliah"));
        JTextField fieldCari = new JTextField();
        JButton tombolCari = new JButton("Cari");
        panelCari.add(fieldCari, BorderLayout.CENTER);
        panelCari.add(tombolCari, BorderLayout.EAST);
        add(panelCari, BorderLayout.SOUTH);

        // Tambahkan tombol Exit
        JButton tombolExit = new JButton("Keluar");
        JPanel panelExit = new JPanel();
        panelExit.add(tombolExit);
        add(panelExit, BorderLayout.SOUTH);

        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String deskripsi = fieldTugas.getText();
                    String mataKuliah = fieldMataKuliah.getText();
                    String deadlineInput = fieldDeadline.getText();

                    if (deskripsi.isEmpty() || mataKuliah.isEmpty() || deadlineInput.isEmpty()) {
                        JOptionPane.showMessageDialog(ToDoList.this, "Input tidak valid. Periksa kembali!");
                        return;
                    }

                    Tugas tugasBaru = new Tugas(deskripsi, mataKuliah, deadlineInput);
                    antreanTugas.add(tugasBaru);

                    petaMataKuliah.putIfAbsent(mataKuliah, new ArrayList<>());
                    petaMataKuliah.get(mataKuliah).add(tugasBaru);

                    perbaruiDaftarTugas();
                    bersihkanField();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ToDoList.this, "Format deadline salah! Gunakan format: yyyy-MM-dd HH:mm");
                }
            }
        });

        tombolSelesai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (antreanTugas.isEmpty()) {
                    JOptionPane.showMessageDialog(ToDoList.this, "Tidak ada tugas untuk diselesaikan.");
                    return;
                }

                Tugas tugasSelesai = antreanTugas.poll();
                petaMataKuliah.get(tugasSelesai.mataKuliah).remove(tugasSelesai);

                JOptionPane.showMessageDialog(ToDoList.this, "Tugas Selesai:\n" + tugasSelesai);
                perbaruiDaftarTugas();
            }
        });

        tombolCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mataKuliah = fieldCari.getText();
                if (!petaMataKuliah.containsKey(mataKuliah) || petaMataKuliah.get(mataKuliah).isEmpty()) {
                    JOptionPane.showMessageDialog(ToDoList.this, "Tidak ada tugas dalam mata kuliah \"" + mataKuliah + "\".");
                    return;
                }

                modelDaftarTugas.clear();
                for (Tugas tugas : petaMataKuliah.get(mataKuliah)) {
                    modelDaftarTugas.addElement(tugas.toString());
                }
            }
        });

        // Action listener untuk tombol Keluar
        tombolExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose the current frame
            }
        });
    }

    private void perbaruiDaftarTugas() {
        modelDaftarTugas.clear();
        for (Tugas tugas : antreanTugas) {
            modelDaftarTugas.addElement(tugas.toString());
        }
    }

    private void bersihkanField() {
        fieldTugas.setText("");
        fieldMataKuliah.setText("");
        fieldDeadline.setText("");
    }

    public static void TDL(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToDoList app = new ToDoList();
                app.setVisible(true);
            }
        });
    }
}
