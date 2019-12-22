package com.example.demo.service;
/**
 * 房屋管理接口
 * @author LvChaoZhang
 *
 */

import com.example.demo.entity.HouseDetail;
import com.example.demo.web.form.HouseForm;
import com.example.demo.web.vo.ServiceResult;

public interface IHouseService {
	
	ServiceResult<HouseDetail> save(HouseForm houseForm);
	
	
}
