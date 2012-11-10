package main;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public class PluginExec extends AbstractMojo{

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Maven Plugin for Hibernate Export");
		
	}

}
