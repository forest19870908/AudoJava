import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * note
 * @author gq
 *
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "table_name")
@EntityModifyLog({"update","delete"})
public class Model implements Serializable {
	public Model(){
		
	}
}