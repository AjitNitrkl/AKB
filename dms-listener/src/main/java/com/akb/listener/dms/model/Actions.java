package com.akb.listener.dms.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Actions {
	
	private List<DMSActions> dmsAction = new ArrayList<DMSActions>();
	private List<ListenerActions> listenerAction = new ArrayList<ListenerActions>();
	
	public void addDMSAction(DMSActions action) {
		dmsAction.add(action);
	}
	
	public void addListenerAction(ListenerActions action) {
		listenerAction.add(action);
	}

}
