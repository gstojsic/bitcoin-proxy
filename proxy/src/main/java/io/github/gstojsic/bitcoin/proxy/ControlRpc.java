package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.MemoryInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.RpcInfo;
import io.github.gstojsic.bitcoin.proxy.model.Command;
import io.github.gstojsic.bitcoin.proxy.model.Logging;

import java.util.Map;
import java.util.Set;

public interface ControlRpc {

    /**
     * @see ControlRpcAsync#getMemoryInfo()
     */
    MemoryInfo getMemoryInfo();

    /**
     * @see ControlRpcAsync#getMemoryInfoMalloc()
     */
    String getMemoryInfoMalloc();

    /**
     * @see ControlRpcAsync#getRpcInfo()
     */
    RpcInfo getRpcInfo();

    /**
     * @see ControlRpcAsync#help(Command)
     */
    String help(Command command);

    /**
     * @see ControlRpcAsync#logging(Set, Set)
     */
    Map<String, Boolean> logging(Set<Logging> include, Set<Logging> exclude);

    /**
     * @see ControlRpcAsync#stop()
     */
    String stop();

    /**
     * @see ControlRpcAsync#uptime()
     */
    int uptime();
}














































































































































