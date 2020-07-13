package org.spring.springboot.domain;

import java.io.Serializable;

public class ResultVO implements Serializable {
    private static final long serialVersionUID = 6543751866024162628L;
    private String kind;
    private boolean result;
    private String msg;
    private Object data;

    public ResultVO() {
        this.result = true;
    }

    public ResultVO(boolean result, String kind) {
        this.result = result;
        this.kind = kind;
    }

    public ResultVO(String kind, String msg) {
        this.result = false;
        this.kind = kind;
        this.msg = msg;
    }

    public ResultVO(boolean result, String kind, String msg) {
        this.result = result;
        this.kind = kind;
        this.msg = msg;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    
	public ResultVO(boolean result, Object data, String kind, String msg) {
		this.result = result;
		this.data = data;
		this.kind = kind;
		this.msg = msg;
	}

}
