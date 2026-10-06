# Monitoring Gempa BMKG

Aplikasi Android untuk melihat, mencari, dan melihat detail informasi gempa
terkini di Indonesia. Data diambil dari API publik BMKG.

## Screenshot

| Home Screen                    | Detail Screen                      |
|--------------------------------|------------------------------------|
| ![Home](screenshots/home.jpeg) | ![Detail](screenshots/detail.jpeg) |

## Fitur

- Daftar gempa terkini memakai LazyColumn (Tanggal, Magnitudo, Wilayah)
- Pencarian lokal berdasarkan Wilayah, tanpa panggilan API tambahan
- Loading state, error state dengan tombol Coba Lagi, dan tampilan hasil kosong
- Halaman detail: Tanggal, Jam, Coordinates, Magnitudo, Kedalaman, Wilayah, Potensi
- Warna badge magnitudo berbeda untuk gempa >= 5.0
- Tema Material 3 (Light/Dark) dengan Color, Typography, dan Theme yang dimodifikasi

## Arsitektur

Aplikasi memakai pola **MVVM**:

View (Composable) -> ViewModel -> Repository -> Retrofit API Service -> BMKG API

- **View**: HomeScreen, DetailScreen, dan komponen reusable (GempaItem, GempaSearchBar, DetailRow, LoadingView, ErrorView)
- **ViewModel**: GempaViewModel menyimpan state lewat StateFlow
- **UiState**: sealed interface Loading / Success / Error
- **Repository**: GempaRepository menjembatani ViewModel dan API
- **Data layer**: BmkgApiService dan RetrofitInstance (Retrofit + Gson)

Struktur folder:

app/src/main/java/com/pemmob/responsipemmob/
- data/model, data/remote, data/repository
- ui/state, ui/viewmodel, ui/components, ui/screen, ui/theme
- navigation
- MainActivity.kt

## API

- Base URL: `https://data.bmkg.go.id`
- Endpoint: `/DataMKG/TEWS/gempaterkini.json`
- Struktur respons: `Infogempa.gempa[]`
- Tanpa API key

## Detail Teknis

- Bahasa: Kotlin (data class, null safety, lambda, extension function)
- UI: Jetpack Compose, Material 3, Scaffold + TopAppBar, LazyColumn
- Networking: Retrofit + converter-gson
- Navigasi: navigation-compose (2 screen: Home dan Detail)
- State: StateFlow + collectAsState
- Min SDK: 24
- Library tambahan: hanya retrofit, converter-gson, navigation-compose, lifecycle-viewmodel-compose
- Tanpa library gambar (Coil/Glide)

## Cara Menjalankan

1. Clone repository ini
2. Buka di Android Studio
3. Sync Gradle (butuh koneksi internet saat pertama kali)
4. Jalankan di emulator atau perangkat

## APK

File APK debug tersedia di bagian Releases atau folder `apk/`.