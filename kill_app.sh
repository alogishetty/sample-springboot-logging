#!/bin/bash

while [ true ]
do
	curl -k --request POST 'https://monitoring-demo-wgo.apps.nonprod.mem.cloud.autozone.com/actuator/chaosmonkey/assaults' --header 'Content-Type: application/json' --data-raw '{ "level": 5, "latencyRangeStart": 2000, "latencyRangeEnd": 5000, "latencyActive": true, "exceptionsActive": true, "killApplicationActive": true, "runtimeAssaultCronExpression": "* * * * * *" }'
done
