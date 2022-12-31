package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class LockedMemoryManagerInfo {
    /**
     * (numeric) Number of bytes used
     */
    private int used;

    /**
     * (numeric) Number of bytes available in current arenas
     */
    private int free;

    /**
     * (numeric) Total number of bytes managed
     */
    private int total;

    /**
     * (numeric) Amount of bytes that succeeded locking. If this number is smaller than total, locking pages failed at some point and key data could be swapped to disk.
     */
    private int locked;

    /**
     * (numeric) Number allocated chunks
     */
    @JsonProperty("chunks_used")
    private int chunksUsed;

    /**
     * (numeric) Number unused chunks
     */
    @JsonProperty("chunks_free")
    private int chunksFree;

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getChunksUsed() {
        return chunksUsed;
    }

    public void setChunksUsed(int chunksUsed) {
        this.chunksUsed = chunksUsed;
    }

    public int getChunksFree() {
        return chunksFree;
    }

    public void setChunksFree(int chunksFree) {
        this.chunksFree = chunksFree;
    }
}