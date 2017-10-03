package com.icarusrises.teststuff.retrofit.infrastrucre;

public class UrlWrapper {

    private String urlAddress;

    public UrlWrapper() {
    }

    public UrlWrapper(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }
}
