#!/bin/bash

set -euxo pipefail

# jq is preinstalled on GitHub runners

# Simplify data structure
cat data.json | jq ".repository.pullRequests.edges[].node" | jq "select(.headRepositoryOwner.login == \"$1\")" > data_simple.json

# Get merged PRs
cat data_simple.json | jq "select(.merged)" | jq ".title" > .titles.txt
cat data_simple.json | jq "select(.merged)" | jq ".number" > .numbers.txt
cat data_simple.json | jq "select(.merged)" | jq ".resourcePath" > .urls.txt

# Get open PRs
# cat data_simple.json | jq "select(.closed | not)" | jq ".title" >> .titles.txt
# cat data_simple.json | jq "select(.closed | not)" | jq ".number" >> .numbers.txt
# cat data_simple.json | jq "select(.closed | not)" | jq ".resourcePath" >> .urls.txt

# Format to markdown
sed -i 's/^"\(.*\)"$/* \1 /g' .titles.txt
sed -i 's/\(.*\)/([PR#\1]/g' .numbers.txt
sed -i 's?^"\(.*\)"$?(https://github.com\1))?g' .urls.txt
paste -d "\0" .titles.txt .numbers.txt .urls.txt > expected.md

# Get matching lines across all PPPs
# Don't fail entire script on no matches. See https://unix.stackexchange.com/a/581991
cat ../docs/team/*.md | sed 's/^ *\(.*\) *$/\1/g' | { grep -Fx "$(cat expected.md)" || test $? = 1; } | sort --unique > actual.md

# Check all lines present
if [ "$(cat expected.md | wc -l)" != "$(cat actual.md | wc -l)" ]; then
    echo "Error. Some PRs not found in any PPP:"
    # Show lines in expected.md that are not present in actual.md
    echo "$(cat expected.md | grep -Fxv "$(cat actual.md)")"
    exit 1
fi
