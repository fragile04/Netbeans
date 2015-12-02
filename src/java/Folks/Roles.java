/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Folks;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author WIN
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByRoleID", query = "SELECT r FROM Roles r WHERE r.roleID = :roleID"),
    @NamedQuery(name = "Roles.findByRoleTile", query = "SELECT r FROM Roles r WHERE r.roleTile = :roleTile")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "RoleID")
    private String roleID;
    @Size(max = 40)
    @Column(name = "RoleTile")
    private String roleTile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<Rolepermissions> rolepermissionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<Userroles> userrolesCollection;

    public Roles() {
    }

    public Roles(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleTile() {
        return roleTile;
    }

    public void setRoleTile(String roleTile) {
        this.roleTile = roleTile;
    }

    @XmlTransient
    public Collection<Rolepermissions> getRolepermissionsCollection() {
        return rolepermissionsCollection;
    }

    public void setRolepermissionsCollection(Collection<Rolepermissions> rolepermissionsCollection) {
        this.rolepermissionsCollection = rolepermissionsCollection;
    }

    @XmlTransient
    public Collection<Userroles> getUserrolesCollection() {
        return userrolesCollection;
    }

    public void setUserrolesCollection(Collection<Userroles> userrolesCollection) {
        this.userrolesCollection = userrolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleID != null ? roleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.roleID == null && other.roleID != null) || (this.roleID != null && !this.roleID.equals(other.roleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Folks.Roles[ roleID=" + roleID + " ]";
    }
    
}
