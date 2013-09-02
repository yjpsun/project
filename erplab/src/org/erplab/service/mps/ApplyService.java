/**
 * 
 */
package org.erplab.service.mps;

import java.util.List;

import org.erplab.domain.Apply;
import org.erplab.domain.Material;
import org.erplab.domain.PurchaseOrder;
import org.workin.core.persistence.support.PaginationSupport;

/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
public interface ApplyService {
	
	public Apply saveApply(Apply apply);

	public void removeApply(Long id);
	
	public PaginationSupport<Apply> findByPagination(Apply apply,int startIndex,
			int pageSize);
	
	public Apply findApplyById(Long id);
	
	public List<Apply> findApplySum(Material mat);
	
	public List<PurchaseOrder> saveGenerateOrder(List<Apply> applyList,Material mat);
}
