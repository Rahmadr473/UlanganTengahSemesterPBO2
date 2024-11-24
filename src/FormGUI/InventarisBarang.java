/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FormGUI;

import Koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventarisBarang extends javax.swing.JFrame {
    
    private ArrayList<String> itemList = new ArrayList<>();
    /**
     * Creates new form ResepMakanan
     */
    public InventarisBarang() {
        initComponents();
        updateItemList();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
    "Elektronik", 
    "Furniture", 
    "Peralatan Kantor", 
    "Alat Tulis", 
    "Lainnya" 
}));
       jButtonSimpan.setEnabled(true); // Default aktif
jButtonUbah.setEnabled(false);  // Default nonaktif

jList1.addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex != -1) {
            // Nonaktifkan tombol Simpan dan aktifkan Ubah
            jButtonSimpan.setEnabled(false);
            jButtonUbah.setEnabled(true);

            // Ambil item yang dipilih
            String selectedItem = itemList.get(selectedIndex);
            String[] parts = selectedItem.split(" - ");

            // Masukkan data ke form
            if (parts.length == 5) {
                jTextFieldNama.setText(parts[0]); // Nama
                jTextFieldJumlah.setText(parts[1]); // Jumlah
                
                // Tangani kategori
                String kategori = parts[2];
                if (((DefaultComboBoxModel<String>) jComboBox1.getModel()).getIndexOf(kategori) == -1) {
                    // Jika kategori tidak ada di model, tambahkan secara dinamis
                    jComboBox1.addItem(kategori);
                }
                jComboBox1.setSelectedItem(kategori); // Pilih kategori

                // Kondisi
                switch (parts[3]) {
                    case "Bagus":
                        jRadioButton1.setSelected(true);
                        break;
                    case "Cukup":
                        jRadioButton2.setSelected(true);
                        break;
                    case "Rusak":
                        jRadioButton3.setSelected(true);
                        break;
                }

                // Tanggal
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(parts[4]);
                    jDateChooser1.setDate(date);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Format tanggal tidak valid.");
                }
            }
        } else {
            // Reset tombol dan form jika tidak ada item yang dipilih
            jButtonSimpan.setEnabled(true);
            jButtonUbah.setEnabled(false);
            resetForm();
        }
    }
});


    }

    private void updateItemList() {
   itemList.clear(); // Kosongkan daftar sebelumnya
    try (Connection conn = Koneksi.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM barang")) {

        System.out.println("Status koneksi saat updateItemList: " + !conn.isClosed());
        var rs = pstmt.executeQuery();
        while (rs.next()) {
            // Format data untuk ditampilkan di JList
            String item = rs.getString("nama") + " - " +
                          rs.getInt("jumlah") + " - " +
                          rs.getString("kategori") + " - " +
                          rs.getString("kondisi") + " - " +
                          rs.getString("tanggal_masuk");
            itemList.add(item);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data dari database: " + e.getMessage());
        e.printStackTrace(); // Debugging
    }

    // Perbarui JList dengan data dari itemList
    DefaultListModel<String> model = new DefaultListModel<>();
    for (String item : itemList) {
        model.addElement(item);
    }
    jList1.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNama = new javax.swing.JTextField();
        jTextFieldJumlah = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldCari = new javax.swing.JTextField();
        jButtonCari = new javax.swing.JButton();
        jButtonImpor = new javax.swing.JButton();
        jButtonEkspor = new javax.swing.JButton();
        jButtonSimpan = new javax.swing.JButton();
        jButtonUbah = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aplikasi Inventaris Barang");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(240, 199, 160));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aplikasi Inventaris Barang", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 24), new java.awt.Color(67, 23, 47))); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel2.setBackground(new java.awt.Color(67, 23, 47));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12), new java.awt.Color(67, 23, 47))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 199, 160));
        jLabel1.setText("Nama ");

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 199, 160));
        jLabel2.setText("Jumlah");

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 199, 160));
        jLabel3.setText("Kategori");

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 199, 160));
        jLabel4.setText("Kondisi");

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 199, 160));
        jLabel5.setText("Tanggal Masuk");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel4.setBackground(new java.awt.Color(209, 234, 234));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jRadioButton2.setForeground(new java.awt.Color(39, 41, 42));
        jRadioButton2.setText("Cukup");

        jRadioButton3.setForeground(new java.awt.Color(39, 41, 42));
        jRadioButton3.setText("Rusak");

        jRadioButton1.setForeground(new java.awt.Color(39, 41, 42));
        jRadioButton1.setText("Bagus");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton1))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldJumlah, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldNama))
                .addGap(274, 274, 274))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(64, 102, 140));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonCari.setText("Cari");
        jButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCariActionPerformed(evt);
            }
        });

        jButtonImpor.setText("Impor Data");
        jButtonImpor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImporActionPerformed(evt);
            }
        });

        jButtonEkspor.setText("Ekspor Data");
        jButtonEkspor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEksporActionPerformed(evt);
            }
        });

        jButtonSimpan.setText("Simpan");
        jButtonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimpanActionPerformed(evt);
            }
        });

        jButtonUbah.setText("Ubah");
        jButtonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUbahActionPerformed(evt);
            }
        });

        jButtonHapus.setText("Hapus");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonImpor)
                .addGap(18, 18, 18)
                .addComponent(jButtonEkspor)
                .addGap(130, 130, 130)
                .addComponent(jButtonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCari)
                    .addComponent(jButtonImpor)
                    .addComponent(jButtonEkspor)
                    .addComponent(jButtonSimpan)
                    .addComponent(jButtonUbah)
                    .addComponent(jButtonHapus))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCariActionPerformed
        String keyword = jTextFieldCari.getText().toLowerCase();
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        for (String item : itemList) {
            if (item.toLowerCase().contains(keyword)) {
                filteredModel.addElement(item);
            }
        }
        jList1.setModel(filteredModel);
    }//GEN-LAST:event_jButtonCariActionPerformed

    private void jButtonImporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImporActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Abaikan header CSV
                if (line.startsWith("nama,jumlah,kategori,kondisi,tanggal_masuk")) continue;

                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String nama = parts[0];
                    int jumlah = Integer.parseInt(parts[1]);
                    String kategori = parts[2];
                    String kondisi = parts[3];
                    String tanggalMasuk = parts[4];

                    // Tambahkan ke database
                    simpanDataKeDatabase(nama, jumlah, kategori, kondisi, new SimpleDateFormat("yyyy-MM-dd").parse(tanggalMasuk));
                }
            }
            updateItemList(); // Perbarui JList
            JOptionPane.showMessageDialog(this, "Data berhasil diimpor!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengimpor data: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_jButtonImporActionPerformed

    private void jButtonEksporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEksporActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String item : itemList) {
                    bw.write(item);
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Data berhasil diekspor!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal mengekspor data.");
            }
        }
    }//GEN-LAST:event_jButtonEksporActionPerformed

    private void jButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimpanActionPerformed
    
        String nama = jTextFieldNama.getText();
        String jumlahText = jTextFieldJumlah.getText();
        String kategori = (String) jComboBox1.getSelectedItem();
        String kondisi = jRadioButton1.isSelected() ? "Bagus" : 
                     jRadioButton2.isSelected() ? "Cukup" : "Rusak";
        Date tanggalMasuk = jDateChooser1.getDate();

    if (nama.isEmpty() || jumlahText.isEmpty() || tanggalMasuk == null) {
        JOptionPane.showMessageDialog(this, "Data tidak lengkap!");
        return;
    }

    try {
        int jumlah = Integer.parseInt(jumlahText);
        simpanDataKeDatabase(nama, jumlah, kategori, kondisi, tanggalMasuk);

        // Perbarui JList
        updateItemList();

        // Reset form
        resetForm();

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
    }
    }//GEN-LAST:event_jButtonSimpanActionPerformed

    private void jButtonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUbahActionPerformed
      int selectedIndex = jList1.getSelectedIndex();
    if (selectedIndex != -1) {
        String namaLama = itemList.get(selectedIndex).split(" - ")[0]; // Nama lama berdasarkan data yang dipilih
        String namaBaru = jTextFieldNama.getText();
        String jumlahText = jTextFieldJumlah.getText();
        String kategori = (String) jComboBox1.getSelectedItem();
        String kondisi = jRadioButton1.isSelected() ? "Bagus" : 
                         jRadioButton2.isSelected() ? "Cukup" : "Rusak";
        Date tanggalMasuk = jDateChooser1.getDate();

        if (namaBaru.isEmpty() || jumlahText.isEmpty() || tanggalMasuk == null) {
            JOptionPane.showMessageDialog(this, "Data tidak lengkap!");
        } else {
            try {
                int jumlah = Integer.parseInt(jumlahText);

                // Ubah data di database
                ubahDataDiDatabase(namaLama, namaBaru, jumlah, kategori, kondisi, tanggalMasuk);

                // Perbarui JList
                updateItemList();

                // Reset form
                resetForm();

                // Set status tombol
                jButtonSimpan.setEnabled(true); // Aktifkan kembali Simpan
                jButtonUbah.setEnabled(false);  // Nonaktifkan Ubah

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan diubah!");
    }
    }//GEN-LAST:event_jButtonUbahActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
         int selectedIndex = jList1.getSelectedIndex();
    if (selectedIndex != -1) {
        // Ambil data yang dipilih
        String selectedItem = itemList.get(selectedIndex);

        // Ambil nama barang dari string item (misal format: "nama - jumlah - kategori - kondisi - tanggal")
        String nama = selectedItem.split(" - ")[0];

        // Hapus data dari database
        hapusDataDariDatabase(nama);

        // Hapus dari itemList dan perbarui JList
        itemList.remove(selectedIndex);
        updateItemList();
    } else {
        JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
    }
    }//GEN-LAST:event_jButtonHapusActionPerformed
    
    private void simpanDataKeDatabase(String nama, int jumlah, String kategori, String kondisi, Date tanggalMasuk) {
    String query = "INSERT INTO barang (nama, jumlah, kategori, kondisi, tanggal_masuk) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = Koneksi.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        // Format tanggal menjadi string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalFormatted = sdf.format(tanggalMasuk);

        // Set parameter untuk query
        pstmt.setString(1, nama);
        pstmt.setInt(2, jumlah);
        pstmt.setString(3, kategori);
        pstmt.setString(4, kondisi);
        pstmt.setString(5, tanggalFormatted);

        // Eksekusi query
        pstmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan ke database!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan data ke database: " + e.getMessage());
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventarisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventarisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventarisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventarisBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventarisBarang().setVisible(true);
            }
        });
    }

    private void resetForm() {
    jTextFieldNama.setText("");
    jTextFieldJumlah.setText("");
    jComboBox1.setSelectedIndex(0); // Reset ke pilihan pertama
    jDateChooser1.setDate(null);    // Reset tanggal
    jRadioButton1.setSelected(false);
    jRadioButton2.setSelected(false);
    jRadioButton3.setSelected(false);

    // Reset status tombol
    jButtonSimpan.setEnabled(true);
    jButtonUbah.setEnabled(false);
}

    private void hapusDataDariDatabase(String nama) {
    String query = "DELETE FROM barang WHERE nama = ?";
    try (Connection conn = Koneksi.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, nama);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus dari database!");
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan di database.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menghapus data dari database: " + e.getMessage());
    }
}
    private void ubahDataDiDatabase(String namaLama, String namaBaru, int jumlah, String kategori, String kondisi, Date tanggalMasuk) {
    String query = "UPDATE barang SET nama = ?, jumlah = ?, kategori = ?, kondisi = ?, tanggal_masuk = ? WHERE nama = ?";
    
    try (Connection conn = Koneksi.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        // Format tanggal menjadi string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalFormatted = sdf.format(tanggalMasuk);

        // Set parameter untuk query
        pstmt.setString(1, namaBaru);
        pstmt.setInt(2, jumlah);
        pstmt.setString(3, kategori);
        pstmt.setString(4, kondisi);
        pstmt.setString(5, tanggalFormatted);
        pstmt.setString(6, namaLama);

        // Eksekusi query
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil diubah di database!");
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan di database.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal mengubah data di database: " + e.getMessage());
    }
}

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCari;
    private javax.swing.JButton jButtonEkspor;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonImpor;
    private javax.swing.JButton jButtonSimpan;
    private javax.swing.JButton jButtonUbah;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCari;
    private javax.swing.JTextField jTextFieldJumlah;
    private javax.swing.JTextField jTextFieldNama;
    // End of variables declaration//GEN-END:variables
}
