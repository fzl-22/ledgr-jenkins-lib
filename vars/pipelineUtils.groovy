def getEnv(String branchName) {
  echo "Checking deployment eligibility for branch: '${branchName}'"

  switch (branchName) {
    case ['master', 'main']:
      return 'prd'
    case ['staging', 'qa']:
      return 'qa'
    case ['development', 'develop']:
      return 'dev'
    default:
      return ""
  }
}

def getNamespace(String env) {
  if (!env) {
    return ""
  }

  return "ledgr-${env}"
}
