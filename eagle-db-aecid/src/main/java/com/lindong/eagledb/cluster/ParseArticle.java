package com.lindong.eagledb.cluster;

import java.io.Serializable;

public class ParseArticle implements Serializable {
    public final String htmlBody;
    public ParseArticle(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
