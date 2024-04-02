<?php
    require("koneksi.php");

    $response = array();
    
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $id = $_POST["id"];
    
        $perintah = "SELECT * FROM tbl_thrift WHERE id='$id'";
        $eksekusi = mysqli_query($konek,$perintah);
        $cek = mysqli_affected_rows($konek);

        if ($cek > 0) {
            $response["kode"] = 1;
            $response["pesan"] = "Data Tersedia";
            $response["data"]=array();

            while ($ambil = mysqli_fetch_object($eksekusi)) {
                $Ar["id"]=$ambil->id;
                $Ar["nama"]=$ambil->nama;
                $Ar["harga"]=$ambil->harga;
                $Ar["kategori"]=$ambil->kategori;
                $Ar["merk"]=$ambil->merk;
                $Ar["stok"]=$ambil->stok;
                $Ar["kondisi"]=$ambil->kondisi;

                array_push($response["data"],$Ar);
            }
        } else {
            $response["kode"] = 0;
            $response["pesan"] = "Data Tidak Tersedia";
        }
        
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Tidak Ada Post Data";
    }

    echo json_encode($response);
    mysqli_close($konek);