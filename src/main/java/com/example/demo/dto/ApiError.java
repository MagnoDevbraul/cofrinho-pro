package com.example.demo.dto;

import java.time.LocalDateTime;

public class ApiError {

    private final LocalDateTime timestamp;
    private final int status;
    private final String erro;
    private final String mensagem;

    public ApiError(int status, String erro, String mensagem) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public String getMensagem() {
        return mensagem;
    }
}