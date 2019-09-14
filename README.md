###IMPORTANT NOTE
After downloading the project, please place it in the root of the file system of your environment
/Users/"Your OS account or profile"
Windows OS users will have to set the path to project chromedriver or geckodriver on their own as well as downloading
the driver binaries on their own as this setup currently automatically supports Unix like environment.

### Dependencies to be installed
1. Java Oracle JDK 11.4
2. TestNG add on for eclipse (for configuration of test runs)
3. InteliJ IDEA (for installation of all project dependencies) or Eclipse

#### SETUP

##### Requirements test:

Test that Java is installed by typing the following into the terminal, which returns the installed version/s of Java:

- Ensure `JAVA_HOME` environment variable is set and points to your JDK installation
You can check this by typing in terminal
  java --version
On MacOS jdk location is:
/Library/Java/JavaVirtualMachines/jdk-11.0.4.jdk/Contents/Home/bin/

##### Maven installation 

[Here](https://maven.apache.org/download.cgi#Installation) you can download the binary archive needed for Maven. 

The installation of Apache Maven is a simple process of extracting the archive and adding the `bin` folder with the `mvn` command to the `PATH`.

Detailed steps are:

- Extract distribution archive in any directory

  
`tar xzvf apache-maven-3.5.3-bin.tar.gz`

- Add the `bin` directory of the created directory `apache-maven-3.5.3` to the `PATH` environment variable

- Confirm with `mvn -v` in a new shell. 

- Check environment variable value

`echo $JAVA_HOME`

`/Library/Java/JavaVirtualMachines/jdk11.0.4.jdk/Contents/Home`


- Adding to `PATH`


`export PATH=/opt/apache-maven-3.5.3/bin:$PATH`


##### Maven instalation via homebrew

Type the following commands in the CLI
`brew update`
`brew install maven`

To check if the installation went well try:
`mvn -version`

You should see a confirmation of a succesfull maven installation in your CLI.
Restart the CLI and check again. If maven commands are recognized you are good to go.

Clone this repository somewhere on your local machine and pull the code so that you have a home folder where project resides

Now you can open your EclipseIDE and go to "File > Import > Maven > Existing Maven Projects > [source folder]"

Connect the project to EclipseIDE in the project folder from the command line by hitting:

`mvn eclipse:eclipse`

You should see "BUILD SUCCESS" here.

Right click on the project in the IDE  and select "Maven > Update project"
Right click on the project again and select "Run as > Maven build" 
In the goals enter: "mvn clean install" and check "Skip Tests" checkbox then hit "Apply" and "Run"
You should see "BUILD SUCCESS" again in the console.

Install TESTNG from the IDE following these steps: Help > Install New Software > "TestNG Eclipse - http://beust.com/eclipse"
When this is done, restart Eclipse IDE and check if TestNG is present. Try to run any test by right-clicking on it and selecting "Run as TestNG test"
If this is succesfull then good luck running and creating tests! 



##### Configuring the dependencies in the POM

If you have successfully completed the steps above you will see pom.xml in your project when you open EclipseIDE. 

The `pom.xml` file is the core of a project's configuration in Maven. It is a single configuration file that contains the majority of information required to build a project in just the way you want. The POM is huge and can be daunting in its complexity, but it is not necessary to understand all of the intricacies just yet to use it effectively.  To add dependencies you can go and search online for them here: https://mvnrepository.com/

Mostly used commands in the Maven:

`mvn compile`

`mvn clean`

`mvn install`

`mvn test`


By running the following  command in your CLI you will run the specific test:
`mvn test -Dtest=classname` 
