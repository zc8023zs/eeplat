package com.exedosoft.plat.action.wf;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.wfi.NodeInstance;

public class DOBackTaskNoSave extends DOAbstractAction {

	private static final long serialVersionUID = -7288193881377838284L;

	public String excute() {
		DOBO ptNI = DOBO.getDOBOByName("do_wfi_nodeinstance");
		if (ptNI.getCorrInstance() == null) {
			this.setEchoValue("当前工作流上下文丢失,请重新操作!");
			return null;
		}
		NodeInstance ni = NodeInstance.getNodeInstanceByID(ptNI
				.getCorrInstance().getUid());
		WFUtil.refreshWFPara(ni.getProcessInstance());
		try {
			ni.returnBack();
		} catch (WFException e) {
			this.setEchoValue(e.getMessage());
			return NO_FORWARD;
		}
		return DEFAULT_FORWARD;
	}

}
