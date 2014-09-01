#!/bin/bash

wget http://algs4.cs.princeton.edu/linux/findbugs.zip
unzip checkstyle.zip
unzip findbugs.zip
wget http://algs4.cs.princeton.edu/linux/checkstyle.xml
wget http://algs4.cs.princeton.edu/linux/findbugs.xml
wget http://algs4.cs.princeton.edu/linux/checkstyle-algs4
wget http://algs4.cs.princeton.edu/linux/findbugs-algs4
chmod 700 checkstyle-algs4 findbugs-algs4
mv checkstyle-algs4 bin
mv findbugs-algs4 bin
mv checkstyle.xml checkstyle-5.5
mv findbugs.xml findbugs-2.0.1
