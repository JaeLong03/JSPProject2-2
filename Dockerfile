FROM tomcat:9.0

ADD target/Project2-2-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]