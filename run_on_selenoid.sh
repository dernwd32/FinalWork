#!/bin/bash

REMOTE_URL=""
BROWSER_NAME=""
BROWSER_VERSION=""

while [[ $# -gt 0 ]];
do
	case $1 in
		--remote.url)
			REMOTE_URL=$2
			shift 2
			;;
		--browser.name)
			BROWSER_NAME=$2
			shift 2
			;;
		--browser.version)
			BROWSER_VERSION=$2
			shift 2
			;;
		*)
			echo "Unknown argument: $1 with value $2"
			exit 13
			;;
	esac
done

echo "Running UI tests on selenoid via maven"
echo "~~ Selenoid URL: $REMOTE_URL"
echo "~~ Chosen browser: $BROWSER_NAME ~ $BROWSER_VERSION"
mvn clean test -Dremote.url=$REMOTE_URL -Dbrowser.name=$BROWSER_NAME -Dbrowser.version=BROWSER_VERSION

sleep 30

