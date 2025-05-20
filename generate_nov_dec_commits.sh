#!/bin/bash

start_months=("11" "12") # November and December
for month in "${start_months[@]}"; do
  for day in {01..31}; do
    # Skip invalid days for each month
    if [[ "$month" == "11" && "$day" -gt 30 ]]; then
      continue
    fi
    if [[ "$month" == "12" && "$day" -gt 31 ]]; then
      continue
    fi

    # Base hour (e.g., 8:00 AM)
    base_hour=8

    for i in {1..6}; do
      # Generate random offset between 1h and 3h
      offset=$(( (RANDOM % 3) + 1 ))
      base_hour=$(( base_hour + offset ))

      # Format hour with leading zero if needed
      commit_hour=$(printf "%02d" $base_hour)

      # Generate datetime string
      commit_time="2024-$month-$day $commit_hour:00:00"

      # Set git environment variables
      export GIT_AUTHOR_DATE="$commit_time"
      export GIT_COMMITTER_DATE="$commit_time"

      # Create and commit
      echo "Dev log $i on 2024-$month-$day at $commit_hour:00" >> dev-log.txt
      git add dev-log.txt
      git commit -m "docs: log $i on 2024-$month-$day at $commit_hour:00"
    done
  done
done
