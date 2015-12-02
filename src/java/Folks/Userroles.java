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
@Table(name = "userroles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userroles.findAll", query = "SELECT u FROM Userroles u"),
    @NamedQuery(name = "Userroles.findByUserRoleID", query = "SELECT u FROM Userroles u WHERE u.userrolesPK.userRoleID = :userRoleID"),
    @NamedQuery(name = "Userroles.findByRoleID", query = "SELECT u FROM Userroles u WHERE u.userrolesPK.roleID = :roleID"),
    @NamedQuery(name = "Userroles.findByUserID", query = "SELECT u FROM Userroles u WHERE u.userrolesPK.userID = :userID")})
public class Userroles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserrolesPK userrolesPK;
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Userroles() {
    }

    public Userroles(UserrolesPK userrolesPK) {
        this.userrolesPK = userrolesPK;
    }

    public Userroles(String userRoleID, String roleID, String userID) {
        this.userrolesPK = new UserrolesPK(userRoleID, roleID, userID);
    }

    public UserrolesPK getUserrolesPK() {
        return userrolesPK;
    }

    public void setUserrolesPK(UserrolesPK userrolesPK) {
        this.userrolesPK = userrolesPK;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userrolesPK != null ? userrolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userroles)) {
            return false;
        }
        Userroles other = (Userroles) object;
        if ((this.userrolesPK == null && other.userrolesPK != null) || (this.userrolesPK != null && !this.userrolesPK.equals(other.userrolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Folks.Userroles[ userrolesPK=" + userrolesPK + " ]";
    }
    
}
