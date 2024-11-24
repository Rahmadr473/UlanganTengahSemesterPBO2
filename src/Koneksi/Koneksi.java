
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Koneksi {
    
    private static final String DATABASE_URL = "jdbc:sqlite:inventaris.db"; // Nama file database SQLite
    private static Connection connection;

    // Metode untuk mendapatkan koneksi
    public static Connection getConnection() {
    try {
        if (connection == null || connection.isClosed()) {
            // Inisialisasi ulang koneksi jika sudah ditutup
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Koneksi ke database berhasil!");
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.err.println("Gagal membuka koneksi: " + e.getMessage());
    }
    return connection;
}

    // Metode untuk menutup koneksi
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Koneksi ke database ditutup.");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database! " + e.getMessage());
            }
        }
    }

    // Metode untuk membuat tabel
    private static void createTable() {
        String createTableQuery = """
            CREATE TABLE IF NOT EXISTS barang (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nama TEXT NOT NULL,
                jumlah INTEGER NOT NULL,
                kategori TEXT NOT NULL,
                kondisi TEXT NOT NULL,
                tanggal_masuk DATE NOT NULL
            );
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableQuery);
            System.out.println("Tabel barang berhasil dibuat atau sudah ada.");
        } catch (SQLException e) {
            System.err.println("Gagal membuat tabel! " + e.getMessage());
        }
    }
}
