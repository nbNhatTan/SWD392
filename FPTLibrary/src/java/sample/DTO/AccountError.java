/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author NhatTan
 */
public class AccountError {
     private String accountIDError;
    private String fullNameError;
    private String passwordError;
    private String confirmError;
    private String roleIDError;
    private String emailError;
    private String addressError;
    private String phoneError;
    

    public AccountError() {
        accountIDError="";
        fullNameError="";
        passwordError="";
        confirmError="";
        roleIDError="";
        emailError="";
        addressError="";
        phoneError="";

    }

    public AccountError(String accountIDError, String fullNameError, String passwordError, String confirmError, String roleIDError, String emailError, String addressError, String phoneError) {
        this.accountIDError = accountIDError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.roleIDError = roleIDError;
        this.emailError = emailError;
        this.addressError = addressError;
        this.phoneError = phoneError;
    }

    public String getAccountIDError() {
        return accountIDError;
    }

    public void setAccountIDError(String accountIDError) {
        this.accountIDError = accountIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getRoleIDError() {
        return roleIDError;
    }

    public void setRoleIDError(String roleIDError) {
        this.roleIDError = roleIDError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

   

    
}
