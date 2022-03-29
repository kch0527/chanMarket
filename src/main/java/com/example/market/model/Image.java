package com.example.market.model;

import javax.persistence.Embeddable;
import java.util.Objects;


public class Image {
    private String uuid;
    private String imageName;
    private String contentType;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(uuid, image.uuid) && Objects.equals(imageName, image.imageName) && Objects.equals(contentType, image.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, imageName, contentType);
    }
}
