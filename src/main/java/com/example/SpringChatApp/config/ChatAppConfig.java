package com.example.SpringChatApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration	//To indicate that it is configuration file
@EnableWebSocketMessageBroker //To enable web socket server
public class ChatAppConfig implements WebSocketMessageBrokerConfigurer{	//To overwrite WebSocketMessageBrokerConfigurer methods
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {	//To provide stomp endpoints
		registry.addEndpoint("/joinchat").withSockJS();	//In example 'javatechie' is used
		//'addEndPoint' will add an url which client call call and 'withSockJs' is socketJs support which will help in establishing http connection in case websocket don't work.So that communication between client and server continues
		//Default configuration provided by spring - WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {	//To enable configurer message broker
		registry.setApplicationDestinationPrefixes("/app");
		// registry.enableSimpleBroker("/topic"); //Enable it if you don't want to use RabbitMq and to use default message-broker
		registry.enableStompBrokerRelay("/topic")
        .setRelayHost("localhost")
        .setRelayPort(61613)
        .setClientLogin("admin")
        .setClientPasscode("prty@rty34p0");
		//Default configuration provided by spring - WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
	}	
}
