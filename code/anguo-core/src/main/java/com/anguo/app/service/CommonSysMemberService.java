package com.anguo.app.service;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anguo.app.db.domain.CommonAppLoggedUser;
import com.anguo.app.db.domain.CommonSysMember;
import com.anguo.app.db.domain.ConstantClass;
import com.anguo.app.db.mapper.CommonAppLoggedUserMapper;
import com.anguo.app.db.mapper.CommonSysMemberMapper;
import com.anguo.exception.AnguoException;
import com.anguo.mybatis.db.service.BaseService;


@Service
public class CommonSysMemberService extends BaseService<CommonSysMember> {
	
	@Autowired
	private CommonSysMemberMapper mapper;
	
	@Autowired
	private CommonAppLoggedUserMapper commonAppLoggedUserMapper;
	
	
	
	/**
	 * 根据用户名单条数据
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public CommonSysMember login(CommonSysMember commonSysMember) throws AnguoException
	{
		//根据用户名查询用户
		CommonSysMember geoMember= this.mapper.getDataByUserName(commonSysMember);
		
		if(geoMember==null)
		{
			throw new AnguoException(ConstantClass.INTERFACE_USERNAME_ERROR);
			
		}else if(!geoMember.getPassword().equals(commonSysMember.getPassword()))
		{
			throw new AnguoException(ConstantClass.INTERFACE_PASSWORD_ERROR);
		}
		
		
		
		return geoMember;
	}
	
	
	
	/**
	 * 根据uuid获取当前登录用户
	 * @param obj
	 * @return
	 */
	public CommonSysMember getDataByUUID(String obj)
	{
		return this.mapper.getDataByUUID(obj);
	}
	
	
	
	public CommonSysMemberMapper getMapper() {
		return mapper;
	}
}
