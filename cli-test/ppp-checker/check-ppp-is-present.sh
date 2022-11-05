#!/bin/bash

set -euxo pipefail

# jq is preinstalled on GitHub runners

# Simplify data structure
cat data.json | jq ".repository.pullRequests.edges[].node" | jq "select(.headRepositoryOwner.login == \"$1\")" > data_simple.json

# Get merged PRs
cat data_simple.json | jq "select(.merged)" | jq ".title" > .titles.txt
cat data_simple.json | jq "select(.merged)" | jq ".number" > .numbers.txt
cat data_simple.json | jq "select(.merged)" | jq ".resourcePath" > .urls.txt

cp .numbers.txt numbers-expected.txt

# Format to markdown
sed -i '' 's/^"\(.*\)"$/* \1 /g' .titles.txt
sed -i '' 's/\(.*\)/([PR#\1]/g' .numbers.txt
sed -i '' 's?^"\(.*\)"$?(https://github.com\1))?g' .urls.txt
paste -d "\0" .titles.txt .numbers.txt .urls.txt > expected-message.md

# Get matching lines across all PPPs
# Don't fail entire script on no matches. See https://unix.stackexchange.com/a/581991
cat ../../docs/team/*.md | grep 'PR#[0-9]\+' | sed 's/^.*PR#\([0-9]\+\).*$/\1/g' | { grep -Fx "$(cat numbers-expected.txt)" || test $? = 1; } | sort --unique > numbers-actual.txt

# Check all lines present
if [ "$(cat numbers-expected.txt | wc -l)" != "$(cat numbers-actual.txt | wc -l)" ]; then
    echo "Error. Some PRs not found in any PPP:"
    # Show lines in expected-message.md that are not present in numbers-actual.txt
    echo "$(cat expected-message.md | grep -Fv "$(cat numbers-actual.txt | sed 's/\([0-9]\+\)/PR#\1/g')")"
    exit 1
fi
