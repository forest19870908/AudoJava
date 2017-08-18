

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.zsl.web.common.dao.IOperations;
import com.zsl.web.common.service.AbstractService;
@Repository(value="modelService")
public class ModelServiceImpl extends AbstractService<Model> implements
		IModelService {

	@Resource(name="modelDao")
	private IModelDao modelDao;
	@Override
	protected IOperations<Model> getDao() {
		return modelDao;
	}
}
