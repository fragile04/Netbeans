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
@Table(name = "permissions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p"),
    @NamedQuery(name = "Permissions.findByPermissionsID", query = "SELECT p FROM Permissions p WHERE p.permissionsID = :permissionsID"),
    @NamedQuery(name = "Permissions.findByPermissionsTilte", query = "SELECT p FROM Permissions p WHERE p.permissionsTilte = :permissionsTilte"),
    @NamedQuery(name = "Permissions.findByPermissionsConstantName", query = "SELECT p FROM Permissions p WHERE p.permissionsConstantName = :permissionsConstantName")})
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PermissionsID")
    private String permissionsID;
    @Size(max = 40)
    @Column(name = "PermissionsTilte")
    private String permissionsTilte;
    @Size(max = 40)
    @Column(name = "PermissionsConstantName")
    private String permissionsConstantName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permissions")
    private Collection<Rolepermissions> rolepermissionsCollection;

    public Permissions() {
    }

    public Permissions(String permissionsID) {
        this.permissionsID = permissionsID;
    }

    public String getPermissionsID() {
        return permissionsID;
    }

    public void setPermissionsID(String permissionsID) {
        this.permissionsID = permissionsID;
    }

    public String getPermissionsTilte() {
        return permissionsTilte;
    }

    public void setPermissionsTilte(String permissionsTilte) {
        this.permissionsTilte = permissionsTilte;
    }

    public String getPermissionsConstantName() {
        return permissionsConstantName;
    }

    public void setPermissionsConstantName(String permissionsConstantName) {
        this.permissionsConstantName = permissionsConstantName;
    }

    @XmlTransient
    public Collection<Rolepermissions> getRolepermissionsCollection() {
        return rolepermissionsCollection;
    }

    public void setRolepermissionsCollection(Collection<Rolepermissions> rolepermissionsCollection) {
        this.rolepermissionsCollection = rolepermissionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissionsID != null ? permissionsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissions)) {
            return false;
        }
        Permissions other = (Permissions) object;
        if ((this.permissionsID == null && other.permissionsID != null) || (this.permissionsID != null && !this.permissionsID.equals(other.permissionsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Folks.Permissions[ permissionsID=" + permissionsID + " ]";
    }
    
}
