package OtherPackage;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;

class Mahasiswa implements Comparable<Mahasiswa> {
    String noPendaftaran;
    String programStudi;
    String namaLengkap;
    String jenisKelamin;
    String TanggalLahir;
    String agama;
    String alamat;
    String noTelepon;

    public Mahasiswa(String noPendaftaran, String programStudi, String namaLengkap, String jenisKelamin,
                     String TanggalLahir, String agama, String alamat, String noTelepon) {
        this.noPendaftaran = noPendaftaran;
        this.programStudi = programStudi;
        this.namaLengkap = namaLengkap;
        this.jenisKelamin = jenisKelamin;
        this.TanggalLahir = TanggalLahir;
        this.agama = agama;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
    }

    @Override
    public String toString() {
        return "No Pendaftaran : " + noPendaftaran + "\n" +
               "Program Studi  : " + programStudi + "\n" +
               "Nama Lengkap   : " + namaLengkap + "\n" +
               "Jenis Kelamin  : " + jenisKelamin + "\n" +
               "Tgl Lahir      : " + TanggalLahir + "\n" +
               "Agama          : " + agama + "\n" +
               "Alamat         : " + alamat + "\n" +
               "No Telepon     : " + noTelepon + "\n";
    }

    @Override
    public int compareTo(Mahasiswa other) {
        return this.namaLengkap.compareToIgnoreCase(other.namaLengkap);
    }
}

public class PendaftaranMahasiswa {
    static List<Mahasiswa> mahasiswaList = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplikasi Pendaftaran Mahasiswa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // Membuat panel utama dengan latar belakang gambar
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("back.png"); 
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        CustomButton btnTambah = new CustomButton("Tambah Data", "");
        CustomButton btnHapus = new CustomButton("Hapus Data", "");
        CustomButton btnUbah = new CustomButton("Ubah Data", "");
        CustomButton btnLihat = new CustomButton("Lihat Data", "");
        CustomButton btnUrutkan = new CustomButton("Urutkan Data", "");
        CustomButton btnCari = new CustomButton("Cari Data", "");
        CustomButton btnKeluar = new CustomButton("Keluar", "");

        btnTambah.addActionListener(e -> tambahData());
        btnHapus.addActionListener(e -> hapusData());
        btnUbah.addActionListener(e -> ubahData());
        btnLihat.addActionListener(e -> lihatData());
        btnUrutkan.addActionListener(e -> urutkanData());
        btnCari.addActionListener(e -> cariData());
        btnKeluar.addActionListener(e -> frame.dispose());

        panel.add(btnTambah);
        panel.add(btnHapus);
        panel.add(btnUbah);
        panel.add(btnLihat);
        panel.add(btnUrutkan);
        panel.add(btnCari);
        panel.add(btnKeluar);

        mainPanel.add(panel, BorderLayout.WEST);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    static void tambahData() {
        JFrame frame = new JFrame("Tambah Data Mahasiswa");
        frame.setSize(400, 500);
        frame.setLayout(new GridLayout(10, 2));
    
        JTextField txtNoPendaftaran = new JTextField();
        JTextField txtProgramStudi = new JTextField();
        JTextField txtNamaLengkap = new JTextField();
    
        // Radio buttons untuk jenis kelamin
        JRadioButton rbLakiLaki = new JRadioButton("Laki-Laki");
        JRadioButton rbPerempuan = new JRadioButton("Perempuan");
        ButtonGroup bgJenisKelamin = new ButtonGroup();
        bgJenisKelamin.add(rbLakiLaki);
        bgJenisKelamin.add(rbPerempuan);
        JPanel panelJenisKelamin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelJenisKelamin.add(rbLakiLaki);
        panelJenisKelamin.add(rbPerempuan);
    
        // Date picker untuk tanggal lahir menggunakan JCalendar
        JSpinner spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
    
        // Dropdown untuk agama
        String[] agamaOptions = {"Islam", "Kristen", "Budha", "Hindu", "Konghucu"};
        JComboBox<String> cmbAgama = new JComboBox<>(agamaOptions);
    
        JTextField txtAlamat = new JTextField();
        JTextField txtNoTelepon = new JTextField();
    
        JButton btnSubmit = new JButton("Simpan");
        btnSubmit.addActionListener(e -> {
            String noPendaftaran = txtNoPendaftaran.getText();
            String programStudi = txtProgramStudi.getText();
            String namaLengkap = txtNamaLengkap.getText();
            String jenisKelamin = rbLakiLaki.isSelected() ? "Laki-Laki" : rbPerempuan.isSelected() ? "Perempuan" : "";
            String TanggalLahir = new SimpleDateFormat("dd/MM/yyyy").format(spinnerTanggalLahir.getValue());
            String agama = (String) cmbAgama.getSelectedItem();
            String alamat = txtAlamat.getText();
            String noTelepon = txtNoTelepon.getText();
    
            Mahasiswa mahasiswa = new Mahasiswa(noPendaftaran, programStudi, namaLengkap, jenisKelamin,
                    TanggalLahir, agama, alamat, noTelepon);
            mahasiswaList.add(mahasiswa);
            JOptionPane.showMessageDialog(frame, "Data berhasil ditambahkan!");
            frame.dispose();
        });
    
        frame.add(new JLabel("No Pendaftaran:"));
        frame.add(txtNoPendaftaran);
        frame.add(new JLabel("Program Studi:"));
        frame.add(txtProgramStudi);
        frame.add(new JLabel("Nama Lengkap:"));
        frame.add(txtNamaLengkap);
        frame.add(new JLabel("Jenis Kelamin:"));
        frame.add(panelJenisKelamin);
        frame.add(new JLabel("Tanggal Lahir:"));
        frame.add(spinnerTanggalLahir);
        frame.add(new JLabel("Agama:"));
        frame.add(cmbAgama);
        frame.add(new JLabel("Alamat:"));
        frame.add(txtAlamat);
        frame.add(new JLabel("No Telepon:"));
        frame.add(txtNoTelepon);
        frame.add(btnSubmit);
    
        frame.setVisible(true);
    }
    

    static void hapusData() {
        String noPendaftaran = JOptionPane.showInputDialog(null, "Masukkan No Pendaftaran yang akan dihapus:");
        if (noPendaftaran == null) return; // Jika user klik Cancel, kembali ke menu

        boolean removed = mahasiswaList.removeIf(m -> m.noPendaftaran.equals(noPendaftaran));
        if (removed) {
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }
    }

    static void ubahData() {
        String noPendaftaran = JOptionPane.showInputDialog(null, "Masukkan No Pendaftaran yang akan diubah:");
        if (noPendaftaran == null) return; // Jika user klik Cancel, kembali ke menu

        Mahasiswa mahasiswa = null;
        for (Mahasiswa m : mahasiswaList) {
            if (m.noPendaftaran.equals(noPendaftaran)) {
                mahasiswa = m;
                break;
            }
        }

        if (mahasiswa != null) {
            boolean selesai = false;
            while (!selesai) {
                String[] options = {
                    "Program Studi", "Nama Lengkap", "Jenis Kelamin",
                    "Tempat/Tgl Lahir", "Agama", "Alamat", "No Telepon", "Selesai"
                };
                String option = (String) JOptionPane.showInputDialog(
                    null, "Pilih data yang ingin diubah:", "Ubah Data Mahasiswa",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]
                );

                if (option == null || option.equals("Selesai")) {
                    selesai = true;
                } else {
                    String newValue = JOptionPane.showInputDialog(null, "Masukkan data baru:");
                    switch (option) {
                        case "Program Studi": mahasiswa.programStudi = newValue; break;
                        case "Nama Lengkap": mahasiswa.namaLengkap = newValue; break;
                        case "Jenis Kelamin": mahasiswa.jenisKelamin = newValue; break;
                        case "Tempat/Tgl Lahir": mahasiswa.TanggalLahir = newValue; break;
                        case "Agama": mahasiswa.agama = newValue; break;
                        case "Alamat": mahasiswa.alamat = newValue; break;
                        case "No Telepon": mahasiswa.noTelepon = newValue; break;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Data berhasil diubah.");
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }
    }

    static void lihatData() {
        JFrame frame = new JFrame("Lihat Data Mahasiswa");
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        StringBuilder data = new StringBuilder("Data Mahasiswa:\n");
        for (Mahasiswa m : mahasiswaList) {
            data.append(m).append("---------------------------\n");
        }
        textArea.setText(data.toString());

        frame.setVisible(true);
    }

    static void urutkanData() {
        String[] options = {"Berdasarkan Nama Lengkap", "Berdasarkan No Pendaftaran"};
        String option = (String) JOptionPane.showInputDialog(
            null, "Pilih metode pengurutan:", "Urutkan Data Mahasiswa",
            JOptionPane.QUESTION_MESSAGE, null, options, options[0]
        );

        if (option != null) {
            if (option.equals("Berdasarkan Nama Lengkap")) {
                mahasiswaList.sort(Comparator.naturalOrder());
                JOptionPane.showMessageDialog(null, "Data berhasil diurutkan berdasarkan Nama Lengkap.");
            } else if (option.equals("Berdasarkan No Pendaftaran")) {
                mahasiswaList.sort(Comparator.comparing(m -> m.noPendaftaran));
                JOptionPane.showMessageDialog(null, "Data berhasil diurutkan berdasarkan No Pendaftaran.");
            }
        }
    }

    static void cariData() {
        String nama = JOptionPane.showInputDialog(null, "Masukkan nama yang ingin dicari:");
        if (nama == null || nama.trim().isEmpty()) { // Validasi jika input kosong
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            return;
        }

        StringBuilder results = new StringBuilder("Hasil pencarian:\n");
        for (Mahasiswa m : mahasiswaList) {
            if (m.namaLengkap.toLowerCase().contains(nama.toLowerCase())) {
                results.append(m).append("---------------------------\n");
            }
        }
        if (results.length() > 20) { // Jika ada hasil pencarian
            JOptionPane.showMessageDialog(null, results.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }
    }

    // CustomButton class to add background image
    static class CustomButton extends JButton {
        private final String imagePath;

        public CustomButton(String text, String imagePath) {
            super(text);
            this.imagePath = imagePath;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon icon = new ImageIcon(imagePath);
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}
