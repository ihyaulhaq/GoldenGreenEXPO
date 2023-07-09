package Unggah_Jadwal;

import java.time.LocalDate;

public class Data_jadwal {
    
    private LocalDate Tanggal;
    private int Waktu;
    private String Kegiatan;
    private String LokasiKegiatan;
    private String pembuat;
    
    public Data_jadwal(LocalDate tanggal, int waktu, String kegiatan, String lokasiKegiatan, String pembuat) {
        Tanggal = tanggal;
        Waktu = waktu;
        Kegiatan = kegiatan;
        LokasiKegiatan = lokasiKegiatan;
        this.pembuat=pembuat;
    }


    public String getPembuat() {
        return pembuat;
    }

    public void SetPembuat(String pembuat) {
        this.pembuat = pembuat;
    }

    public LocalDate getTanggal() {
        return Tanggal;
    }
    public void setTanggal(LocalDate tanggal) {
        Tanggal = tanggal;
    }
    public int getWaktu() {
        return Waktu;
    }
    public void setWaktu(int waktu) {
        Waktu = waktu;
    }
    public String getKegiatan() {
        return Kegiatan;
    }
    public void setKegiatan(String kegiatan) {
        Kegiatan = kegiatan;
    }
    public String getLokasiKegiatan() {
        return LokasiKegiatan;
    }
    public void setLokasiKegiatan(String lokasiKegiatan) {
        LokasiKegiatan = lokasiKegiatan;
    }
    


}

