

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsl.web.common.controller.BaseController;
/**
 * note
 * @author gq
 *
 */
@Controller("modelController")
@RequestMapping({"/admin/model"})
public class ModelController extends BaseController {
	@Resource(name="modelService")
	private IModelService modelService;
}
