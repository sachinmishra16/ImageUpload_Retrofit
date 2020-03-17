package com.blueapple.imageupload_retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageRequest
{
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("voucher_id")
    @Expose
    private String voucherId;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }
}
