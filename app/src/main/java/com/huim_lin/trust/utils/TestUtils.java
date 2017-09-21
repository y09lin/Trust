package com.huim_lin.trust.utils;


import android.content.Context;

import com.huim_lin.trust.adapter.UserAdapter;
import com.huim_lin.trust.bean.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestUtils implements Serializable{
    private static final long serialVersionUID = 1L;

    private List<User> beanList;
    private UserAdapter adapter;

    private void initData(Context context){
        beanList=new ArrayList<>();
        adapter=new UserAdapter(beanList,context);
    }
}

