package com.lindong.eagledb.exception;

import java.io.Serializable;

public class KeyNotFoundException extends Exception implements Serializable {
    public final String key;

    public KeyNotFoundException(String key) {
        this.key = key;
    }
}
