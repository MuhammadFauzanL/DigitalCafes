






# DigitalCafe - Proyek Materi 5 PAM

Aplikasi ini adalah submission untuk tugas mata kuliah Pemrograman Aplikasi Mobile (PAM) - **Materi 5: User Interaction**. Proyek ini membangun sebuah aplikasi Android fungsional dari awal (dari `Activity` kosong) untuk mensimulasikan alur pemesanan makanan di kafe, dengan fokus pada implementasi berbagai komponen interaksi pengguna.

## 📄 Ringkasan Proyek

**DigitalCafe** adalah aplikasi Android sederhana yang mensimulasikan alur pemesanan makanan. Aplikasi ini mencakup siklus hidup lengkap yang dihadapi pengguna, mulai dari layar selamat datang, autentikasi (registrasi dan login), pemilihan menu, penambahan item ke keranjang, pengisian alamat pengiriman, hingga mendapatkan layar konfirmasi pesanan.

Tujuan utama proyek ini adalah untuk mempraktikkan cara menangani berbagai *input controls* dan *user interactions* yang menjadi inti dari Materi 5, serta mengelola alur navigasi antar `Activity` yang logis dan efisien.

---

## ✨ Fitur Utama

Aplikasi ini memiliki beberapa fitur inti yang mencerminkan alur pengguna yang lengkap:

* **Autentikasi Pengguna:** Alur yang jelas memandu pengguna dari `WelcomeActivity` (layar splash) ke `AuthActivity` (pilihan register/login), dan kemudian ke `RegisterActivity` atau `LoginActivity`.
* **Sapaan Dinamis:** Aplikasi ini secara aktif mengambil data (nama pengguna) dari `RegisterActivity` atau `LoginActivity` dan meneruskannya ke `HomeActivity` untuk menampilkan sapaan yang dipersonalisasi (cth: "Halo Fauzan,").
* **Menu Statis 13-Item:** Menampilkan 13 item menu menggunakan `ScrollView` dan `LinearLayout` yang diisi secara statis. Setiap item menggunakan `ImageView` dan `TextView` (menggunakan metode "statis" sesuai permintaan).
* **Keranjang "Countable" (Countable Cart):** Ini adalah logika kustom. Setiap `ImageView` makanan di menu berfungsi sebagai tombol. Saat diklik, `setOnClickListener` akan mengambil **nama** item (dari `strings.xml`) dan menambahkannya ke sebuah `ArrayList` yang berfungsi sebagai keranjang belanja.
* **Ringkasan Pesanan (Screen 17):** Saat ikon "Order" diklik, aplikasi pindah ke `OrderSummaryActivity` baru. Layar ini menerima `ArrayList` (keranjang) dan `USER_NAME` dari `HomeActivity` dan menampilkannya dalam daftar yang rapi.
* **Formulir Pengiriman (Screen 18):** `DeliveryActivity` berisi formulir lengkap yang menggunakan berbagai *input controls* dari Materi 5, termasuk `EditText` (untuk nama dan alamat), `Spinner` (untuk pilihan kota), dan `RadioButton` (untuk metode pengiriman).
* **Konfirmasi Pesanan (Screen 19):** Setelah formulir pengiriman di-submit, aplikasi pindah ke `OrderCompleteActivity`. Ini adalah layar akhir yang berterima kasih kepada pengguna dan, saat tombol "Selesai" diklik, mengarahkan pengguna kembali ke `HomeActivity` dengan tumpukan (stack) navigasi yang sudah bersih.

---

## 🛠️ Konsep dan Teknologi yang Diimplementasikan

Aplikasi ini secara khusus mengimplementasikan konsep inti dari Materi 5 (User Interaction) dan praktik pengembangan Android dasar.

### 1. Navigasi Activity dan Pengiriman Data
Aplikasi ini sangat bergantung pada navigasi antar `Activity`.
* **`Intent` Sederhana:** Digunakan untuk berpindah antar layar (misalnya, `WelcomeActivity` ke `AuthActivity`).
* **`Intent` dengan Data (Extras):** Digunakan untuk meneruskan data penting antar layar.
    * **Contoh 1 (String):** Mengirim nama pengguna dari `RegisterActivity` ke `HomeActivity`.
        ```kotlin
        intent.putExtra("USER_NAME", nama)
        ```
    * **Contoh 2 (ArrayList):** Mengirim seluruh keranjang belanja dari `HomeActivity` ke `OrderSummaryActivity`.
        ```kotlin
        intent.putStringArrayListExtra("ORDER_LIST", orderList)
        ```
* **Membersihkan Stack Navigasi:** Saat pesanan selesai, `Intent` khusus digunakan untuk kembali ke `HomeActivity` sambil menghapus semua layar sebelumnya (Order, Delivery, Summary) dari tumpukan, sehingga pengguna tidak bisa menekan "kembali" ke formulir yang sudah diisi.
    ```kotlin
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    ```

### 2. Penanganan Interaksi Pengguna (User Interaction)
Ini adalah inti dari tugas.
* **`setOnClickListener`:** Digunakan secara ekstensif pada semua `Button` (Login, Register, Kirim, Selesai) dan, yang terpenting, pada semua 13 `ImageView` menu untuk mengaktifkan fungsi "countable cart".
* **`setOnItemSelectedListener`:** Digunakan pada `BottomNavigationView` untuk menangani navigasi utama (Home, Order, Profile).

### 3. Komponen Input (Input Controls)
Sesuai dengan Pokok Bahasan Materi 5, aplikasi ini mengimplementasikan:
* **`EditText`:** Digunakan di formulir Register (Nama, Username, Password) dan formulir Delivery (Nama, Alamat, Detail Alamat). Atribut `android:inputType` digunakan untuk keyboard yang sesuai (misal `textPassword`, `textPersonName`).
* **`RadioButton`:** Digunakan dalam `RadioGroup` di `DeliveryActivity` untuk memastikan pengguna hanya dapat memilih satu metode pengiriman (Same Day, Next Day, Pick Up).
* **`Spinner`:** Digunakan di `DeliveryActivity` untuk memilih kota. Daftar kota dimuat secara dinamis dari `string-array` di `strings.xml`.

### 4. Kustomisasi Tema dan Manajemen Sumber Daya
* **Kustomisasi Tema:** Tema aplikasi diubah dari ungu default menjadi **biru**. Ini dicapai dengan mendefinisikan palet warna biru (`blue_primary`, `blue_primary_dark`, `blue_accent`) di `res/values/colors.xml` dan menerapkannya sebagai `colorPrimary` dan `colorSecondary` di `res/values/themes.xml`.
* **Manajemen `strings.xml`:** Untuk memenuhi permintaan agar keranjang menampilkan "Nama" (bukan "Deskripsi"), `strings.xml` di-refaktor secara ekstensif.
    * **Pemisahan String:** Setiap item menu sekarang memiliki dua string (cth: `donut_name` dan `donut_desc`).
    * **Penggunaan:** `activity_home.xml` menampilkan `_desc` di menu, sementara `HomeActivity.kt` menambahkan `_name` ke keranjang.
    * **Lokalisasi:** 10 item menu tambahan diterjemahkan ke Bahasa Indonesia di `strings.xml`.

---

## 🗺️ Alur Aplikasi (User Flow)

1.  Pengguna membuka aplikasi dan disambut oleh `WelcomeActivity`.
2.  Pengguna menekan "Start Now" dan pindah ke `AuthActivity`.
3.  Pengguna memilih "Register", masuk ke `RegisterActivity`.
4.  Pengguna mengisi nama (misal: "Fauzan"), username, dan password, lalu menekan "Register".
5.  Aplikasi pindah ke `HomeActivity`. `HomeActivity` menerima nama dan menampilkan "Halo Fauzan,".
6.  Pengguna melihat 13 item menu. Pengguna mengklik gambar "Donuts" dan gambar "Kopi Hitam". Dua `Toast` muncul mengonfirmasi penambahan ke keranjang.
7.  Pengguna menekan ikon "Order" pada `BottomNavigationView`.
8.  Aplikasi pindah ke `OrderSummaryActivity` (Screen 17), yang kini menampilkan "Halo Fauzan," dan daftar pesanan:
    * Donuts
    * Kopi Hitam
9.  Pengguna menekan "Kirim". Aplikasi pindah ke `DeliveryActivity`.
10. Pengguna mengisi nama, alamat, detail alamat. Pengguna memilih "Kota Bandung" dari `Spinner` dan memilih "Same day" dari `RadioButton`.
11. Pengguna menekan "Order dan Kirim".
12. Aplikasi pindah ke `OrderCompleteActivity` (Screen 19), yang menampilkan "Halo Fauzan, Terima kasih sudah memesan...".
13. Pengguna menekan "Selesai". Aplikasi kembali ke `HomeActivity`, dan keranjang (`orderList`) sekarang kosong, siap untuk pesanan baru.

---

## 📱 Tangkapan Layar (Screenshots)

**Cara Menambahkan Gambar:**
1.  Ambil tangkapan layar dari setiap halaman di emulator atau HP Anda.
2.  Buka file `README.md` ini di editor GitHub (klik ikon pensil).
3.  Hapus teks placeholder (seperti `*[Upload screenshot...]`*)
4.  **Seret (drag) dan lepaskan (drop)** file gambar Anda ke area teks tersebut. GitHub akan otomatis meng-upload dan membuatkan link untuk Anda.

### Alur Autentikasi
| 1. Halaman Selamat Datang | 2. Halaman Pilihan Auth | 3. Halaman Register | 4. Halaman Login |
| :---: | :---: | :---: | :---: |
| *[Upload screenshot WelcomeActivity di sini]* | *[Upload screenshot AuthActivity di sini]* | *[Upload screenshot RegisterActivity di sini]* | *[Upload screenshot LoginActivity di sini]* |

### Alur Pemesanan
| 5. Halaman Menu (Home) | 6. Halaman Keranjang (Order Summary) | 7. Halaman Alamat (Delivery) | 8. Halaman Selesai (Confirm) |
| :---: | :---: | :---: | :---: |
| *[Upload screenshot HomeActivity (13 item) di sini]* | *[Upload screenshot OrderSummaryActivity di sini]* | *[Upload screenshot DeliveryActivity di sini]* | *[Upload screenshot OrderCompleteActivity di sini]* |

---

## 🧑‍💻 Dibuat Oleh

* **Nama:** [Nama Lengkap Anda]
* **NIM:** [NIM Anda]
* **Kelas:** [Kelas Anda]
