/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FolksSession;

import Folks.Userroles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author WIN
 */
@Stateless
public class UserrolesFacade extends AbstractFacade<Userroles> {

    @PersistenceContext(unitName = "5605104017PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserrolesFacade() {
        super(Userroles.class);
    }
    
}
