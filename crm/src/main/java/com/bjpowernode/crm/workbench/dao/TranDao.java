package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Tran;

public interface TranDao {

    int save(Tran t);

    Tran detail(String id);

    int changeStage(Tran t);
}
