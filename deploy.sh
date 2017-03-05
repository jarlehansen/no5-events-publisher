#!/usr/bin/env bash
./gradlew
heroku jar:deploy no5-events-publisher-api/build/libs/*.jar --app no5-events-publisher
