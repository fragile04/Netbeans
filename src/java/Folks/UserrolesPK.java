/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Folks;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author WIN
 */
@Embeddable
public class UserrolesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "UserRoleID")
    private String userRoleID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RoleID")
    private String roleID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "UserID")
    private String userID;

    public UserrolesPK() {
    }

    public UserrolesPK(String userRoleID, String roleID, String userID) {
        this.userRoleID = userRoleID;
        this.roleID = roleID;
        this.userID = userID;
    }

    public String getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(String userRoleID) {
        this.userRoleID = userRoleID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRoleID != null ? userRoleID.hashCode() : 0);
        hash += (roleID != null ? roleID.hashCode() : 0);
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserrolesPK)) {
            return false;
        }
        UserrolesPK other = (UserrolesPK) object;
        if ((this.userRoleID == null && other.userRoleID != null) || (this.userRoleID != null && !this.userRoleID.equals(other.userRoleID))) {
            return false;
        }
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Folks.UserrolesPK[ userRoleID=" + userRoleID + ", roleID=" + roleID + ", userID=" + userID + " ]";
    }
    
}
