package file.com.zsl.web.modules.company.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 我的自动生成java代码
 * @author gq
 *
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "z_company_group")
public class CompanyGroup implements Serializable {
	public CompanyGroup(){
		
	}
}
