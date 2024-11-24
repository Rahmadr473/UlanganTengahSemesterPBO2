
# Aplikasi Inventaris Barang

Aplikasi **Inventaris Barang** adalah aplikasi berbasis Java GUI yang dirancang untuk mencatat, mengelola, dan mencari data barang dalam sebuah inventaris. Aplikasi ini menggunakan konsep **CRUD** (Create, Read, Update, Delete) untuk memudahkan pengguna dalam pengelolaan data barang.

## Fitur Utama
1. **Tambah Data Barang**: Menambahkan data barang baru ke dalam daftar inventaris.
2. **Ubah Data Barang**: Mengubah informasi barang yang sudah ada di daftar.
3. **Hapus Data Barang**: Menghapus data barang dari daftar.
4. **Pencarian Data Barang**: Mencari data barang berdasarkan nama barang.
5. **Impor Data**: Mengimpor data barang dari file CSV.
6. **Ekspor Data**: Mengekspor data barang ke dalam file CSV.

## Spesifikasi Aplikasi
- **Framework**: Java Swing
- **Komponen GUI yang Digunakan**:
  - `JFrame`, `JPanel`, `JList`, `JTextField`, `JComboBox`, `JButton`, `JDateChooser`, dan `JRadioButton`.
- **Database**: MySQL
- **Library Tambahan**: 
  - `toedter.calendar` untuk pemilihan tanggal (`JDateChooser`).

## Cara Menggunakan
1. **Menjalankan Program**
   - Jalankan file `InventarisBarang.java` melalui IDE seperti NetBeans, Eclipse, atau IntelliJ IDEA.
2. **Tambah Data**
   - Isi semua input (Nama, Jumlah, Kategori, Kondisi, Tanggal Masuk) dan klik tombol **Simpan**.
3. **Ubah Data**
   - Pilih item di daftar, lalu ubah informasi pada form. Klik tombol **Ubah** untuk menyimpan perubahan.
4. **Hapus Data**
   - Pilih item di daftar, lalu klik tombol **Hapus** untuk menghapus data.
5. **Pencarian**
   - Masukkan kata kunci pada kolom **Cari**, lalu tekan tombol **Cari**.
6. **Impor Data**
   - Klik tombol **Impor Data**, pilih file CSV sesuai format yang telah ditentukan, lalu data akan dimasukkan ke dalam daftar.
7. **Ekspor Data**
   - Klik tombol **Ekspor Data** untuk menyimpan semua data barang dalam format CSV.

## Format CSV
Format file CSV untuk impor data adalah sebagai berikut:

```csv
nama,jumlah,kategori,kondisi,tanggal_masuk
Barang A,10,Elektronik,Bagus,2023-01-01
Barang B,5,Furniture,Cukup,2023-02-01
```

**Keterangan Kolom:**
- `nama`: Nama barang (String).
- `jumlah`: Jumlah barang (Integer).
- `kategori`: Kategori barang (String).
- `kondisi`: Kondisi barang (Bagus, Cukup, Rusak).
- `tanggal_masuk`: Tanggal masuk barang dalam format `YYYY-MM-DD`.

## Persiapan Database
1. Buat database MySQL dengan nama `inventaris`.
2. Jalankan query berikut untuk membuat tabel:

```sql
CREATE TABLE barang (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(255),
    jumlah INT,
    kategori VARCHAR(50),
    kondisi VARCHAR(20),
    tanggal_masuk DATE
);
```

## Informasi Pengembang
- **Nama**: Rahmad
- **NPM**: 2210010446


## Lisensi
Aplikasi ini dibuat untuk kebutuhan pembelajaran dan pengembangan pribadi. Anda bebas menggunakan dan memodifikasi kode ini untuk keperluan Anda sendiri.
```

Anda dapat menyimpan isi di atas ke dalam file `README.md`. Jangan lupa mengganti `[Tuliskan Nama Anda]` dan `[Tuliskan NPM Anda]` sesuai dengan informasi Anda.
