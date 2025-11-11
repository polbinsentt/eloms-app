package com.project.eloms.dtos;

import com.project.eloms.types.MessageType;
import com.project.eloms.types.ResponseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String code;
    private ResponseStatus responseStatus;
    private MessageType message;
    private Object data;
    private Date responseTime;

    public ResponseDto(String code, ResponseStatus responseStatus, MessageType message){
        this.code = code;
        this.responseStatus = responseStatus;
        this.message = message;
        this.responseTime = new Date();
    }

    public ResponseDto(String code, ResponseStatus responseStatus, MessageType message, Object data){
        this.code = code;
        this.responseStatus = responseStatus;
        this.message = message;
        this.data = data;
        this.responseTime = new Date();
    }
}
