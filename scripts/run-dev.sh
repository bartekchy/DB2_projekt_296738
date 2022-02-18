#!/bin/bash

# Konfiguracja bazy danych
export MONGO_NAME=db-hotel-dev
export MONGO_PORT=27017
export MONGO_INITDB_ROOT_USERNAME=user
export MONGO_INITDB_ROOT_PASSWORD=pass
export MONGO_INITDB_DATABASE=hotel

# Usunięcie poprzedniej bazy danych
docker rm -f "$MONGO_NAME"

# Uruchomienie kontenera z bazą danych
docker run \
    --rm -d \
    --name "$MONGO_NAME" \
    -e MONGO_INITDB_ROOT_USERNAME="$MONGO_INITDB_ROOT_USERNAME" \
    -e MONGO_INITDB_ROOT_PASSWORD="$MONGO_INITDB_ROOT_PASSWORD" \
    -e MONGO_INITDB_DATABASE="$MONGO_INITDB_DATABASE" \
    -p "127.0.0.1:$MONGO_PORT:27017" \
    mongo:latest