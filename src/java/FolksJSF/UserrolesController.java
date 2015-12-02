package FolksJSF;

import Folks.Userroles;
import FolksJSF.util.JsfUtil;
import FolksJSF.util.PaginationHelper;
import FolksSession.UserrolesFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("userrolesController")
@SessionScoped
public class UserrolesController implements Serializable {

    private Userroles current;
    private DataModel items = null;
    @EJB
    private FolksSession.UserrolesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UserrolesController() {
    }

    public Userroles getSelected() {
        if (current == null) {
            current = new Userroles();
            current.setUserrolesPK(new Folks.UserrolesPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private UserrolesFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Userroles) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Userroles();
        current.setUserrolesPK(new Folks.UserrolesPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getUserrolesPK().setRoleID(current.getRoles().getRoleID());
            current.getUserrolesPK().setUserID(current.getUsers().getUserID());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserrolesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Userroles) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getUserrolesPK().setRoleID(current.getRoles().getRoleID());
            current.getUserrolesPK().setUserID(current.getUsers().getUserID());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserrolesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Userroles) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserrolesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Userroles getUserroles(Folks.UserrolesPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Userroles.class)
    public static class UserrolesControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserrolesController controller = (UserrolesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userrolesController");
            return controller.getUserroles(getKey(value));
        }

        Folks.UserrolesPK getKey(String value) {
            Folks.UserrolesPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Folks.UserrolesPK();
            key.setUserRoleID(values[0]);
            key.setRoleID(values[1]);
            key.setUserID(values[2]);
            return key;
        }

        String getStringKey(Folks.UserrolesPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUserRoleID());
            sb.append(SEPARATOR);
            sb.append(value.getRoleID());
            sb.append(SEPARATOR);
            sb.append(value.getUserID());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Userroles) {
                Userroles o = (Userroles) object;
                return getStringKey(o.getUserrolesPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Userroles.class.getName());
            }
        }

    }

}
