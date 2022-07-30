package com.facebook.api.dto;

import com.facebook.api.validator.EmailValidator;
import com.facebook.api.validator.PhoneValidator;

public class ContactDTO {
    @EmailValidator
    private String email;
    @PhoneValidator
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
