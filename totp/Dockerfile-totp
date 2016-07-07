# Dockerfile - totp service
FROM java:8-jre

COPY libs/totp-all.jar /totp-all.jar

EXPOSE 8080 8080

ENTRYPOINT ["java", "-jar", "/totp-all.jar"]
CMD []
