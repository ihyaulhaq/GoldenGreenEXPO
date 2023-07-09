package unggah_artikelPage;


public class data_artikel {
    private String judul;
    private String artikel;
    private String pembuat;
    private boolean klik;
    
    public data_artikel(String judul, String artikel, String pembuat, boolean klik) {
        this.judul = judul;
        this.artikel = artikel;
        this.pembuat = pembuat;
        this.klik=klik;
    }

    public String getJudul() {
        return judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public boolean getklik() {
        return klik;
    }
    
    public void setJudul(boolean klik) {
        this.klik = klik;
    }
    
    public String getArtikel() {
        return artikel;
    }
    
    public void setArtikel(String artikel) {
        this.artikel = artikel;
    }
    
    public String getPembuat() {
       return pembuat;
    }
    
    public void setPembuat(String pembuat) {
        this.pembuat = pembuat;
    }

    public void setKlik(boolean klik) {
        this.klik=klik;
    }
    
    
}


