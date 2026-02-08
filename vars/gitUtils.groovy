def getBranchName() {
  def branchName = ""

  if (env.BRANCH_NAME) {
    branchName = env.BRANCH_NAME
  } else if (env.GIT_BRANCH) {
    branchName = env.GIT_BRANCH
  } else {
    try {
      branchName = sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
    } catch (Exception e) {
      echo "Warning: Could not determine git branch name via command. Details: ${e.getMessage()}"
      return "unknown"
    }
  }

  return branchName.replace('origin/', '')
}

def getShortCommitID() {
  try {
    return sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
  } catch (Exception e) {
    echo "Warning: Could not determine short git commit id. Details: ${e.getMessage()}"
    return "unknown"
  }
}

def getFullCommitID() {
  try {
    return sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
  } catch (Exception e) {
    echo "Warning: Could not determine full git commit id. Details: ${e.getMessage()}"
    return "unknown"
  }
}

def getMetadata() {
  def branchName = getBranchName()
  def shortCommitID = getShortCommitID()
  def fullCommitID = getFullCommitID()

  return [
    'GIT_BRANCH_NAME': branchName,
    'GIT_SHORT_COMMIT_ID': shortCommitID,
    'GIT_FULL_COMMIT_ID': fullCommitID,
  ]
}
