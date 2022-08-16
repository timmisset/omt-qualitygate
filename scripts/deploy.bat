docker start sonarqube
docker exec sonarqube rm -rf /opt/sonarqube/logs/sonar.log
docker exec sonarqube rm -rf /opt/sonarqube/logs/ce.log
docker exec sonarqube rm -rf /opt/sonarqube/logs/web.log
docker exec sonarqube rm -rf /opt/sonarqube/logs/es.log

docker stop sonarqube
docker cp ..\target\omt-qualitygate-1.0-SNAPSHOT.jar sonarqube:/opt/sonarqube/extensions/plugins/omt-qualitygate-1.0-SNAPSHOT.jar
docker start sonarqube
