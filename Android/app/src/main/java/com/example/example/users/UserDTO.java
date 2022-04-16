package com.example.example.users;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
@Data
public class UserDTO {
    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("email")
    @Expose
    private String email;
}
