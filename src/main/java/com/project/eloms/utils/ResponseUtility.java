package com.project.eloms.utils;

import com.project.eloms.dtos.ResponseDto;
import com.project.eloms.types.MessageType;
import com.project.eloms.types.ResponseStatus;

import java.util.Date;

public class ResponseUtility {

    public static ResponseDto getSuccessResponse(MessageType message){
        ResponseDto res = new ResponseDto();
        res.setCode("S0001");
        res.setMessage(message);
        res.setResponseStatus(ResponseStatus.SUCCESS);
        res.setResponseTime(new Date());
        return res;
    }

    public static ResponseDto getSuccessResponse(MessageType message, Object data){
        ResponseDto res = new ResponseDto();
        res.setCode("S0001");
        res.setMessage(message);
        res.setData(data);
        res.setResponseStatus(ResponseStatus.SUCCESS);
        res.setResponseTime(new Date());
        return res;
    }

    public static ResponseDto getErrorResponse(String errorCode, MessageType message){
        return new ResponseDto(errorCode,ResponseStatus.ERROR, message);
    }

    public static ResponseDto getErrorResponse(String errorCode, MessageType message, Object data){
        return new ResponseDto(errorCode, ResponseStatus.ERROR, message, data);
    }

    public static ResponseDto getInvalidResponse(String invalidCode, MessageType message){
        return new ResponseDto(invalidCode, ResponseStatus.INVALID, message);
    }

    public static ResponseDto getInvalidResponse(String invalidCode, MessageType message, Object data){
        return new ResponseDto(invalidCode, ResponseStatus.INVALID, message, data);
    }


}
