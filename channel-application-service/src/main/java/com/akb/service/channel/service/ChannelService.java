package com.akb.service.channel.service;

import java.util.function.Consumer;

import com.akb.model.application.common.ApplicationRequest;
import com.akb.model.application.common.ApplicationResponse;

public interface ChannelService {

	 ApplicationResponse saveApplication(ApplicationRequest req, Consumer<String> notifyNewApp);
}
