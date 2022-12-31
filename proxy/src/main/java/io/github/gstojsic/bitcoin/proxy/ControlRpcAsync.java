package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.MemoryInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.RpcInfo;
import io.github.gstojsic.bitcoin.proxy.model.Command;
import io.github.gstojsic.bitcoin.proxy.model.Logging;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface ControlRpcAsync {

    /**
     * <p>Calls getmemoryinfo method on the bitcoin node which returns general statistics about memory usage in the node.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmemoryinfo);</pre>
     *
     * @return general statistics about memory usage in the node. See {@link MemoryInfo}
     */
    CompletableFuture<MemoryInfo> getMemoryInfo();

    /**
     * <p>Calls getmemoryinfo method on the bitcoin node which returns an XML string describing low-level heap state.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmemoryinfo);</pre>
     *
     * @return an XML string describing low-level heap state (only available if compiled with glibc 2.10+).
     */
    CompletableFuture<String> getMemoryInfoMalloc();

    /**
     * <p>Calls getrpcinfo method on the bitcoin node which returns details of the RPC server.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getrpcinfo);</pre>
     *
     * @return details of the RPC server. See {@link RpcInfo}
     */
    CompletableFuture<RpcInfo> getRpcInfo();

    /**
     * <p>Calls help method on the bitcoin node which list all commands, or get help for a specified command.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.help);</pre>
     *
     * @param command The command to get help on, if null a list of all available commands is returned
     * @return The help text
     */
    CompletableFuture<String> help(Command command);

    /**
     * <p>Calls logging method on the bitcoin node which gets and sets the logging configuration.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.logging);</pre>
     *
     * @param include The categories to add to debug logging
     * @param exclude The categories to remove from debug logging
     * @return map of (log category -> is logged)
     */
    CompletableFuture<Map<String, Boolean>> logging(Set<Logging> include, Set<Logging> exclude);

    /**
     * <p>Calls stop method on the bitcoin node which request a graceful shutdown of Bitcoin Core</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.stop);</pre>
     *
     * @return "Bitcoin Core stopping" if request is successful
     */
    CompletableFuture<String> stop();

    /**
     * <p>Calls uptime method on the bitcoin node which returns the total uptime of the server</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.uptime);</pre>
     *
     * @return The number of seconds that the server has been running
     */
    CompletableFuture<Integer> uptime();
}
