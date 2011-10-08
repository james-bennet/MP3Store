package MP3Store.Models;

import java.io.Serializable;

/**
 * @author james
 */
public class MembershipTypeStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer membershipTypeID;
    private String membershipName;
    private String membershipTypeDesc;
    private boolean canDownload;
    private boolean canRedownload;
    private boolean canUpload;
    private boolean canDownloadUnlimited;

    public MembershipTypeStore() {
    }

    public MembershipTypeStore(Integer membershipTypeID) {
        this.membershipTypeID = membershipTypeID;
    }

    public MembershipTypeStore(Integer membershipTypeID, String membershipName, String membershipTypeDesc, boolean canDownload, boolean canRedownload, boolean canUpload, boolean canDownloadUnlimited) {
        this.membershipTypeID = membershipTypeID;
        this.membershipName = membershipName;
        this.membershipTypeDesc = membershipTypeDesc;
        this.canDownload = canDownload;
        this.canRedownload = canRedownload;
        this.canUpload = canUpload;
        this.canDownloadUnlimited = canDownloadUnlimited;
    }

    public Integer getMembershipTypeID() {
        return membershipTypeID;
    }

    public void setMembershipTypeID(Integer membershipTypeID) {
        this.membershipTypeID = membershipTypeID;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public String getMembershipTypeDesc() {
        return membershipTypeDesc;
    }

    public void setMembershipTypeDesc(String membershipTypeDesc) {
        this.membershipTypeDesc = membershipTypeDesc;
    }

    public boolean getCanDownload() {
        return canDownload;
    }

    public void setCanDownload(boolean canDownload) {
        this.canDownload = canDownload;
    }

    public boolean getCanRedownload() {
        return canRedownload;
    }

    public void setCanRedownload(boolean canRedownload) {
        this.canRedownload = canRedownload;
    }

    public boolean getCanUpload() {
        return canUpload;
    }

    public void setCanUpload(boolean canUpload) {
        this.canUpload = canUpload;
    }

    public boolean getCanDownloadUnlimited() {
        return canDownloadUnlimited;
    }

    public void setCanDownloadUnlimited(boolean canDownloadUnlimited) {
        this.canDownloadUnlimited = canDownloadUnlimited;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (membershipTypeID != null ? membershipTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MembershipTypeStore)) {
            return false;
        }
        MembershipTypeStore other = (MembershipTypeStore) object;
        if ((this.membershipTypeID == null && other.membershipTypeID != null) || (this.membershipTypeID != null && !this.membershipTypeID.equals(other.membershipTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MP3Store.MembershipType[ membershipTypeID=" + membershipTypeID + " ]";
    }
    
}
