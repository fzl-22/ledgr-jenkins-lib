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
  def env = getEnv(branchName)
  def namespace = getNamespace(env)
  def isDeployable = getIsDeployable(env)

  return [
    'PROJECT_ENV': env,
    'PROJECT_NAMESPACE': namespace,
    'PROJECT_IS_DEPLOYABLE': isDeployable,
  ]
}
