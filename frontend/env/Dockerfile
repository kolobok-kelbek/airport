FROM node:14

WORKDIR /app

COPY entrypoint.sh /usr/local/bin/

RUN chmod 777 /usr/local/bin/entrypoint.sh \
    && ln -s /usr/local/bin/entrypoint.sh / \
    && mkdir /app/node_modules \
    && chown 1000:1000 /app/node_modules \
    && mkdir /app/.next \
    && chown 1000:1000 /app/.next

EXPOSE 3000

ENTRYPOINT ["entrypoint.sh"]

CMD ["npm", "run", "dev"]
