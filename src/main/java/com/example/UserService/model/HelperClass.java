package com.example.UserService.model;

import com.example.UserService.model.response.CommonResponse;
import com.example.UserService.utils.Constants;
import com.example.UserService.utils.Messages;

public class HelperClass {
    public static CommonResponse getNullRequestResoponse() {
        CommonResponse  commonResponse = new  CommonResponse();
        commonResponse.setMessage(Messages.NULL_REQUEST);
        commonResponse.setStatusCode(Constants.NULL_REQUEST);

        return  commonResponse;

    }

    public static CommonResponse getCommonExceptionResoponse() {
        CommonResponse  commonResponse = new  CommonResponse();
        commonResponse.setMessage(Messages.SOMETHING_WENT_WRONG);
        commonResponse.setStatusCode(Constants.ERROR_CD);

        return  commonResponse;

    }
}
