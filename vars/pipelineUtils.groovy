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

def getIsDeployable(String env) {
  def allowedEnvs = ['prd', 'qa', 'dev']
  return allowedEnvs.contains(env)
}

def getMetadata(String branchName) {
  def projectEnv = getEnv(branchName)
  def projectNamespace = getNamespace(env)
  def projectIsDeployable = getIsDeployable(env)

  return [
    'PROJECT_ENV': projectEnv,
    'PROJECT_NAMESPACE': projectNamespace,
    'PROJECT_IS_DEPLOYABLE': projectIsDeployable,
  ]
}
