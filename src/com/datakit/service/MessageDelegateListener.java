package com.datakit.service;

import java.io.Serializable;

public interface MessageDelegateListener {
	public void handleMessage(Serializable message) ;
}
