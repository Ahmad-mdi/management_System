package com.manage.response;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class ApiResponse<T> implements Serializable {
    private List<T> dataList;
    private ResponseStatus status;
    private boolean hasError;
    private String message;
    private long totalCount;

    public ApiResponse(List<T> dataList, ResponseStatus status) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 0;
    }
    public ApiResponse(List<T> dataList,long totalCount, ResponseStatus status) {
        this.dataList = dataList;
        this.status = status;
        this.message = "";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = totalCount;
    }
    public ApiResponse(T data, ResponseStatus status) {
        this.dataList = new ArrayList<T>();
        this.dataList .add(data);
        this.status = status;
        this.message = "ok";
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 1;
    }
    public ApiResponse(String message, ResponseStatus status) {
        this.dataList = new ArrayList<T>();
        this.status = status;
        this.message = message;
        this.hasError = status != ResponseStatus.SUCCESS;
        this.totalCount = 0;
    }
    public ApiResponse(Exception ex) {
        this.dataList = new ArrayList<T>();
        this.status = ResponseStatus.EXCEPTION;
        this.message = ex.getMessage();
        this.hasError = true;
        this.totalCount = 0;
    }
}
