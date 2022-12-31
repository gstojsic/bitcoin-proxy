package io.github.gstojsic.bitcoin.proxy.model;

public enum ImportType {
    /**
     * If using descriptor, do not also provide address/scriptPubKey, scripts, or pubkeys
     */
    descriptor,
    /**
     * scriptPubKey as string
     */
    script,
    /**
     * scriptPubKey as address (json)
     */
    address
}
