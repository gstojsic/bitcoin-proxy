package io.github.gstojsic.bitcoin.proxy.json.model;

public class ZmqNotificationInfo {
    /**
     * (string) Type of notification
     */
    private String type;

    /**
     * (string) Address of the publisher
     */
    private String address;

    /**
     * (numeric) Outbound message high water mark
     */
    private String hwm;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHwm() {
        return hwm;
    }

    public void setHwm(String hwm) {
        this.hwm = hwm;
    }
}
