package com.example.integradorii.estructura;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Post {
    @SerializedName("id_post")
    private int idPost;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("id_user")
    private int idUser;

    @SerializedName("status")
    private String status;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("img")
    private String img;

    public Post(int idPost, String title, String body, int idUser, String status, Date createdAt, String img) {
        this.idPost = idPost;
        this.title = title;
        this.body = body;
        this.idUser = idUser;
        this.status = status;
        this.createdAt = createdAt;
        this.img = img;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
