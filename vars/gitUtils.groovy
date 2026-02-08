def getBranch() {
  def branch = ""

  if (env.BRANCH_NAME) {
    branch = env.BRANCH_NAME
  } else if (env.GIT_BRANCH) {
    branch = env.GIT_BRANCH
  } else {
    try {
      branch = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
    } catch (Exception e) {
      echo "Warning: Could not determine git branch via command. Details: ${e.getMessage()}"
      return "unknown"
    }
  }

  return branch.replace('origin/', '')
}

def getShortCommit() {
  try {
    return sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
  } catch (Exception e) {
    echo "⚠️ Warning: Could not determine git commit. Details: ${e.getMessage()}"
    return "unknown"
  }
}
