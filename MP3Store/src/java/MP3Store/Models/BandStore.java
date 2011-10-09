package MP3Store.Models;

import java.io.Serializable;

/**
 * @author james
 */

public class BandStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer bandID;
    private String bandManager;
    private String bandName;
    private String bandDesc;
    private int bandGenre;

    public BandStore() {
    }

    public BandStore(Integer bandID) {
        this.bandID = bandID;
    }

    public BandStore(Integer bandID, String bandManager, String bandName, String bandDesc, int bandGenre) {
        this.bandID = bandID;
        this.bandManager = bandManager;
        this.bandName = bandName;
        this.bandDesc = bandDesc;
        this.bandGenre = bandGenre;
    }

    public Integer getBandID() {
        return bandID;
    }

    public void setBandID(Integer bandID) {
        this.bandID = bandID;
    }

    public String getBandManager() {
        return bandManager;
    }

    public void setBandManager(String bandManager) {
        this.bandManager = bandManager;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandDesc() {
        return bandDesc;
    }

    public void setBandDesc(String bandDesc) {
        this.bandDesc = bandDesc;
    }

    public int getBandGenre() {
        return bandGenre;
    }

    public void setBandGenre(int bandGenre) {
        this.bandGenre = bandGenre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bandID != null ? bandID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BandStore)) {
            return false;
        }
        BandStore other = (BandStore) object;
        if ((this.bandID == null && other.bandID != null) || (this.bandID != null && !this.bandID.equals(other.bandID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.Band[ bandID=" + bandID + " ]";
    }
    
}
