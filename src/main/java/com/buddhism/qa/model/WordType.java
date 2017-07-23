package com.buddhism.qa.model;

import com.sun.corba.se.spi.monitoring.MonitoredAttributeInfo;

/**
 * 词语类型
 * Created by TT. Wu on 2017/4/22.
 */
public enum WordType {
    NULL("非术语"),
    PERSON("人物"),
    DAILY_LIFE("日常生活"),
    INCANTATION("咒语"),
    GEOGRAPHY("地理"),
    SECT("宗派"),
    TEMPLE("寺塔"),
    DOCTRINE("教义"),
    DAILY_SUPPLY("日常用品"),
    ART("艺术"),
    POEM("诗偈"),
    X("未分类");

    public String getDescription() {
        return description;
    }

    public final String description;

    WordType(String description) {
        this.description = description;
    }

    /**
     * 根据术语分类获得相应的词性
     * @return
     */
    public String getPos(){
        switch (this){
            case NULL:
                return null;
            case PERSON:
                return "bpn_p";
            case DAILY_LIFE:
                return "bpn_dl";
            case INCANTATION:
                return "bpn_i";
            case GEOGRAPHY:
                return "bpn_g";
            case SECT:
                return "bpn_s";
            case TEMPLE:
                return "bpn_t";
            case DOCTRINE:
                return "bpn_d";
            case DAILY_SUPPLY:
                return "bpn_ds";
            case ART:
                return "bpn_a";
            case POEM:
                return "bpn_po";
            case X:
                return "bpn_x";
        }
        return null;
    }
}
