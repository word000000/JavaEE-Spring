package com.example.demo.core.response;

import jdk.jfr.DataAmount;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

/**
 * @Author:GQM
 * @Date:created in 23:43 2020/5/27
 * @Description:
 * @Modifyed_By:
 */

@Data
public class DataResponse<T> {

    private int code;
    private String msg;
    private T data;

}
