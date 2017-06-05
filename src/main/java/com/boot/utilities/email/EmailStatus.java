package com.boot.utilities.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailStatus {

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    private String to;
    private String subject;
    private String body;
    private String status;
    private String errorMessage;

    public EmailStatus(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public EmailStatus success() {
        this.status = SUCCESS;
        return this;
    }

    public EmailStatus error(String errorMessage) {
        this.status = ERROR;
        this.errorMessage = errorMessage;
        return this;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(this.status);
    }

    public boolean isError() {
        return ERROR.equals(this.status);
    }

}