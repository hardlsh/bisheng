package com.bisheng.services.system.dao.customized;

import java.util.List;

import com.bisheng.apps.system.param.DictQuery;
import com.bisheng.services.system.dao.generated.DictMapper;
import com.bisheng.services.system.model.generated.Dict;

public interface DictDao extends DictMapper{

	List<Dict> queryDictList(DictQuery query);

}
