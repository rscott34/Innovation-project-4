package Group4.tracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "images", schema = "public")public class Image {
    @Id
    private String imageId;

    @OneToOne
    @JoinColumn(name = "product_id") 
    private Products productLinked; 

    private String fileLocation;

    public Image() {
    }

    public Image(String imageId, Products productLinked, String fileLocation) {
        this.imageId = imageId;
        this.productLinked = productLinked;
        this.fileLocation = fileLocation;
    }

// getters and setters
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Products getProduct() { 
        return productLinked; 
    }

    public void setProduct(Products product) { 
        this.productLinked = product; 
    }

    public String getFileLocation() { 
        return fileLocation; 
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}