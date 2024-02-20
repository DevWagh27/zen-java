# Account Info

# We need access to EAP or wildfly

<deploy.jboss.host>127.0.0.1</deploy.jboss.host>
<deploy.jboss.port>19990</deploy.jboss.port>
<deploy.jboss.username>jbossadmin</deploy.jboss.username>
<deploy.jboss.password>jbossadmin</deploy.jboss.password>

# Clean package install (or deploy to JBoss EAP)
Navigate to pom.xml directory and execute below command 
Pre-requisite is to have the Jboss up and running 
Note this will take considerable time to deploy. 
As an alternative, use the next mvn command 

mvn clean install


# Clean package (or generate war file )
Navigate to pom.xml directory and execute below command 
This command will generate a warfile and place it under 'target' folder. 
Use the war file and copy to JBoss Webconsole via UI (Drag and Drop)

mvn clean package

 
