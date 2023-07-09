package unggah_laporkegiatan;

public class Data_LK {
    private String judul_LK;
    private String isi_LK;
    private String pembuat_LK;
    private String identifier;
    
    public Data_LK(String judul_LK, String isi_LK, String pembuat_LK) {
        this.judul_LK = judul_LK;
        this.isi_LK = isi_LK;
        this.pembuat_LK = pembuat_LK;
        this.identifier = judul_LK+"_"+pembuat_LK;
    }

    public String getJudul_LK() {
        return judul_LK;
    }

    public void setJudul_LK(String judul_LK) {
        this.judul_LK = judul_LK;
    }

    public String getIsi_LK() {
        return isi_LK;
    }

    public void setIsi_LK(String isi_LK) {
        this.isi_LK = isi_LK;
    }

    public String getPembuat_LK() {
        return pembuat_LK;
    }

    public void setPembuat_LK(String pembuat_LK) {
        this.pembuat_LK = pembuat_LK;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
