package com.deegeu.utilities.git;

import java.io.IOException;
import java.util.Properties;

/**
 * Since the repository utilities are meant to be a library, this class's only
 * purpose is to return the version information for the file.
 *
 */
public class RepositoryUtils {
    
    private GitRepositoryState gitRepositoryState;
    
    /***
     * Main application that returns the version information.
     * @param args 
     */
    public static void main( String[] args ) {
        
        RepositoryUtils app = new RepositoryUtils();
        try {
            app.getGitRepositoryState();
        } catch (IOException ioe) {
            System.out.println("Could not get repository state : " + ioe);
            System.exit(-1);
        }
        
        System.out.println( "RepositoryUtils version " 
                +  app.gitRepositoryState.getBuildVersion()
                + " Git SHA "+ app.gitRepositoryState.getCommitIdAbbrev());
    }
    
    /***
     * Gets the repository state for this artifact.
     * @return
     * @throws IOException 
     */
    private GitRepositoryState getGitRepositoryState() throws IOException {
       if (this.gitRepositoryState == null) {
          Properties properties = new Properties();
          properties.load(getClass().getClassLoader().getResourceAsStream("git.properties"));
          this.gitRepositoryState = new GitRepositoryState(properties);
       }
       return this.gitRepositoryState;
    }
}
