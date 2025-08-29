package model;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Category implements Serializable {
    private int cateid;
    private String catename;
    private String icon;
    private int userId;
    private Date createdDate;
    
    public Category() {
    }
    
    public Category(String catename, String icon, int userId) {
        this.catename = catename;
        this.icon = icon;
        this.userId = userId;
    }
    
    public Category(int cateid, String catename, String icon, int userId, Date createdDate) {
        this.cateid = cateid;
        this.catename = catename;
        this.icon = icon;
        this.userId = userId;
        this.createdDate = createdDate;
    }
    
    public int getCateid() {
        return cateid;
    }
    
    public void setCateid(int cateid) {
        this.cateid = cateid;
    }
    
    public String getCatename() {
        return catename;
    }
    
    public void setCatename(String catename) {
        this.catename = catename;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @Override
    public String toString() {
        return "Category [cateid=" + cateid + ", catename=" + catename + ", icon=" + icon + ", userId=" + userId
                + ", createdDate=" + createdDate + "]";
    }
}
