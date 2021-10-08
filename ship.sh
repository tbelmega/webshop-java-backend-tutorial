#!/bin/bash
set -e -o pipefail

# Pull from GitHub & Rebase, in case a team member has committed in the meantime
git pull -r

# Build and run tests
./gradlew clean build

# Push only if build & test was successful
git push origin master