/**
 * 
 */
package org.erplab.action.mps;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.erplab.common.Constants;
import org.erplab.domain.Material;
import org.erplab.domain.TimeGrating;
import org.erplab.service.mps.MaterialService;
import org.erplab.service.mps.TimeGratingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.workin.core.persistence.support.PaginationSupport;
import org.workin.web.struts2.GenericActionSupport;

import com.google.common.collect.Lists;
/**
 * @author lawrence.wang
 *
 * 2011-4-6
 */
@Results({
	@Result(name = "reload", location = "timegrating.do", type = "redirectAction")})
public class TimegratingAction extends GenericActionSupport<TimeGrating>{

	private static final long serialVersionUID = -24023527432809790L;
	private TimeGrating timeGrating;
	private List<TimeGrating> timeGratingList;
	private List<Material> materialList = Lists.newArrayList();

	@Autowired
	private TimeGratingService timeGratingService;
	@Autowired
	private MaterialService materialService;
	
	private Long id;

	@Override
	public String list() throws Exception {
		PaginationSupport<TimeGrating> ps = timeGratingService.findByPagination(null,
				this.getStartIndexUsingId(Constants.DISPLAY_TABLE_ID), 10);
		timeGratingList = (List<TimeGrating>) ps.getResult();
		this.setPageResultSizeAttribute(ps.getTotalCount());
		return SUCCESS;
	}

	public String input() throws Exception {
		if(id != null){
			timeGrating = timeGratingService.findTimeGratingById(id);
			materialList.add(timeGrating.getMaterial());
		}
		return INPUT;
	}
	

	@Override
	public String remove() throws Exception {
		this.addActionMessage("Remove successfully!");
		timeGratingService.removeTimeGrating(id);
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		timeGrating = timeGratingService.saveTimeGrating(timeGrating);
		if(CollectionUtils.isEmpty(materialList)){
			materialList.add(timeGrating.getMaterial());
		}
		this.addActionMessage("Save or Update successfully!");
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		materialList = materialService.findMaterialEndByOne();
	}
	
	@Override
	public TimeGrating getModel() {
		return timeGrating;
	}
	public TimeGrating getTimeGrating() {
		return timeGrating;
	}

	public void setTimeGrating(TimeGrating timeGrating) {
		this.timeGrating = timeGrating;
	}


	public List<TimeGrating> getTimeGratingList() {
		return timeGratingList;
	}


	public void setTimeGratingList(List<TimeGrating> timeGratingList) {
		this.timeGratingList = timeGratingList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}
}
