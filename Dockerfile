FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
ENV DISCORD_CLIENT_ID
ENV DISCORD_CLIENT_SECRET
ENV DISCORD_BOT_TOKEN
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp","app:app/lib/*", "-DDISCORD_CLIENT_ID=${DISCORD_CLIENT_ID}", "-DDISCORD_CLIENT_SECRET=${DISCORD_CLIENT_SECRET}", "-DDISCORD_BOT_TOKEN=${DISCORD_BOT_TOKEN}", "com.github.edpilots.quest.QuestServerApplication"]