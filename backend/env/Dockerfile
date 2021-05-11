FROM gradle:6.8.2-jdk15

WORKDIR /app

COPY entrypoint.sh /usr/local/bin/

RUN apt-get update \
    && apt-get install unzip  \
    &&cd /opt  \
    && curl -L https://github.com/JetBrains/kotlin/releases/download/v1.4.31/kotlin-compiler-1.4.31.zip --output kotlin-compiler.zip \
    && unzip kotlin-compiler.zip -d kotlin-compiler  \
    && cd kotlin-compiler \
    && PATH="$PATH:$PWD/bin" \
    && chmod 777 /usr/local/bin/entrypoint.sh \
    && ln -s /usr/local/bin/entrypoint.sh / \
    && mkdir /app/build \
    && chown gradle /app/build \
    && mkdir /app/.gradle \
    && chown gradle /app/.gradle

EXPOSE 8081

USER gradle

ENTRYPOINT ["entrypoint.sh"]
