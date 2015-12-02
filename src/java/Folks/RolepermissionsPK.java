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
public class RolepermissionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RolePermissionsID")
    private String rolePermissionsID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "RoleID")
    private String roleID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PermissionsID")
    private String permissionsID;

    public RolepermissionsPK() {
    }

    public RolepermissionsPK(String rolePermissionsID, String roleID, String permissionsID) {
        this.rolePermissionsID = rolePermissionsID;
        this.roleID = roleID;
        this.permissionsID = permissionsID;
    }

    public String getRolePermissionsID() {
        return rolePermissionsID;
    }

    public void setRolePermissionsID(String rolePermissionsID) {
        this.rolePermissionsID = rolePermissionsID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPermissionsID() {
        return permissionsID;
    }

    public void setPermissionsID(String permissionsID) {
        this.permissionsID = permissionsID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolePermissionsID != null ? rolePermissionsID.hashCode() : 0);
        hash += (roleID != null ? roleID.hashCode() : 0);
        hash += (permissionsID != null ? permissionsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolepermissionsPK)) {
            return false;
        }
        RolepermissionsPK other = (RolepermissionsPK) object;
        if ((this.rolePermissionsID == null && other.rolePermissionsID != null) || (this.rolePermissionsID != null && !this.rolePermissionsID.equals(other.rolePermissionsID))) {
            return false;
        }
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        if ((this.permissionsID == null && other.permissionsID != null) || (this.permissionsID != null && !this.permissionsID.equals(other.permissionsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Folks.RolepermissionsPK[ rolePermissionsID=" + rolePermissionsID + ", roleID=" + roleID + ", permissionsID=" + permissionsID + " ]";
    }
    
}
