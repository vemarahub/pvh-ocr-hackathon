package com.pvh.ocrapi.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

@Setter
@Getter
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class DocumentDBConfiguration extends AbstractMongoClientConfiguration {

	private static final String CLASSNAME = DocumentDBConfiguration.class.getName();
	private static final Logger LOGGER = Logger.getLogger(CLASSNAME);

	private String dbUserName;
	private String dbPassword;
	private String host;
	private String username;
	private String database;
	private int port;
	private String dbName;

	@Autowired
	private Environment env;

	/**
	 * Gets documentDB connection URI
	 * @return The documentDB connection URI
	 */
	public String getConnectionString() {
		final var METHODNAME = "getConnectionString";
		LOGGER.entering(CLASSNAME, METHODNAME);
		var sb = new StringBuilder();
		sb.append("mongodb://");
		Optional<String> prodProfile = Arrays.stream(env.getActiveProfiles()).filter(f -> !f.equalsIgnoreCase("default")).findAny();
		if(prodProfile.isPresent()) {
			sb.append(getDBUsername(dbUserName));
			sb.append(":");
			sb.append(getDBPasswordByPath(dbPassword));
			sb.append("@");
		}
		sb.append(getHost());
		sb.append(":");
		sb.append(getPort());
		sb.append("/");
		sb.append(getDbName());
		sb.append("?retrywrites=false");
		LOGGER.exiting(CLASSNAME, METHODNAME);
		return sb.toString();
	}

	/**
	 * Gets documentDB username path
	 * @param dbUsernamePath The username SSM path
	 * @return The username SSM path
	 */
	private String getDBUsername(String dbUsernamePath) {
		final var METHODNAME = "getDBUsername";
		LOGGER.entering(CLASSNAME, METHODNAME);
		LOGGER.exiting(CLASSNAME, METHODNAME);
		return "user";
	}

	/**
	 * Gets documentDB password path
	 * @param dbPasswordPath The password SSM path
	 * @return The password SSM path
	 */
	protected String getDBPasswordByPath(String dbPasswordPath) {
		final var METHODNAME = "getDBPasswordByPath";
		LOGGER.entering(CLASSNAME, METHODNAME);
		LOGGER.exiting(CLASSNAME, METHODNAME);
		return "test";
	}

	/**
	 * Gets mongo client
	 * @return The mongo client
	 */
	@Bean
	public MongoClient mongoClient() {
		final var METHODNAME = "mongoClient";
		LOGGER.entering(CLASSNAME, METHODNAME);
		LOGGER.exiting(CLASSNAME, METHODNAME);
		return MongoClients.create(getConnectionString());
	}

	/**
	 * Gets mongo template
	 * @return The mongo template
	 */
	@Bean
	public MongoTemplate mongoTemplate() {
		final var METHODNAME = "mongoTemplate";
		LOGGER.entering(CLASSNAME, METHODNAME);
		LOGGER.exiting(CLASSNAME, METHODNAME);
		return new MongoTemplate(mongoClient(), database);
	}

	@Override
	protected String getDatabaseName() {
		return this.dbName;
	}

	@Override
	protected boolean autoIndexCreation() {
		return true;
	}
}
