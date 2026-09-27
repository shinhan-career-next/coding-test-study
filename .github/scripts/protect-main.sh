#!/usr/bin/env bash

set -euo pipefail

if [[ $# -ne 1 ]]; then
  echo "Usage: $0 OWNER/REPOSITORY" >&2
  exit 1
fi

repository="$1"

if [[ ! "$repository" =~ ^[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+$ ]]; then
  echo "Error: repository must use the OWNER/REPOSITORY format." >&2
  exit 1
fi

command -v gh >/dev/null 2>&1 || {
  echo "Error: GitHub CLI (gh) is required." >&2
  exit 1
}

gh auth status >/dev/null

gh api \
  --method PUT \
  -H "Accept: application/vnd.github+json" \
  -H "X-GitHub-Api-Version: 2022-11-28" \
  "/repos/${repository}/branches/main/protection" \
  --input - <<'JSON'
{
  "required_status_checks": null,
  "enforce_admins": true,
  "required_pull_request_reviews": {
    "dismiss_stale_reviews": false,
    "require_code_owner_reviews": false,
    "required_approving_review_count": 0,
    "require_last_push_approval": false
  },
  "restrictions": null,
  "required_conversation_resolution": true,
  "required_linear_history": false,
  "allow_force_pushes": false,
  "allow_deletions": false,
  "block_creations": false,
  "lock_branch": false,
  "allow_fork_syncing": true
}
JSON

echo "Protected ${repository}:main (PR required, force pushes and deletion disabled)."
