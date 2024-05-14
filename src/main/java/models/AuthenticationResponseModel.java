package models;

public class AuthenticationResponseModel {
    String token;

    public AuthenticationResponseModel token(String token){
        this.token = token;
        return this;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthenticationResponseModel{" +
                "token='" + token + '\'' +
                '}';
    }
}
