<?php
    require("koneksi.php");

    $response = array();
    
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $id = $_POST["id"];
        $nama = $_POST["nama"];
        $harga = $_POST["harga"];
        $kategori = $_POST["kategori"];
        $merk = $_POST["merk"];
        $stok = $_POST["stok"];
        $kondisi = $_POST["kondisi"];
    
        $perintah = "UPDATE tbl_thrift SET nama='$nama', harga='$harga', kategori='$kategori', merk='$merk', stok='$stok', kondisi='$kondisi' WHERE id='$id'";
        $eksekusi = mysqli_query($konek,$perintah);
        $cek = mysqli_affected_rows($konek);

        if ($cek > 0) {
            $response["kode"] = 1;
            $response["pesan"] = "Data Berhasil Diubah";
        } else {
            $response["kode"] = 0;
            $response["pesan"] = "Data Gagal Diubah";
        }
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Tidak Ada Post Data";
    }

    echo json_encode($response);
    mysqli_close($konek);