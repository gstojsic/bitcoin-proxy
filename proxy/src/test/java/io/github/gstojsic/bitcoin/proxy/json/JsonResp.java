package io.github.gstojsic.bitcoin.proxy.json;

import java.util.List;

public class JsonResp {
    private String id;
    private String result;
    private String error;
    private boolean active;
    private double count;

    private Transaction transaction;

    private List<Transaction> oldTransactions;

    public JsonResp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getOldTransactions() {
        return oldTransactions;
    }

    public void setOldTransactions(List<Transaction> oldTransactions) {
        this.oldTransactions = oldTransactions;
    }
}
