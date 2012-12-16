package maven;

import hibernate.HibernateUtil;

import java.io.*;
import java.util.List;
import java.util.Properties;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;

/**
 * @goal helloworld
 */
public class PluginExec extends AbstractMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
	
		HibernateUtil hibernateUtil = new HibernateUtil();
		hibernateUtil.setConfigurationPath("");
		hibernateUtil.setDialect("org.hibernate.dialect.MySQLDialect");
		hibernateUtil.createConfiguration();
		try {
			String result = hibernateUtil.createTablesScript(null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getLog().info("Maven Plugin for Hibernate Export");
	}
	
}
