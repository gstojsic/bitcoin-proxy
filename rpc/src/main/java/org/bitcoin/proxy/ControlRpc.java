package org.bitcoin.proxy;

public interface ControlRpc {
    //getmemoryinfo
    void getMemoryInfo();

    //getrpcinfo
    void getRpcInfo();

    //help
    void help();

    //logging
    void logging();

    //stop
    void stop();

    //uptime
    void uptime();
}
