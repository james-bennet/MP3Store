package MP3Store.Models;

import java.io.Serializable;

/**
 * @author james
 */

public class TransactionLineStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private int transactionID;
    private Integer transactionLineID;
    private int trackID;

    public TransactionLineStore() {
    }

    public TransactionLineStore(Integer transactionLineID) {
        this.transactionLineID = transactionLineID;
    }

    public TransactionLineStore(Integer transactionLineID, int transactionID, int trackID) {
        this.transactionLineID = transactionLineID;
        this.transactionID = transactionID;
        this.trackID = trackID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Integer getTransactionLineID() {
        return transactionLineID;
    }

    public void setTransactionLineID(Integer transactionLineID) {
        this.transactionLineID = transactionLineID;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionLineID != null ? transactionLineID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TransactionLineStore)) {
            return false;
        }
        TransactionLineStore other = (TransactionLineStore) object;
        if ((this.transactionLineID == null && other.transactionLineID != null) || (this.transactionLineID != null && !this.transactionLineID.equals(other.transactionLineID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.TransactionLine[ transactionLineID=" + transactionLineID + " ]";
    }
    
}
