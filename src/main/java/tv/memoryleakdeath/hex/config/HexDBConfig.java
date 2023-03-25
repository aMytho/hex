package tv.memoryleakdeath.hex.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:hex.properties")
public class HexDBConfig {

	@Value("${databaseUsername}")
	private String databaseUsername;

	@Value("${databasePassword}")
	private String databasePassword;

	@Value("${databaseUrl}")
	private String databaseUrl;

	@Value("${databaseName}")
	private String databaseName;

	@Value("${databaseServer}")
	private String databaseServer;

	@Value("${databasePort}")
	private int databasePort;

	@Bean
	public DataSource getDataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
		// ds.setJdbcUrl(databaseUrl);
		ds.setUsername(databaseUsername);
		ds.setPassword(databasePassword);
		ds.addDataSourceProperty("applicationName", "hex");
		ds.addDataSourceProperty("databaseName", databaseName);
		ds.addDataSourceProperty("serverNames", new String[] { databaseServer });
		ds.addDataSourceProperty("portNumbers", new int[] { databasePort });
		return ds;
	}
}
