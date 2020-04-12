#!/usr/bin/env bash

set -e
cd "$(dirname "$0")/.."

source script/env.sh
load_project_env

GIT_BRANCH=${GIT_BRANCH:-$(git rev-parse --abbrev-ref HEAD)}

printf "\nLocal build for $PROJECT_NAME. \n"
printf " --> git branch: $GIT_BRANCH\n\n"

printf "\nFormatting source and executing build... \n\n"
./gradlew formatKotlin build
