package MP3Store.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author james
 */
public class AlbumStore implements Serializable {
    private Date releaseDate;
    private static final long serialVersionUID = 1L;
    private Integer albumID;
    private int bandID;
    private String albumName;
    private int albumGenre;
    private String albumDesc;
    private int rating;

    public AlbumStore() {
    }

    public AlbumStore(Integer albumID) {
        this.albumID = albumID;
    }

    public AlbumStore(Integer albumID, int bandID, String albumName, int albumGenre, String albumDesc, Date releaseDate, int rating) {
        this.albumID = albumID;
        this.bandID = bandID;
        this.albumName = albumName;
        this.albumGenre = albumGenre;
        this.albumDesc = albumDesc;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public Integer getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Integer albumID) {
        this.albumID = albumID;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(int albumGenre) {
        this.albumGenre = albumGenre;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumID != null ? albumID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AlbumStore)) {
            return false;
        }
        AlbumStore other = (AlbumStore) object;
        if ((this.albumID == null && other.albumID != null) || (this.albumID != null && !this.albumID.equals(other.albumID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.Album[ albumID=" + albumID + " ]";
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
}
