import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;



public class TugasBesar{

    public record Listacara(String id, String eventnama, String eventtanggal, String eventwaktu, String eventlokasi) implements Comparable<Listacara> {
        @Override //Anotasi @Override adalah alat yang berguna dalam pemrograman Java untuk meningkatkan keterbacaan, mencegah kesalahan, dan memastikan konsistensi dalam pengimplementasian metode.
        public int compareTo(Listacara other) {
            return this.id.compareTo(other.id);
            /*Metode ini digunakan untuk membandingkan objek Listacara berdasarkan id. Jika id dari objek ini lebih kecil,
            maka akan mengembalikan nilai negatif; jika lebih besar, akan mengembalikan nilai positif; dan jika sama, akan mengembalikan nol. Ini memungkinkan Anda untuk mengurutkan daftar acara berdasarkan id */
        }
        
        @Override
        public String toString() {
            return "id: " + id + ", Nama Event: " + eventnama + ", Tanggal Event: " + eventtanggal + ", Waktu Event: " + eventwaktu + ", Lokasi Event:" + eventlokasi;
        }
    }
    
    public  record Tamu (int id, String nama, String tipeKamar, int durasiMenginap, String nomorKamar, String telepon, String nik) {
        
    

        @Override
        public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Tipe Kamar: " + tipeKamar + ", Durasi Menginap: " + durasiMenginap +
                " hari, Nomor Kamar: " + nomorKamar + ", Telepon: " + telepon + ", NIK: " + nik;
            
        }
    }

    public record Pendapatan (int id, LocalDate tanggal, double jumlah, String jenisPendapatan, String deskripsi) {
    

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "ID: " + id + ", Tanggal: " + tanggal.format(formatter) + ", Jumlah: Rp " + jumlah +
               ", Jenis: " + jenisPendapatan + ", Deskripsi: " + deskripsi;
        }
    }

    public record PerformaPenjualanData (String namaEvent, String jenisPendapatan, String tanggalAcara, double hargaPendapatan) {
        
        

        @Override
        public String toString() { 
        return "Nama Event: " + namaEvent + ", Jenis Pendapatan: " + jenisPendapatan + ", Tanggal Acara: " + tanggalAcara + ", Harga Pendapatan: " + hargaPendapatan;
        }
    
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        

        int input;
        Scanner scanner = new Scanner(System.in);
        
        do{

           
            System.out.println("=====================================================================================================");
            System.out.println("========================............APLIKASI MANAGEMENT HOTEL...........=============================");
            System.out.println("=====================================================================================================");
            System.out.println("=              1. Tambahkan Data Tamu          |    3. Peforma Penjualan                            =");       
            System.out.println("=              2. Pendapatan                   |    4. Tambah Jadwal Acara                          =");
            System.out.println("=====================================================================================================");
            System.out.print("                                   Masukkan pilihan Anda:");
            input = scanner.nextInt();

            
            

            if (input == 1){

                menutamu(args);

            } else if (input == 2){
                HotelPendapatan.menupendapatan(args);
            } else if (input == 3){
                PerformaPenjualan.menuperforma(args);
            } else if (input == 4){
                Jadwalacara3.menujadwal(args);
            }else {
            System.out.println("data selesai");
            }

        } while  (input >= 1 && input <=3);
        scanner.close();
        


    }
    
    // Kelas untuk menyimpan data tamu
    static class Tamu1 {
        int id;
        String nama;
        String tipeKamar;
        int durasiMenginap;
        String nomorKamar;
        String telepon;
        String nik;
    }

        
    

    // Daftar tamu menggunakan ArrayList
    public static ArrayList<Tamu> daftarTamu = new ArrayList<>();
    public static int nextId = 1; // ID tamu otomatis bertambah

    // Fungsi Create (Menambahkan tamu baru)
    public static void tambahTamu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan nama tamu: ");
        String nama = scanner.nextLine();
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan tipe kamar (Single/Double/Suite): ");
        String tipeKamar = scanner.nextLine();
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan durasi menginap (dalam hari): ");
        int durasiMenginap = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan nomor kamar: ");
        String nomorKamar = scanner.nextLine();
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan nomor telepon: ");
        String telepon = scanner.nextLine();
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan NIK: ");
        String nik = scanner.nextLine();

        // Menambahkan tamu baru ke dalam daftar
        Tamu tamuBaru = new Tamu(nextId, nama, tipeKamar, durasiMenginap, nomorKamar, telepon, nik);
        daftarTamu.add(tamuBaru);
        nextId++;
        System.out.println("=====================================================================================================");
        System.out.println("Tamu berhasil ditambahkan!\n");
    }

    // Fungsi Read (Melihat daftar tamu)
    public static void lihatDaftarTamu() {
        if (daftarTamu.isEmpty()) {
            System.out.println("Belum ada tamu yang terdaftar.\n");
        } else {
            System.out.println("=====================================================================================================");
            System.out.println("Daftar Tamu:");
            for (Tamu tamu : daftarTamu) {
                System.out.println(tamu);
            }
            System.out.println();
        }
    }

    // Fungsi Update (Memperbarui data tamu)
    public static void perbaruiTamu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan ID tamu yang ingin diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer

        Tamu tamuDiperbarui = null;
        for (Tamu tamu : daftarTamu) {
            if (tamu.id == id) {
                tamuDiperbarui = tamu;
                break;
            }
        }

        if (tamuDiperbarui != null) {
            System.out.println("=====================================================================================================");
            System.out.print(    "Masukkan nama baru (sebelumnya: " + tamuDiperbarui.nama + "): ");
            String namaBaru = scanner.nextLine();
            System.out.println("=====================================================================================================");
            System.out.print(    "Masukkan tipe kamar baru (sebelumnya: " + tamuDiperbarui.tipeKamar + "): ");
            String tipeKamarBaru = scanner.nextLine();
            System.out.println("=====================================================================================================");
            System.out.print(    "Masukkan durasi menginap baru (sebelumnya: " + tamuDiperbarui.durasiMenginap + "): ");
            int durasiBaru = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer
            System.out.println("=====================================================================================================");
            System.out.print(    "Masukkan nomor kamar baru (sebelumnya: " + tamuDiperbarui.nomorKamar + "): ");
            String nomorKamarBaru = scanner.nextLine();
            System.out.println("=====================================================================================================");
            System.out.print(    "Masukkan nomor telepon baru (sebelumnya: " + tamuDiperbarui.telepon + "): ");
            String teleponBaru = scanner.nextLine();
            System.out.println("=====================================================================================================");
            System.out.print(    "Masukkan NIK baru (sebelumnya: " + tamuDiperbarui.nik + "): ");
            String nikBaru = scanner.nextLine();

            // Memperbarui data tamu
            
            Tamu input = new Tamu(id, namaBaru, tipeKamarBaru, durasiBaru, nomorKamarBaru, teleponBaru, nikBaru);
            daftarTamu.add(input);
            System.out.println("=====================================================================================================");
            System.out.println("Data tamu berhasil diperbarui!\n");
        } else {
            System.out.println("=====================================================================================================");
            System.out.println("ID tamu tidak ditemukan.\n");
        }
    }

    // Fungsi Delete (Menghapus tamu dari daftar)
    public static void hapusTamu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan ID tamu yang ingin dihapus: ");
        int id = scanner.nextInt();

        Tamu tamuDihapus = null;
        for (Tamu tamu : daftarTamu) {
            if (tamu.id == id) {
                tamuDihapus = tamu;
                break;
            }
        }

        if (tamuDihapus != null) {
            daftarTamu.remove(tamuDihapus);
            System.out.println("Tamu berhasil dihapus!\n");
        } else {
            System.out.println("ID tamu tidak ditemukan.\n");
        }
    
    }

    // Fungsi Cari Tamu Berdasarkan Nama
    public static void cariTamu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================================================================================================");
        System.out.print(  "Masukkan nama tamu yang ingin dicari: ");
        String namaDicari = scanner.nextLine();

        boolean ditemukan = false;
        for (Tamu tamu : daftarTamu) {
            if (tamu.nama.equalsIgnoreCase(namaDicari)) {
                System.out.println("=====================================================================================================");
                System.out.println(  "Tamu ditemukan: " + tamu);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tamu dengan nama \"" + namaDicari + "\" tidak ditemukan.\n");
        }
        
    }

    // Menu utama
    public static void menutamu(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            
            System.out.println("=====================================================================================================");
            System.out.println("=============================  Sistem Check-In-Check-Out Hotel ======================================");
            System.out.println("=====================================================================================================");
            System.out.println("=          1. Tambah Tamu (Check-In)          | 4. Hapus Tamu (Check-Out)                           =");       
            System.out.println("=          2. Lihat Daftar Tamu               | 5. Cari Tamu Berdasarkan Nama                       =");
            System.out.println("=          3. Perbarui Data Tamu              | 6. Keluar                                           =");
            System.out.println("=====================================================================================================");
            System.out.print("                                      Masukkan pilihan Anda:");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahTamu();
                    break;
                case 2:
                    lihatDaftarTamu();
                    break;
                case 3:
                    perbaruiTamu();
                    break;
                case 4:
                    hapusTamu();
                    break;
                case 5:
                    cariTamu();
                    break;
                case 6:
                    try {
                    saveDataToTextFile(daftarTamu);
                    System.out.println("=====================================================================================================");
                    System.out.println("================================Data berhasil disimpan ke file.======================================");
                    } catch (IOException e) {
                    System.out.println("=====================================================================================================");
                    System.out.println(  "..............................Gagal menyimpan data ke file: ........................................." + e.getMessage());
                    }
                    System.out.println("=====================================================================================================");
                    System.out.println("....................................Program selesai..................................................");
                    System.out.println("=====================================================================================================");
                    System.out.println(".................................Sistem selesai. Terima kasih!.......................................");
                    break;
                default:
                System.out.println(    "=====================================================================================================");
                    System.out.println("............................Pilihan tidak valid. Silakan coba lagi...................................\n");
            }
        } while (pilihan != 6);
        return;

        
    }

    private static void saveDataToTextFile(ArrayList<Tamu> ip) throws IOException{

        File fn = new File("data_tamu.txt");
        FileWriter fw = new FileWriter(fn,false);
        PrintWriter pw = new PrintWriter(fw);


        for(int i = 0; i < ip.size(); i++){
            Tamu br = ip.get(i);
            System.out.println("=====================================================================================================");
             pw.println(br.id+"||"+br.nama+"||"+br.tipeKamar+"||"+br.durasiMenginap+"||"+br.nomorKamar+"||"+br.telepon+"||"+br.nik);
             System.out.println("=====================================================================================================");
        }
        pw.flush();
        pw.close();

    }

    private static ArrayList<Tamu> readDataFromTxt() throws IOException{

        ArrayList<Tamu> ip = new ArrayList<Tamu>();
        File fn = new File ("data_tamu.txt");
        Scanner scanner = new Scanner(fn);

        if (!fn.exists()) {
            System.out.println("File data_tamu.txt tidak ditemukan. Membuat file baru...");
            
            return ip;
        }

        String data;

        while (scanner.hasNextLine()) {
            
            data = scanner.nextLine();
            String[] obj = data.split("\\|");
            Tamu dt = new Tamu(Integer.parseInt(obj[0]), obj[1], obj[2],Integer.parseInt(obj[3]), obj[4],obj[5], obj[6]);
            ip.add(dt);
        }
        scanner.close();
        return ip;
    }

    private static void display ( ArrayList<Tamu> dt_list){

 String format =             "%-5s%-20s%-30s%-20s%-20s%n";
        System.out.println("==================================================================================================================");
        System.out.printf(format, "Id", "Nama Tamu", "Tipe Kamar", "Durasi Menginap", "Nomor Kamar", "Telepon","NIK       ");
        System.out.println("==================================================================================================================");
        for(int i = 0; i < dt_list.size(); i++){
            System.out.printf(format,i+1,dt_list.get(i).id,dt_list.get(i).nama,dt_list.get(i).tipeKamar, dt_list.get(i).durasiMenginap, dt_list.get(i).nomorKamar, dt_list.get(i).telepon, dt_list.get(i).nik);
        }
        
    }




    static class Pendapatan1 {
    int id;
    LocalDate tanggal;
    double jumlah;
    String jenisPendapatan;
    String deskripsi;

    }

    static class HotelPendapatan {

    public static ArrayList<Pendapatan> pendapatanList = new ArrayList<>();
    

    public static void menupendapatan(String[] args) throws IOException, ClassNotFoundException{

        Scanner scanner = new Scanner(System.in);
        int pilihan;
    

    
        do {
            System.out.println("=====================================================================================================");
            System.out.println("=============================  Management Pendapatan Hotel ==========================================");
            System.out.println("=====================================================================================================");
            System.out.println("|          1. Tambah Pendapatan                 | 4. Hapus Pendapatan                                |");       
            System.out.println("|          2. Lihat Pendapatan                  | 5. Keluar                                          |");
            System.out.println("|          3. Update Pendapatan                 |                                                    |");
            System.out.println("=====================================================================================================");
            System.out.print("                                           Pilih opsi: ");
 ;
            pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    tambahPendapatan();
                    break;
                case 2:
                    lihatPendapatan();
                    break;
                case 3:
                    updatePendapatan();
                    break;
                case 4:
                    hapusPendapatan();
                    break;
                case 5:
                    try{
                        saveDataToText(pendapatanList);
                        System.out.println("Data berhasil disimpan ke file.");
                        } catch (IOException e) {
                        System.out.println("Gagal menyimpan ke file: " + e.getMessage());
                        }
                        System.out.println("Sistem berhasil disimpan.");
                        
                    System.out.println("Terima kasih telah menggunakan Manajemen Pendapatan Hotel!");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }while (pilihan != 5);
        return;
    }
    

    public static void tambahPendapatan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        LocalDate tanggal = inputTanggal();


        System.out.print("Jumlah: Rp ");
        double jumlah = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Jenis Pendapatan: ");
        System.out.println("1. Event");
        System.out.println("2. Pesta Ulang Tahun");
        System.out.println("3. Kamar");
        System.out.print("Pilih jenis pendapatan (1/2/3): ");
        int pilihanJenis = scanner.nextInt();
        scanner.nextLine();

        String jenisPendapatan = "";
        switch (pilihanJenis) {
            case 1:
                jenisPendapatan = "Event";
                break;
            case 2:
                jenisPendapatan = "Pesta Ulang Tahun";
                break;
            case 3:
                jenisPendapatan = "Penghasilan Kamar";
                break;
            default:
                System.out.println("Pilihan tidak valid. Menggunakan default: Event");
                jenisPendapatan = "Event";
        }

        System.out.print("Deskripsi: ");
        String deskripsi = scanner.nextLine();

        pendapatanList.add(new Pendapatan(id, tanggal, jumlah, jenisPendapatan, deskripsi));
        System.out.println("Pendapatan berhasil ditambahkan.");
    }

    public static void lihatPendapatan() {
        if (pendapatanList.isEmpty()) {
            System.out.println("Belum ada data pendapatan.");
            return;
        }

        Collections.sort(pendapatanList, (p1, p2) -> p1.tanggal.compareTo(p2.tanggal));

        System.out.println("== Data Pendapatan Hotel (Urut Berdasarkan Tanggal) ==");
        for (Pendapatan pendapatan : pendapatanList) {
            System.out.println(pendapatan);
        }
    }

    public static void updatePendapatan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID pendapatan yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Pendapatan pendapatan : pendapatanList) {
            if (pendapatan.id == id) {
                found = true;
                LocalDate tanggal = inputTanggal();

                System.out.print("Jumlah baru: Rp ");
                double jumlah = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Jenis Pendapatan: ");
                System.out.println("1. Event");
                System.out.println("2. Pesta Ulang Tahun");
                System.out.println("3. Penghasilan Kamar");
                System.out.print("Pilih jenis pendapatan (1/2/3): ");
                int pilihanJenis = scanner.nextInt();
                scanner.nextLine();

                String jenisPendapatan = "";
                switch (pilihanJenis) {
                    case 1:
                        jenisPendapatan = "Event";
                        break;
                    case 2:
                        jenisPendapatan = "Pesta Ulang Tahun";
                        break;
                    case 3:
                        jenisPendapatan = "Penghasilan Kamar";
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Menggunakan default: Event");
                        jenisPendapatan = "Event";
                }

                System.out.print("Deskripsi baru: ");
                String deskripsi = scanner.nextLine();
                Pendapatan input = new Pendapatan (id, tanggal, jumlah, jenisPendapatan, deskripsi);
                pendapatanList.add(input);
            
                System.out.println("Pendapatan berhasil diupdate.");
                break;
            }
        }

        if (!found) {
            System.out.println("Pendapatan dengan ID tersebut tidak ditemukan.");
        }
    }

    public static void hapusPendapatan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID pendapatan yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = pendapatanList.removeIf(p -> p.id == id);
        if (removed) {
            System.out.println("Pendapatan berhasil dihapus.");
        } else {
            System.out.println("Pendapatan dengan ID tersebut tidak ditemukan.");
        }
    }

    public static LocalDate inputTanggal() {
        Scanner scanner = new Scanner(System.in);
        LocalDate tanggal = null;
        while (tanggal == null) {
            System.out.print("Tanggal (format: dd-MM-yyyy): ");
            String tanggalInput = scanner.nextLine();
            try {
                tanggal = LocalDate.parse(tanggalInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal tidak valid, coba lagi.");
            }
        }
        return tanggal;
    }
    }

    private static void saveDataToText (ArrayList<Pendapatan> ip) throws IOException {

        File fn = new File("data_pendapatan.txt");
        FileWriter fw = new FileWriter(fn,false);
        PrintWriter pw = new PrintWriter(fw);

        for(int i = 0; i < ip.size(); i++){
            Pendapatan p = ip.get(i);
            pw.println(p.id + "|" + p.tanggal + "|" + p.jumlah + "|" + p.jenisPendapatan + "|" + p.deskripsi);
        }
        pw.flush();
        pw.close();
    }

    private static ArrayList<Pendapatan> readDataFromText() throws IOException {

        ArrayList<Pendapatan> ip = new ArrayList<>();
        File fn = new File("data_pendapatan.txt");
        Scanner scanner = new Scanner(fn);

        if (!fn.exists()){
            System.out.println("File data_pendapatan.txt tidak ditemukan. Membuat file baru...");

            return ip;

        }
        
        String data;

        while (scanner.hasNextLine()) {
            
            data = scanner.nextLine();
            String[] obj = data.split("\\|");
            Pendapatan dt = new Pendapatan(Integer.parseInt(obj[0]), LocalDate.parse(obj[1]), Double.parseDouble(obj[2]) ,  obj[3], obj[4]);
            ip.add(dt);
        }
        scanner.close();
        return ip;
    }
    
    private static void displayy (ArrayList<Pendapatan> dt_list) {

        String format = "%-5s%-20s%-30s%-20s%-20s%n";
        System.out.printf(format, "Id", "Tanggal", "Jumlah", "Jenis Pendapatan", "Deskripsi");

        System.out.println("======================================================================================================");
        for(int i = 0; i < dt_list.size(); i++){
            System.out.printf(format, i+1, dt_list.get(i).id, dt_list.get(i).tanggal, dt_list.get(i).jenisPendapatan, dt_list.get(i).deskripsi);
        }

    }




    static class PerformaPenjualan {
    
        private static ArrayList<PerformaPenjualanData> daftarPendapatan = new ArrayList<>();
        private static Scanner scanner = new Scanner(System.in);
    
        // Public Record (data class) untuk pendapatan
        public record PerformaPenjualanData(String namaEvent, String jenisPendapatan, String tanggalAcara, double hargaPendapatan) {
            @Override
            public String toString() {
                return "Nama Event: " + namaEvent + ", Jenis Pendapatan: " + jenisPendapatan + ", Tanggal Acara: " + tanggalAcara + ", Harga Pendapatan: " + hargaPendapatan;
            }
        }
    
        public static void tambahPendapatan() {
            System.out.print("Masukkan Nama Event: ");
            String namaEvent = scanner.nextLine();
            System.out.print("Masukkan Jenis Pendapatan: ");
            String jenisPendapatan = scanner.nextLine();
            System.out.print("Masukkan Tanggal Acara (DD-MM-YYYY): ");
            String tanggalAcara = scanner.nextLine();
            System.out.print("Masukkan Jumlah Pembayaran : ");
            double hargaPendapatan = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
    
            daftarPendapatan.add(new PerformaPenjualanData(namaEvent, jenisPendapatan, tanggalAcara, hargaPendapatan));
            System.out.println("Pendapatan berhasil ditambahkan!");
        }
    
        public static void tampilkanPendapatan() {
            if (daftarPendapatan.isEmpty()) {
                System.out.println("Tidak ada data pendapatan.");
            } else {
                System.out.println("\nDaftar Pendapatan:");
                for (int i = 0; i < daftarPendapatan.size(); i++) {
                    System.out.println(i + ". " + daftarPendapatan.get(i));
                }
            }
        }
    
        public static void updatePendapatan() {
            System.out.print("Masukkan index pendapatan yang ingin diupdate: ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            if (index >= 0 && index < daftarPendapatan.size()) {
                System.out.print("Masukkan Nama Event baru: ");
                String namaEvent = scanner.nextLine();
                System.out.print("Masukkan Jenis Pendapatan baru: ");
                String jenisPendapatan = scanner.nextLine();
                System.out.print("Masukkan Tanggal Acara baru (DD-MM-YYYY): ");
                String tanggalAcara = scanner.nextLine();
                System.out.print("Masukkan Harga Pendapatan baru: ");
                double hargaPendapatan = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
    
                daftarPendapatan.set(index, new PerformaPenjualanData(namaEvent, jenisPendapatan, tanggalAcara, hargaPendapatan));
                System.out.println("Pendapatan berhasil diperbarui!");
            } else {
                System.out.println("Index tidak valid.");
            }
        }
    
        public static void hapusPendapatan() {
            System.out.print("Masukkan index pendapatan yang ingin dihapus: ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            if (index >= 0 && index < daftarPendapatan.size()) {
                daftarPendapatan.remove(index);
                System.out.println("Pendapatan berhasil dihapus!");
            } else {
                System.out.println("Index tidak valid.");
            }
        }
    
        public static void hitungTotalPendapatanBulan() {
            System.out.print("Masukkan bulan (01-12): ");
            String bulan = scanner.nextLine();
            double totalPendapatan = 0;
    
            for (PerformaPenjualanData data : daftarPendapatan) {
                if (data.tanggalAcara().substring(3, 5).equals(bulan)) {
                    totalPendapatan += data.hargaPendapatan();
                }
            }
    
            System.out.println("Deskripsi Total pendapatan pada bulan " + bulan + " adalah: " + totalPendapatan);
        }
    
        public static void tampilkanLaporan() {
            if (daftarPendapatan.isEmpty()) {
                System.out.println("Tidak ada data untuk ditampilkan di laporan.");
            } else {
                System.out.println("\nLaporan Pendapatan:");
                double totalPendapatan = 0;
                for (PerformaPenjualanData data : daftarPendapatan) {
                    System.out.println(data);
                    totalPendapatan += data.hargaPendapatan();
                }
                System.out.println("\nDeskripsi Total Pendapatan Keseluruhan dalam setahun: " + totalPendapatan);
            }
        }
    
        public static void menuperforma(String[] args) {
            while (true) {
            System.out.println("=====================================================================================================");
            System.out.println("======================================= Peforma penjualan ===========================================");
            System.out.println("=====================================================================================================");
            System.out.println("=          1. Tambah Pendapatan               | 4. Hapus Pendapatan                                 =");       
            System.out.println("=          2. Tampilkan Pendapatan            | 5. Hitung Total Pendapatan Bulan Tertentu           =");
            System.out.println("=          3. Update Pendapatan               | 6. Tampilkan Laporan Pendapatan                     =");
            System.out.println("=                                             | 7. Keluar                                           =");
            System.out.println("=====================================================================================================");
            System.out.print("                                      Pilih opsi: ");
            
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Consume newline
    
                if (pilihan == 1) {
                    tambahPendapatan();
                } else if (pilihan == 2) {
                    tampilkanPendapatan();
                } else if (pilihan == 3) {
                    updatePendapatan();
                } else if (pilihan == 4) {
                    hapusPendapatan();
                } else if (pilihan == 5) {
                    hitungTotalPendapatanBulan();
                } else if (pilihan == 6) {
                    tampilkanLaporan();
                } else if (pilihan == 7) {
                    try {
                        saveDataToText(daftarPendapatan);
                        System.out.println("Data berhasil disimpan ke file.");
                    } catch (IOException e) {
                        System.out.println("Gagal menyimpan data ke file: " + e.getMessage());
                    }
                    System.out.println("Terima kasih! Program selesai.");
                    return;
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            }
        }
    
        private static void saveDataToText(ArrayList<PerformaPenjualanData> ip) throws IOException{
    
            File fn = new File("performa_penjualan_data.txt");
            FileWriter fw = new FileWriter(fn,true);
            PrintWriter pw = new PrintWriter(fw);
    
            for(int i = 0; i < ip.size(); i++){
                PerformaPenjualanData br = ip.get(i);
                pw.println(br.namaEvent+"|"+br.jenisPendapatan+"|"+br.tanggalAcara+"|"+br.hargaPendapatan);
    
            }
            pw.flush();
            pw.close();
            
    
        }
    
        private static ArrayList<PerformaPenjualanData> readDataFromTxt() throws IOException{
            ArrayList<PerformaPenjualanData> arr_dt = new ArrayList<>();
            File fn = new File("performa_penjualan_data.txt");
            Scanner scn = new Scanner(fn);
            
            String datatext;
    
            while(scn.hasNextLine()){
                datatext = scn.nextLine();
                String[] obj = datatext.split("\\|");
                PerformaPenjualanData dt = new PerformaPenjualanData(obj[0], obj[1], (obj[2]), Double.parseDouble(obj[3]));
                arr_dt.add(dt);
            }
            scn.close();
            
            return arr_dt;
            
        }
    
        private static void display(ArrayList<PerformaPenjualanData> dt_list) {
            /* ini adalah method yang digunakan untuk menampilkan daftar barang kepada pengguna,
            method ini akan mengambil daftar barang dan menunjukkan semuanya satu per satu.
             */
            String format = "| %-5s | %-12s | %-20s | %-5s | %-10s |%n";
            System.out.printf(format, "No", "Kode Barang", "Nama Barang", "Stok", "Harga");
            System.out.println("=========================================================================================");
        
            for (int i = 0; i < dt_list.size(); i++) {
                System.out.printf(format, i + 1, dt_list.get(i).namaEvent, dt_list.get(i).jenisPendapatan, dt_list.get(i).tanggalAcara, dt_list.get(i).hargaPendapatan);
            }
        }
    
    
    }
    
    
    

    static class Jadwalacara3  {
    

    public static <T extends Comparable<T>> void insertionSort(ArrayList<T> inventoryacara) {
        /*Dengan menggunakan T extends Comparable<T>, Anda memastikan bahwa objek yang Anda masukkan ke dalam metode insertionSort
        dapat dibandingkan satu sama lain, sehingga Anda dapat mengurutkannya. */

        int a = inventoryacara.size();
        for (int i = 1; i < a; i++) {
            T sort = inventoryacara.get(i);
            int j = i - 1;

            while (j >= 0 && inventoryacara.get(j).compareTo(sort) > 0) {
                inventoryacara.set(j + 1, inventoryacara.get(j));
                j--;
                inventoryacara.set(j +1, sort);
            }
        }
    }

    public static void menujadwal (String[] args) throws IOException , ClassNotFoundException{
        
        Scanner scn = new Scanner(System.in);
        ArrayList<Listacara> inventoryacara = new ArrayList<>();
        int input, i;
        int indexremove;
        Listacara inputacara;
        Listacara cariacara;

        String ID;
        String NAMA_ACARA;
        String TANGGAL;
        String WAKTU;
        String LOKASI;

        Listacara list1 = new Listacara("0103","Meeting", "20-10-2025","10:00","Garuda Ballroom");
        inventoryacara.add(list1);
        
        Listacara list2 = new Listacara("0107","Ultah", "27-10-2025","10:00","Rajawali Ballroom");
        inventoryacara.add(list2);
        
        Listacara list3 = new Listacara("0105","Wedding", "11-10-2025","10:00","Merak Ballroom");
        inventoryacara.add(list3);
        
      

        do {
            
            System.out.println(" ");
            System.out.println(" ==============================================          |             =========================================================");
            System.out.println("             Aplikasi Manajemen Hotel                    |                                     Menu Event                       ");
            System.out.println(" ==============================================          |             =========================================================");
            System.out.println("                                                         |                                                                      ");
            System.out.println(" 1. Tambah Data tamu                                     |             1. input event");
            System.out.println(" 2. Pendapatan                                           |             2. hapus acara berdasarkan kode ID");
            System.out.println(" 3. Performa penjualan                                   |             3. tampilkan jadwal acara");
            System.out.println(" 4. tambah jadwal acara   <===                           |             4. hapus Event berdasarkan nama acara");
            System.out.println("                                                         |             5. untuk update Event berdsarkan nama Event");
            System.out.println("  ==============================================         |             6. urutkan Event berdasarkan kode ID");
            System.out.println("                                                         |             0. program selesai ");
            System.out.println("                                                         |                                                                      ");
            System.out.println("                                                         |                                    Rengga_202415022                  ");
            System.out.println("                                                         |                                                                      ");
            System.out.print("                                                         |             Pilihan anda : ");
            input = scn.nextInt();
            scn.nextLine();
            System.out.println(" ");
            System.out.println(" ");

            
            
            
            
          
            if(input == 1){
                System.out.println("                                                         |             ================== menu input acara baru ================");
                System.out.println(" ");
                System.out.print("                                                         |             masukan kode ID       : ");
                ID = scn.nextLine();
    
                
                System.out.print("                                                         |             masukan nama acara    : ");
                NAMA_ACARA = scn.nextLine();
    
                System.out.print("                                                         |             masukan tanggal acara : ");
                TANGGAL = scn.nextLine();
                System.out.print("                                                         |             masukan waktu acara   : ");
                WAKTU= scn.nextLine();
                System.out.print("                                                         |             masukan lokasi acara  : ");
                LOKASI = scn.nextLine();
                
                inputacara = new Listacara(ID, NAMA_ACARA, TANGGAL, WAKTU, LOKASI);
                inventoryacara.add(inputacara);
                System.out.println(" ");
                System.out.println(" ");

                

    
            }else if(input == 2){
                System.out.println("                                                         |             ===================== menu hapus acara ==================");
                System.out.println(" ");
                System.out.print("                                                         |             Masukan kode ID yang ingin di hapus : ");
                ID = scn.nextLine();
                indexremove = -1;
                i = 0; 
                while (indexremove < 0 && i < inventoryacara.size()  ){
                    cariacara = inventoryacara.get(i);
                    if(cariacara.id.equals(ID)){
                        indexremove = i;
                    }
                    i++;
                }
                
                if(indexremove < 0){
                    System.out.println("                                                   |             Kode yang ingin dihapus tidak ada");
                }
                else{
                    inventoryacara.remove(indexremove);
                    System.out.println("                                                         |             data berhasil dihapus");
                }

                System.out.println(" ");
                System.out.println(" ");


            }else if(input == 3){
                System.out.println("======================================================= data acara ======================================================================\n ");
                display(inventoryacara);
                System.out.println(" ");
                System.out.println(" ");

                
                
            }else if (input == 4) {
                System.out.println("                                                         |              ===================== menu hapus acara ==================");
                System.out.print("                                                         |              Masukan nama acara yang ingin di hapus : ");
                NAMA_ACARA = scn.nextLine();
                indexremove = -1;
                i = 0; 
                while (indexremove < 0 && i < inventoryacara.size()  ){
                    cariacara = inventoryacara.get(i);
                    if(cariacara.eventnama.equals(NAMA_ACARA)){
                        indexremove = i;
                    }
                    i++;
                }
                
                if(indexremove < 0){
                    System.out.println("                                                      |              Acara yang ingin dihapus tidak ada");
                }
                else{
                    inventoryacara.remove(indexremove);
                    System.out.println("                                                         |              data Acara berhasil dihapus");
                }
                System.out.println(" ");
                System.out.println(" ");


            }else if (input == 5) {
                System.out.print("                                                         |              Masukkan nama Acara yang ingin di-update : ");
                NAMA_ACARA = scn.nextLine();
                indexremove = -1;
                i = 0;
                while (indexremove < 0 && i < inventoryacara.size()) {
                    cariacara = inventoryacara.get(i);
                    if (cariacara.eventnama.equals(NAMA_ACARA)) {
                        System.out.print("                                                         |              Masukkan nama Acara baru : " );
                        String namaacarabaru = scn.nextLine();
                        cariacara = new Listacara(cariacara.id, namaacarabaru, cariacara.eventtanggal, cariacara.eventwaktu, cariacara.eventlokasi);
                        inventoryacara.set(i, cariacara);
                        indexremove = i;
                    }
                    i++;
                }
                    if (indexremove < 0) {
                    System.out.println("                                                         |              Nama acara tidak ditemukan");
                    } else {
                    System.out.println("                                                         |              Data berhasil diupdate");
                    
                    
                }
                System.out.println(" ");
                System.out.println(" ");


                } else if (input == 6) {
                    System.out.println(" ============================ Urutkan acara Berdasarkan kode ID ============================");
                    System.out.println(" ");
                    System.out.println(" ");
                    
                    System.out.println("data acara sebelum pengurutan ");
                    System.out.println(" ");
                    
                    for(i = 0; i < inventoryacara.size(); i++ ){
                        cariacara = inventoryacara.get(i);
                        System.out.println(cariacara);
                        
                    }

                    System.out.println(" ");
                    System.out.println(" ");
                    insertionSort(inventoryacara);
                    display(inventoryacara);
                    System.out.println(" ");
                    System.out.println(" ");

                    System.out.println("\nSetelah pengurutan"); 
                    System.out.println(" ");   
                    for (Listacara Listacara : inventoryacara) {
                        System.out.println(Listacara);
                    } 
                    System.out.println(" ");
                    System.out.println(" ");


                    
                } else if (input == 0) {
                        try {
                            saveDataToText(inventoryacara);
                            System.out.println("                                                        |              Data berhasil disimpan ke file.");
                        } catch (IOException e) {
                            System.out.println("                                                     |              Gagal menyimpan data ke file: " + e.getMessage());
                        }
                        System.out.println("                                                        |              Program selesai");

                }else{
                System.out.println("                                                     |              Pilihan yang dimasukkan kurang tepat ");
                
            } 


        } while (input >= 1 && input <= 6);
        
       
    }

    private static void saveDataToText(ArrayList<Listacara> ip) throws IOException{
    /* ini adalah method yang digunakan untuk menyimpan daftar barang barang ke dalam file.
     */

        File fn = new File("data_Jadwalacara.txt");
        FileWriter fw = new FileWriter(fn,false);
        PrintWriter pw = new PrintWriter(fw);

        for(int i = 0; i < ip.size(); i++){
            Listacara br = ip.get(i);
            pw.println(br.id+"|"+br.eventnama+"|"+br.eventtanggal+"|"+br.eventwaktu+"|"+br.eventlokasi);
        }
        pw.flush();
        pw.close();
    }

    private static ArrayList<Listacara> readDataFromTxt() throws IOException{
    /* ini adalah method yang digunakan untuk mengambil barang barang
     * yang sudah kita simpan sebelumnya dari file.
     */ 
        ArrayList<Listacara> ip = new ArrayList<>();
        File fn = new File("data_Jadwalacara.txt");
        Scanner scn = new Scanner(fn);

        if (!fn.exists()) {
            System.out.println("File data_Jadwalacara.txt tidak ditemukan, Memulai dengan data kosong.");

            return ip;   
        }
                
        String data;

        while (scn.hasNextLine()) {
            
            data = scn.nextLine();
            String[] obj = data.split("\\|");
            Listacara dt = new Listacara(obj[0], obj[1], (obj[2]),(obj[3]), (obj[4]));
            ip.add(dt);
            
        }
        scn.close();
        
        return ip;    
    }

    
    private static void display( ArrayList<Listacara> dt_list){
    /* ini adalah method yang digunakan untuk menampilkan daftar barang kepada pengguna,
    method ini akan mengambil daftar barang dan menunjukkan semuanya satu per satu.
     */
        String format = "%-5s%-20s%-30s%-20s%-20s%-20s%n";
        System.out.printf(format, "No", "Id", "Nama Acara", "Tanggal Acara", "Waktu acara", "Lokasi acara");
       
        System.out.println("=========================================================================================================================================");
        for(int i = 0; i < dt_list.size(); i++){
            System.out.printf(format,i+1,dt_list.get(i).id,dt_list.get(i).eventnama,dt_list.get(i).eventtanggal, dt_list.get(i).eventwaktu, dt_list.get(i).eventlokasi);
        }
        System.out.println("=========================================================================================================================================");

    }
    }
}