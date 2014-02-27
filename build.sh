#!/bin/bash

##
# Build UML2PHP Generator as a jar file.
#

echo "Init classes folder" 
if [ -e classes ]; then
    rm -rf classes
fi
mkdir classes 

echo "Starting Compilation of Java sources"
javac \
    -sourcepath ./src \
    -classpath ./classes \
    -extdirs ./lib \
    src/umltophp/main/*.java \
    src/mtlcompiler/*.java \
    -d ./classes
if [ ! $? -eq 0 ]; then
    exit -1
fi

echo "Copying mtl into classes folder"
cp ./src/umltophp/main/*.mtl ./classes/umltophp/main/ 
cp ./src/umltophp/main/umltophp.properties ./classes/umltophp/main/ \

echo "Starting compilation of Mtl sources"
java \
    -classpath ./lib/*:classes: \
    mtlcompiler.MtlCompiler \
    "/home/tiben/Code/sources/java/uml-to-php" \
    "/home/tiben/Code/sources/java/uml-to-php/src" \
    "/home/tiben/Code/sources/java/uml-to-php/classes"
if [ ! $? -eq 0 ]; then
    exit -1
fi

echo "Compacting into jar file"
cd classes/ 
jar cfm ../umltophp.jar ../MANIFEST.MF ./ \

echo "Deleting classes folder"
rm -rf classes 

echo "Build finished"
