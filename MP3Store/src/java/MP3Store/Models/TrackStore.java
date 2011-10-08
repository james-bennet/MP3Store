package MP3Store.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author james
 */

public class TrackStore implements Serializable {
    private Date uploadedOn;
    private static final long serialVersionUID = 1L;
    private Integer trackID;
    private int trackNumber;
    private int albumID;
    private String trackName;
    private String filePath;
    private BigDecimal price;

    public TrackStore() {
    }

    public TrackStore(Integer trackID) {
        this.trackID = trackID;
    }

    public TrackStore(Integer trackID, int trackNumber, int albumID, String trackName, String filePath, BigDecimal price, Date uploadedOn) {
        this.trackID = trackID;
        this.trackNumber = trackNumber;
        this.albumID = albumID;
        this.trackName = trackName;
        this.filePath = filePath;
        this.price = price;
        this.uploadedOn = uploadedOn;
    }

    public Integer getTrackID() {
        return trackID;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trackID != null ? trackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TrackStore)) {
            return false;
        }
        TrackStore other = (TrackStore) object;
        if ((this.trackID == null && other.trackID != null) || (this.trackID != null && !this.trackID.equals(other.trackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.Track[ trackID=" + trackID + " ]";
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }
    
}
