package file.com.zsl.web.modules.company.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsl.web.common.dao.IOperations;
import com.zsl.web.common.service.AbstractService;

import file.com.zsl.web.modules.company.dao.ICompanyGroupDao;
import file.com.zsl.web.modules.company.model.CompanyGroup;
import file.com.zsl.web.modules.company.service.ICompanyGroupService;
@Repository(value="companyGroupService")
public class CompanyGroupServiceImpl extends AbstractService<CompanyGroup> implements
		ICompanyGroupService {

	@Resource(name="companyGroupDao")
	private ICompanyGroupDao companyGroupDao;
	@Override
	protected IOperations<CompanyGroup> getDao() {
		return companyGroupDao;
	}
}
