package com.bookstore.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Entity
@Builder
@Table
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull    
    private int otp;
    @NotNull
    private Date exDate;
    @OneToOne
    private User user;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOtp() {
        return otp;
    }
    public void setOtp(int otp) {
        this.otp = otp;
    }
    public Date getExDate() {
        return exDate;
    }
    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ForgotPassword(int id, @NotNull int otp, @NotNull Date exDate, User user) {
        this.id = id;
        this.otp = otp;
        this.exDate = exDate;
        this.user = user;
    }
    public ForgotPassword() {
    }

}
