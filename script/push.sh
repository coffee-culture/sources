#!/usr/bin/env bash

set -e
cd "$(dirname "$0")/.."

source script/env.sh
load_project_env

GIT_BRANCH=${GIT_BRANCH:-$(git rev-parse --abbrev-ref HEAD)}
GIT_COMMIT=${GIT_COMMIT:-$(git rev-parse HEAD)}

printf "\nPushing $PROJECT_NAME to DockerHub \n"
printf " --> git branch: $GIT_BRANCH\n"
printf " --> git commit: $GIT_COMMIT\n\n"

# DockerHub credentials required to push image
[[ "${DOCKERHUB_USERNAME:-}" ]] || (echo "DOCKERHUB_USERNAME is required." && exit 1)
[[ "${DOCKERHUB_PASSWORD:-}" ]] || (echo "DOCKERHUB_PASSWORD is required." && exit 1)

# Log in to DockerHub
echo "$DOCKERHUB_PASSWORD" | docker login --username=$DOCKERHUB_USERNAME --password-stdin

docker push $PROJECT_NAME:$GIT_COMMIT
docker push $PROJECT_NAME:latest

if [[ $GIT_BRANCH == *"master"* ]]; then
  printf "\nPushing $PROJECT_NAME:master to DockerHub \n\n"
  docker push $PROJECT_NAME:master
fi
