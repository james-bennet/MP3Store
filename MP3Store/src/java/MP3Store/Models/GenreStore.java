package MP3Store.Models;

import java.io.Serializable;

/**
 * @author james
 */
public class GenreStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer genreID;
    private String genreName;
    private String genreDesc;

    public GenreStore() {
    }

    public GenreStore(Integer genreID) {
        this.genreID = genreID;
    }

    public GenreStore(Integer genreID, String genreName, String genreDesc) {
        this.genreID = genreID;
        this.genreName = genreName;
        this.genreDesc = genreDesc;
    }

    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreDesc() {
        return genreDesc;
    }

    public void setGenreDesc(String genreDesc) {
        this.genreDesc = genreDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genreID != null ? genreID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GenreStore)) {
            return false;
        }
        GenreStore other = (GenreStore) object;
        if ((this.genreID == null && other.genreID != null) || (this.genreID != null && !this.genreID.equals(other.genreID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.Genre[ genreID=" + genreID + " ]";
    }
    
}
