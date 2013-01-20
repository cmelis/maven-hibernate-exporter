package hibernate;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;

public class HibernateUtil {

	private final String HIBERNATE_DIALECT_KEY = "hibernate.dialect";

	private String configurationPath;
	private Configuration configuration;
	private Properties dialect;

	public void setConfigurationPath(String configurationPath) {
		this.configurationPath = configurationPath;
	}

	public void setDialect(String dialect) {
		if (this.dialect == null) {
			this.dialect = new Properties();
		}

		if (this.dialect.size() > 0) {
			this.dialect.clear();
		}

		this.dialect.put(HIBERNATE_DIALECT_KEY, dialect);
	}

	public void createConfiguration() throws HibernateException {
		if (configuration != null) {
			return;
		}
		
		File confirationFile = new File(configurationPath);
		configuration = new Configuration();
		configuration.configure(confirationFile);

	}

	public String createTablesScript(List<String> entityNames)
			throws ClassNotFoundException {

		StringBuilder script = new StringBuilder();

		String[] creationScript = this.configuration
				.generateSchemaCreationScript(Dialect.getDialect(this.dialect));

		for (String string : creationScript) {
			script.append(string).append(";\n");
		}
		script.append("\ngo\n\n");

		return script.toString();
	}
}
