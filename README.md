# Repository Utilities

The purpose of this repository is to provide a library to report Git Repository information in the artifact.

## Building the code

The mvn POM file requires two parameters passed.

'''mvn clean install -Dproject.version=1.0.0 -Dbuild.number=$BUILD_NUMBER.$GIT_COMMIT
'''

## Getting started

Add the most recent JAR to your project. To load the properties, use the following code.

'''/***
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
    '''

This code assumes [the git-commit-id-plugin](https://github.com/ktoso/maven-git-commit-id-plugin) is added to your Maven POM.    

The plugin is configured as follows:

'''<plugin>
      <groupId>pl.project13.maven</groupId>
      <artifactId>git-commit-id-plugin</artifactId>
      <version>2.2.1</version>
      <executions>
          <execution>
              <phase>validate</phase>
              <goals>
                  <goal>revision</goal>
              </goals>
          </execution>
      </executions>

      <configuration>
          <!-- <dotGitDirectory>${project.basedir}/.git</dotGitDirectory> -->
          <prefix>git</prefix>
          <dateFormat>dd.MM.yyyy '@' HH:mm:ss z</dateFormat>
          <dateFormatTimeZone>${user.timezone}</dateFormatTimeZone>
          <verbose>true</verbose>
          <generateGitPropertiesFile>true</generateGitPropertiesFile>
          <generateGitPropertiesFilename>${project.build.outputDirectory}/git.properties</generateGitPropertiesFilename>
          <format>properties</format>
          <skipPoms>true</skipPoms>
          <injectAllReactorProjects>true</injectAllReactorProjects>
          <failOnNoGitDirectory>true</failOnNoGitDirectory>
          <failOnUnableToExtractRepoInfo>true</failOnUnableToExtractRepoInfo>
          <skip>false</skip>
          <runOnlyOnce>false</runOnlyOnce>
          <useNativeGit>false</useNativeGit>
          <abbrevLength>7</abbrevLength>
          <commitIdGenerationMode>flat</commitIdGenerationMode>
          <gitDescribe>
              <skip>false</skip>
              <always>false</always>
              <abbrev>7</abbrev>
              <dirty>-dirty</dirty>
              <match>*</match>
              <forceLongFormat>false</forceLongFormat>
          </gitDescribe>
      </configuration>
  </plugin>
  '''

## Contributions

If you wish to contribute to any issues you find in the source code, please issue a pull request.
