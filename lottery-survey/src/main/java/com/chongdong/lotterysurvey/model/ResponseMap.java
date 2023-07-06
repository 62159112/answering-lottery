package com.chongdong.lotterysurvey.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseMap {
    private Boolean flag;
    private Object data;
    private String message;
}
