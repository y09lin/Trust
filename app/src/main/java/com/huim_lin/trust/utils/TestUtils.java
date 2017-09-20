package com.huim_lin.trust.utils;


import android.content.Context;

import com.huim_lin.trust.adapter.UserAdapter;
import com.huim_lin.trust.bean.User;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    private List<User> beanList;
    private UserAdapter adapter;

    private void initData(Context context){
        beanList=new ArrayList<>();
        adapter=new UserAdapter(beanList,context);
    }
}

