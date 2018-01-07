package com.bisheng.services.system.dao.customized;

import java.util.Map;

import com.bisheng.services.system.dao.generated.UserExhibitMapper;

public interface UserExhibitDao extends UserExhibitMapper{

	int batchInsert(Map<String, Object> addUserExhibitMap);

}
