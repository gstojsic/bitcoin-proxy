package io.github.gstojsic.bitcoin.proxy.json.model;

import java.util.Map;

public class DeploymentInfo {
    /**
     * (string) requested block hash (or tip)
     */
    private String hash;

    /**
     * (numeric) requested block height (or tip)
     */
    private int height;

    /**
     * (json object)
     */
    private Map<String, Deployment> deployments;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map<String, Deployment> getDeployments() {
        return deployments;
    }

    public void setDeployments(Map<String, Deployment> deployments) {
        this.deployments = deployments;
    }
}