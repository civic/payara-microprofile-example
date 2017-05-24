#!/bin/bash

mvn exec:java \
    -Dexec.mainClass=org.h2.tools.RunScript \
    -Dexec.args="-url jdbc:h2:~/h2db/test -script db.sql"
