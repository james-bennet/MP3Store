package MP3Store.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author james
 */

public class TransactionStore implements Serializable {
    private Date transactionDate;
    private static final long serialVersionUID = 1L;
    private Integer transactionID;
    private int Username;
    private int paymentRef;

    public TransactionStore() {
    }

    public TransactionStore(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public TransactionStore(Integer transactionID, int Username, int paymentRef, Date transactionDate) {
        this.transactionID = transactionID;
        this.Username = Username;
        this.paymentRef = paymentRef;
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public int getUsername() {
        return Username;
    }

    public void setUsername(int Username) {
        this.Username = Username;
    }

    public int getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(int paymentRef) {
        this.paymentRef = paymentRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionID != null ? transactionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TransactionStore)) {
            return false;
        }
        TransactionStore other = (TransactionStore) object;
        if ((this.transactionID == null && other.transactionID != null) || (this.transactionID != null && !this.transactionID.equals(other.transactionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.Transaction[ transactionID=" + transactionID + " ]";
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    
}
