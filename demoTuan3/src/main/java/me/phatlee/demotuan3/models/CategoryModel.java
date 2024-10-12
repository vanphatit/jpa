package me.phatlee.demotuan3.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private int categoryID;
    private String categoryName;
    private String images;
    private int status;

    public CategoryModel() {
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", images='" + images + '\'' +
                ", status=" + status +
                '}';
    }
}
