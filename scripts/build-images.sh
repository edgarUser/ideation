#!/bin/bash

year=$(date +'%Y')
quarter=$(((($(date +'%m') - 1) / 3) + 1))
incremental=$(head -n 1 ./scripts/incremental.txt)
version="${year}.${quarter}.${incremental}"
./gradlew -PprojVersion="${version}" jibDockerBuild
echo $((incremental + 1)) > ./scripts/incremental.txt
