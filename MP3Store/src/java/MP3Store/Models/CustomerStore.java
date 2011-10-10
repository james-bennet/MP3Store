package MP3Store.Models;

import MP3Store.Connectors.CustomerConnector;
import java.io.Serializable;
import java.util.Date;
/**
 * @author james
 */
public class CustomerStore implements Serializable {
    private Date customerSince;
    private static final long serialVersionUID = 1L;
    private String Username;
    private String customerForename;
    private String customerSurname;
    private String customerTitle;
    private String customerEmail;
    private String customerAddress;
    private boolean verified;
    private int membershipType;
    private String password;

    public CustomerStore() {
        setDefaults();
    }
    
    public void setDefaults()
    {
        this.Username = null; //?
        this.customerForename = "John";
        this.customerSurname = "Smith";
        this.customerTitle = "Mr.";
        this.customerEmail = "email@test.com";
        this.customerAddress = "Enter an Address";
        this.customerSince = null; // ?
        this.verified = false;
        this.membershipType = 0;
        this.password = "password";
    }

    public CustomerStore(String Username) {
        setDefaults();
        this.Username = Username;

    }
    
    public boolean verifyPassword(String attempt) {
        if (password != null) {
            if (attempt.equalsIgnoreCase(password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean saveCustomer() {
        CustomerConnector myCustomerCustomerConnector = new CustomerConnector();
        boolean success = myCustomerCustomerConnector.insertCustomer(this);
        return success;
    }

        public boolean updateCustomer() {
        CustomerConnector myCustomerCustomerConnector = new CustomerConnector();
        boolean success = myCustomerCustomerConnector.updateCustomer(this);
        return success;
    }

    public CustomerStore(String Username, String customerForename, String customerSurname, String customerTitle, String customerEmail, String customerAddress, Date customerSince, boolean verified, int membershipType, String password) {
        this.Username = Username;
        this.customerForename = customerForename;
        this.customerSurname = customerSurname;
        this.customerTitle = customerTitle;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerSince = customerSince;
        this.verified = verified;
        this.membershipType = membershipType;
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getCustomerForename() {
        return customerForename;
    }

    public void setCustomerForename(String customerForename) {
        this.customerForename = customerForename;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(int membershipType) {
        this.membershipType = membershipType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Username != null ? Username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CustomerStore)) {
            return false;
        }
        CustomerStore other = (CustomerStore) object;
        if ((this.Username == null && other.Username != null) || (this.Username != null && !this.Username.equals(other.Username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.Customer[ Username=" + Username + " ]";
    }

    public Date getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(Date customerSince) {
        this.customerSince = customerSince;
    }
    
}
