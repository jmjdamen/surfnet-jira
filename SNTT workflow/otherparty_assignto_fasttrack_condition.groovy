String service = cfValues['Other Party Service']
def result = service.contains('cumulus')

// invert result so if not cumulus then false, so fast transition happens
return !result
