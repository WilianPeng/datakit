package com.datakit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.datakit.protocol.server.NettyServer;

public class ServerBootstrap implements ApplicationListener<ContextRefreshedEvent>  {
	@Autowired 
	protected NettyServer nettyServer;
	
	public NettyServer getNettyServer() {
		return nettyServer;
	}

	public void setNettyServer(NettyServer nettyServer) {
		this.nettyServer = nettyServer;
	}

	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			try {
				nettyServer.bind();
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }
	}
	

}
