package com.example.TCSwim.responses;

import lombok.Getter;

@Getter
public class AuthenticationResponse {

    private Data data;

    public AuthenticationResponse(String token) {
        this.data = new Data(token);
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Getter
    public static class Data {
        private String token;

        public Data(String token) {
            this.token = token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

