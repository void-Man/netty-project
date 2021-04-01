package com.cmj.example.vo;

import java.io.Serializable;

public class EbuyProductParametersVo implements Serializable {
    private static final long serialVersionUID = -2126959022858589651L;
    private String id;
    /**
     * 参数名称
     */
    private String propname;
    /**
     * 参数值
     */
    private String propvalue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropname() {
        return propname;
    }

    public void setPropname(String propname) {
        this.propname = propname;
    }

    public String getPropvalue() {
        return propvalue;
    }

    public void setPropvalue(String propvalue) {
        this.propvalue = propvalue;
    }
}
