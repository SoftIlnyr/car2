package com.bla.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by softi on 06.05.2016.
 */
public class UserForm {
    @NotEmpty(message = "Ник не должен быть пустым")
    private String nickname;
    @NotEmpty(message = "Для входа необходим пароль")
    private String password;
    private String passwordConfirmation;
    @NotEmpty(message = "Данная графа не должна быть пустой")
    private String firstname;
    @NotEmpty(message = "Данная графа не должна быть пустой")
    private String surname;
//    private MultipartFile avatar;

    @NotEmpty(message = "Данная графа не должна быть пустой")
    @Email(message = "Некорректный формат email")
    private String email;

    public UserForm() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

//    public MultipartFile getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(MultipartFile avatar) {
//        this.avatar = avatar;
//    }
}
