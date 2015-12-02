/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Folks;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WIN
 */
@Entity
@Table(name = "rolepermissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolepermissions.findAll", query = "SELECT r FROM Rolepermissions r"),
    @NamedQuery(name = "Rolepermissions.findByRolePermissionsID", query = "SELECT r FROM Rolepermissions r WHERE r.rolepermissionsPK.rolePermissionsID = :rolePermissionsID"),
    @NamedQuery(name = "Rolepermissions.findByRoleID", query = "SELECT r FROM Rolepermissions r WHERE r.rolepermissionsPK.roleID = :roleID"),
    @NamedQuery(name = "Rolepermissions.findByPermissionsID", query = "SELECT r FROM Rolepermissions r WHERE r.rolepermissionsPK.permissionsID = :permissionsID")})
public class Rolepermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolepermissionsPK rolepermissionsPK;
    @JoinColumn(name = "PermissionsID", referencedColumnName = "PermissionsID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Permissions permissions;
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;

    public Rolepermissions() {
    }

    public Rolepermissions(RolepermissionsPK rolepermissionsPK) {
        this.rolepermissionsPK = rolepermissionsPK;
    }

    public Rolepermissions(String rolePermissionsID, String roleID, String permissionsID) {
        this.rolepermissionsPK = new RolepermissionsPK(rolePermissionsID, roleID, permissionsID);
    }

    public RolepermissionsPK getRolepermissionsPK() {
        return rolepermissionsPK;
    }

    public void setRolepermissionsPK(RolepermissionsPK rolepermissionsPK) {
        this.rolepermissionsPK = rolepermissionsPK;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolepermissionsPK != null ? rolepermissionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolepermissions)) {
            return false;
        }
        Rolepermissions other = (Rolepermissions) object;
        if ((this.rolepermissionsPK == null && other.rolepermissionsPK != null) || (this.rolepermissionsPK != null && !this.rolepermissionsPK.equals(other.rolepermissionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Folks.Rolepermissions[ rolepermissionsPK=" + rolepermissionsPK + " ]";
    }
    
}
