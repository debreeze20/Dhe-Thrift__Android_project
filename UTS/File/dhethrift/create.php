<?php
    require("koneksi.php");

    $response = array();
    
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $nama = $_POST["nama"];
        $harga = $_POST["harga"];
        $kategori = $_POST["kategori"];
        $merk = $_POST["merk"];
        $stok = $_POST["stok"];
        $kondisi = $_POST["kondisi"];

        $perintah = "INSERT INTO tbl_thrift (`nama`, `harga`, `kategori`, `merk`, `stok`, `kondisi`) VALUES ('$nama','$harga','$kategori','$merk','$stok','$kondisi')";
        $eksekusi = mysqli_query($konek,$perintah);
        $cek = mysqli_affected_rows($konek);

        if ($cek > 0) {
            $response["kode"] = 1;
            $response["pesan"] = "Simpan Data Berhasil";
        } else {
            $response["kode"] = 0;
            $response["pesan"] = "Simpan Data Gagal";
        }
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Tidak Ada Post Data";
    }

    echo json_encode($response);
    mysqli_close($konek);