#!/usr/bin/env bash

set -e
cd "$(dirname "$0")/.."

source script/env.sh
load_project_env

source script/cleanup.sh

GIT_BRANCH=${GIT_BRANCH:-$(git rev-parse --abbrev-ref HEAD)}
GIT_COMMIT=${GIT_COMMIT:-$(git rev-parse HEAD)}

printf "\nBuilding $PROJECT_NAME \n"
printf " --> git branch: $GIT_BRANCH\n"
printf " --> git commit: $GIT_COMMIT\n\n"

docker build --no-cache --force-rm --tag=$PROJECT_NAME .

# Tag with commit ID and branch name
docker tag $PROJECT_NAME $PROJECT_NAME:$GIT_COMMIT
docker tag $PROJECT_NAME $PROJECT_NAME:latest

# Apply `master` tag to master branch builds
if [[ $GIT_BRANCH == *"master"* ]]; then
  docker tag $PROJECT_NAME:$GIT_COMMIT $PROJECT_NAME:master
fi
