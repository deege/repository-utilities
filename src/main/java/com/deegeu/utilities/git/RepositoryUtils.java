package com.deegeu.utilities.git;

import java.io.IOException;
import java.util.Properties;

/**
 * Since the repository utilities are meant to be a library, this class's only
 * purpose is to return the version information for the file.
 *
 */
public class RepositoryUtils {
    
    static private GitRepositoryState gitRepositoryState;
    
    /***
     * Main application that returns the version information.
     * @param args 
     */
    public static void main( String[] args ) {
        try {
            getGitRepositoryState();
        } catch (IOException ioe) {
            System.out.println("Could not get repository state : " + ioe);
            System.exit(-1);
        }
        
        System.out.println( "RepositoryUtils version " 
                +  gitRepositoryState.getBuildVersion()
                + " Git SHA "+ gitRepositoryState.getCommitIdAbbrev());
    }
    
    /***
     * Gets the repository state for this artifact.
     * @return
     * @throws IOException 
     */
    static public GitRepositoryState getGitRepositoryState() throws IOException {
       if (RepositoryUtils.gitRepositoryState == null) {
          Properties properties = new Properties();
          properties.load(RepositoryUtils.class.getClassLoader().getResourceAsStream("git.properties"));
          RepositoryUtils.gitRepositoryState = new GitRepositoryState(properties);
       }
       return RepositoryUtils.gitRepositoryState;
    }
}
