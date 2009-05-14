/*
 * Copyright 2001-2009 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.juddi.portlets.client.service;

import java.util.List;

/**
 * 
 *  @author <a href="mailto:tcunning@apache.org">Tom Cunningham</a>
 *
 */
public class NotifyResponse extends Response {
	
	private static final long serialVersionUID = 1L;
	List<String> subscriptionNotifications;
	
	public List<String> getSubscriptionNotifications() {
		return subscriptionNotifications;
	}
	public void setBusinesses(List<String> subscriptionNotifications) {
		this.subscriptionNotifications = subscriptionNotifications;
	}

}