package io.github.gstojsic.bitcoin.proxy.json.model;

public class MemoryInfo {
    /**
     * (json object) Information about locked memory manager
     */
    private LockedMemoryManagerInfo locked;

    public LockedMemoryManagerInfo getLocked() {
        return locked;
    }

    public void setLocked(LockedMemoryManagerInfo locked) {
        this.locked = locked;
    }
}