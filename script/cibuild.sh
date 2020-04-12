#!/usr/bin/env bash

set -e
cd "$(dirname "$0")/.."

script/build.sh
script/push.sh
