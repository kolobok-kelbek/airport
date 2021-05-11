#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  IF NOT EXISTS (
    SELECT FROM pg_catalog.pg_roles  -- SELECT list can be empty for this
    WHERE  rolname = 'airuser') THEN

    CREATE ROLE airuser LOGIN PASSWORD 'airuser';
  END IF;

  CREATE DATABASE airport;
  GRANT ALL PRIVILEGES ON DATABASE airport TO airuser;
EOSQL