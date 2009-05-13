/*
 * Copyright 2001-2008 The Apache Software Foundation.
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

package org.apache.juddi.query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.config.AppConfig;
import org.apache.log4j.Logger;

public class PersistenceManager {
	private static Logger log = Logger.getLogger(PersistenceManager.class);
	
	public static final String PERSISTENCE_UNIT_NAME = "juddiDatabase";

	private static EntityManagerFactory emf;
	
	// Now, the factory will be initialized in the config. This will force the config to initialize, thereby initializing emf.
	static {
		try {
			AppConfig.getInstance();
		} 
		catch (ConfigurationException e) {
			log.error("Error initializing config in PersistenceManager", e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void closeEntityManager() {
		if (emf.isOpen())
			emf.close();
	}
	
	public static void initializeEntityManagerFactory(String persistenceUnitName) {
		try {
			if (emf == null)
				emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		catch (Throwable t) {
			log.error("entityManagerFactory creation failed", t);
			throw new ExceptionInInitializerError(t);
		}
		
	}
}
