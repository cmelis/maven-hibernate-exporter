package maven;


import hibernate.HibernateUtil;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Mojo(name="helloworld", requiresProject=true, requiresDependencyResolution=ResolutionScope.COMPILE)
public class PluginExec extends AbstractMojo {

	@Component
	private MavenProject project;
	
	
	
	private HibernateUtil hibernateUtil;

	public PluginExec(){
		Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
		logger.info("Hello !!!!!");
		
		
		/*List runtimeClasspathElements;
		try {
			runtimeClasspathElements = project.getRuntimeClasspathElements();
			
			URL[] runtimeUrls = new URL[runtimeClasspathElements.size()];
			for (int i = 0; i < runtimeClasspathElements.size(); i++) {
			  String element = (String) runtimeClasspathElements.get(i);
			}
		} catch (DependencyResolutionRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		hibernateUtil = new HibernateUtil();
	}
	
	/**
     * Hibernate configuration path.
     *
     * @parameter default-value="undefined"
     */
    private String hibernateConfigurationPath;
	
	public void execute() throws MojoExecutionException, MojoFailureException {
			
		try{
			hibernateConfigurationPath = "C:\\Dropbox\\Data\\Programming\\Eclipse_WorkSpace\\git\\Quickstart\\Quickstart\\src\\main\\java\\hibernate.cfg.xml";
			hibernateConfigurationPathIsValid();
			
			hibernateUtil.setConfigurationPath(hibernateConfigurationPath);
			hibernateUtil.setDialect("org.hibernate.dialect.MySQLDialect");
			hibernateUtil.createConfiguration();
			
			String result = hibernateUtil.createTablesScript(null);
	
			getLog().info("Maven Plugin for Hibernate Export");
			
		}catch(IllegalArgumentException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void hibernateConfigurationPathIsValid(){
		if(hibernateConfigurationPath != null && !hibernateConfigurationPath.equals("undefined")){
			return;
		}
		
		throw new IllegalArgumentException("Hibernate configuration path must be specified!");
	}
	
}
