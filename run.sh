#!/bin/bash

./gradlew clean installDist && \
./build/install/blockchain/bin/blockchain

