package com.akb.listener.email.dbservice;

import com.akb.dao.entity.ApplicationEvent;

public interface EmailService {

	void saveEmailEvent(ApplicationEvent event);
}
