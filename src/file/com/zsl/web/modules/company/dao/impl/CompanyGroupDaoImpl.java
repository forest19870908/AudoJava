package file.com.zsl.web.modules.company.dao.impl;
import org.springframework.stereotype.Repository;

import com.zsl.web.common.dao.BaseDao;

import file.com.zsl.web.modules.company.dao.ICompanyGroupDao;
import file.com.zsl.web.modules.company.model.CompanyGroup;
@Repository(value="companyGroupDao")
public class CompanyGroupDaoImpl extends BaseDao<CompanyGroup> implements ICompanyGroupDao {

}
