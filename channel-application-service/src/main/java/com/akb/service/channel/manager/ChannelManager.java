package com.akb.service.channel.manager;

import com.akb.model.application.common.ApplicationRequest;
import com.akb.model.application.common.ApplicationResponse;

public interface ChannelManager {

	ApplicationResponse saveApplication(ApplicationRequest req);
}
