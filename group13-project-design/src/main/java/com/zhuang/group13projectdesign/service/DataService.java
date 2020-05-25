package com.zhuang.group13projectdesign.service;

import com.zhuang.group13projectdesign.bean.Data;
import com.zhuang.group13projectdesign.mapper.DataMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DataService {

    @Resource
    private DataMapper dataMapper;

    public void insertData(Data data) {
        dataMapper.insertData(data);
    }

    public Data getData(int id) {
        Data data = dataMapper.getData(id);
        return data;
    }
}
