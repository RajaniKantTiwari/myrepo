package com.app.community.network.request;

/**
 * Created by on 23/12/17.
 */

public class VerifyMobileRequest {
    private String mobile;
    private int otp;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public VerifyMobileRequest(String mobile, int otp) {
        this.mobile=mobile;
        this.otp=otp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
