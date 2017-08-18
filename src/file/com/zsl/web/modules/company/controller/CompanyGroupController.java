package file.com.zsl.web.modules.company.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsl.web.common.controller.BaseController;

import file.com.zsl.web.modules.company.service.ICompanyGroupService;
/**
 * 我的自动生成java代码
 * @author gq
 *
 */
@Controller("companyGroupController")
@RequestMapping({"/admin/companyGroup"})
public class CompanyGroupController extends BaseController {
	@Resource(name="companyGroupService")
	private ICompanyGroupService companyGroupService;
}
