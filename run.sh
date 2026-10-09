#!/usr/bin/env bash
# Сборка и запуск демонстрации: ./run.sh   (тесты: ./run.sh test)
set -e
cd "$(dirname "$0")"
rm -rf out && mkdir -p out
javac -encoding UTF-8 -d out $(find src test -name "*.java")
if [ "$1" = "test" ]; then
  java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out MemeTests
else
  java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out memes.Main
fi
