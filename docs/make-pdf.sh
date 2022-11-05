#!/bin/bash

set -euxo pipefail

mkdir -p release

bundle exec jekyll build
SHA="$(docker run --rm -dit -p 8080:80 -v "$(pwd)/_site":/usr/local/apache2/htdocs/ httpd:2.4)"

alias makepdf="docker run --network host --rm -v $(pwd):/data michaelperrin/prince --javascript"
makepdf -o /data/release/DeveloperGuide.pdf http://localhost:8080/DeveloperGuide.html
makepdf -o /data/release/UserGuide.pdf http://localhost:8080/UserGuide.html
unalias makepdf

docker stop "$SHA"
