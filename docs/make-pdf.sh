#!/bin/bash

set -euxo pipefail

mkdir -p release

bundle exec jekyll build
SHA="$(docker run --rm -dit -p 8080:80 -v "$(pwd)/_site":/usr/local/apache2/htdocs/ httpd:2.4)"

docker run --network host --rm -v $(pwd):/data michaelperrin/prince --javascript -o /data/release/DeveloperGuide.pdf http://localhost:8080/DeveloperGuide.html
docker run --network host --rm -v $(pwd):/data michaelperrin/prince --javascript -o /data/release/UserGuide.pdf http://localhost:8080/UserGuide.html

docker stop "$SHA"
