#!/bin/bash

for file in $(find ./ -name .DS_Store)
do
	echo $file
	rm -f $file
done

for file in $(find ./ -name ._*)
do
	echo $file
	rm -f $file
done