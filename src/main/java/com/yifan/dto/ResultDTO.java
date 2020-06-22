package com.yifan.dto;

import com.yifan.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO<T> {
    /* ------对象*/
    private Map<String,T> mapObj;
    private List<T> listObj;
    /*------信息*/
    private String msg;
    /*------状态码*/
    private Integer code;

    /*------请求失败*/
    public static ResultDTO MError(String msg, Integer code){
        ResultDTO<Movie> dto = new ResultDTO<>();
        dto.setCode(code);
        dto.setMsg(msg);
        return dto ;
    }

}
