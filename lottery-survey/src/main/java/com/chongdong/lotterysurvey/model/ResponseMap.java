package com.chongdong.lotterysurvey.model;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ResponseMap {
    private Boolean flag;
    private Object data;
    private String message;
    //成功静态方法
    public static ResponseMap ok() {
        ResponseMap respond = new ResponseMap();
        respond.setFlag(true);
        respond.setMessage("成功");
        return respond;
    }

    //失败静态方法
    public static ResponseMap error() {
        ResponseMap respond = new ResponseMap();
        respond.setFlag(false);
        respond.setMessage("失败");
        return respond;
    }

    public ResponseMap success(Boolean success){
        this.setFlag(success);
        return this;
    }

    public ResponseMap message(String message){
        this.setMessage(message);
        return this;
    }

    public ResponseMap data(Object data){
        this.setData(data);
        return this;
    }
}
