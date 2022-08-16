podman start sonarqube
podman exec sonarqube rm -rf /opt/sonarqube/logs/sonar.log
podman exec sonarqube rm -rf /opt/sonarqube/logs/ce.log
podman exec sonarqube rm -rf /opt/sonarqube/logs/web.log
podman exec sonarqube rm -rf /opt/sonarqube/logs/es.log

podman stop sonarqube
podman cp ../target/omt-qualitygate-1.0-SNAPSHOT.jar sonarqube:/opt/sonarqube/extensions/plugins/omt-qualitygate-1.0-SNAPSHOT.jar
podman start sonarqube
