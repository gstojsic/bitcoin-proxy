package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class PsbtAnalysisInput {
    /**
     * (boolean) Whether a UTXO is provided
     */
    @JsonProperty("has_utxo")
    private boolean hasUtxo;

    /**
     * (boolean) Whether the input is finalized
     */
    @JsonProperty("is_final")
    private boolean finalInput;

    /**
     * (json object, optional) Things that are missing that are required to complete this input
     */
    private PsbtAnalysisInputMissing missing;

    /**
     * (string, optional) Role of the next person that this input needs to go to
     */
    private String next;

    public boolean isHasUtxo() {
        return hasUtxo;
    }

    public void setHasUtxo(boolean hasUtxo) {
        this.hasUtxo = hasUtxo;
    }

    public boolean isFinalInput() {
        return finalInput;
    }

    public void setFinalInput(boolean finalInput) {
        this.finalInput = finalInput;
    }

    public PsbtAnalysisInputMissing getMissing() {
        return missing;
    }

    public void setMissing(PsbtAnalysisInputMissing missing) {
        this.missing = missing;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
