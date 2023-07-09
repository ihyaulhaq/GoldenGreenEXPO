package isi_daftar_relawan;

public class dataDR {
    private String Syarat;
    private String NoTelpon;
    private String pembuat;
    private String identifier;
    
    public dataDR(String noTelpon, String syarat,  String pembuat) {
        Syarat = syarat;
        NoTelpon = noTelpon;
        this.pembuat = pembuat;
        this.identifier = pembuat + "_" + noTelpon;
    }
    
    public String getSyarat() {
        return Syarat;
    }
    
    public void setSyarat(String syarat) {
        Syarat = syarat;
    }
    
    public String getNoTelpon() {
        return NoTelpon;
    }
    
    public void setNoTelpon(String noTelpon) {
        NoTelpon = noTelpon;
    }
    
    public String getPembuat() {
        return pembuat;
    }
    
    public void setPembuat(String pembuat) {
        this.pembuat = pembuat;
    }
    
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
   
    
}
