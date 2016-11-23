#!/bin/bash
name="$(date +%F_%T)"
extension=".tar"

file="$name$extension"
echo $file

find . -mtime -1 | xargs tar -czf $file
