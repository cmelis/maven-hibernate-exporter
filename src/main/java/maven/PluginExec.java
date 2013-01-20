package maven;



import hibernate.HibernateUtil;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author Petr
 * @goal helloworld
 * @configurator include-project-dependencies
 * @requiresDependencyResolution compile
 * @requiresDependencyResolution compile+runtime
 */
public class PluginExec extends AbstractMojo {

	@Component
	private MavenProject project;
	
	/**
	    * Any String to print out.
	    * @parameter
	    *   expression="${helloworld.hibernateConfigurationPath}"
	    *   default-value="undefined"
	    */
	private String hibernateConfigurationPath;
	
	static final Logger LOG = LoggerFactory.getLogger(PluginExec.class);
	
	
	private HibernateUtil hibernateUtil;

	public PluginExec(){
		
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
	
    
	
	public void execute() throws MojoExecutionException, MojoFailureException {
		
		
			LOG.debug("LOL");
			LOG.info(hibernateConfigurationPath);
			getLog().info("HELLO 2 !!!");
		
		
		try{
			//hibernateConfigurationPath = "C:\\Dropbox\\Data\\Programming\\Eclipse_WorkSpace\\git\\Quickstart\\Quickstart\\src\\main\\java\\hibernate.cfg.xml";
			hibernateConfigurationPathIsValid();
			
			hibernateUtil.setConfigurationPath(hibernateConfigurationPath);
			hibernateUtil.setDialect("org.hibernate.dialect.MySQLDialect");
			hibernateUtil.createConfiguration();
			
			String result = hibernateUtil.createTablesScript(null);
			LOG.info(result);
	
			getLog().info("Maven Plugin for Hibernate Export");
			
		}catch(IllegalArgumentException e){
			e.printStackTrace();
			LOG.error(e.getMessage());
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
	}
	
	private void hibernateConfigurationPathIsValid(){
		if(hibernateConfigurationPath != null && !hibernateConfigurationPath.equals("undefined")){
			return;
		}
		
		throw new IllegalArgumentException("Hibernate configuration path must be specified!");
	}
	
}
