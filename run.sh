#!/usr/bin/bash

./gradlew clean installDist && \
bash build/install/blockchain/bin/blockchain

