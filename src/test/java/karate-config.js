function fn() {    
  var env = karate.env; // get system property 'karate.env'
    if (!env) {
      env = 'dev';
    }
  karate.log('karate.env system property was:', env);

  var config = {
    env: env,
	myVarName: 'someValue'
  }
  if (env == 'dev') {
var Factory = Java.type('MockSpringMvcServlet');
    karate.configure('httpClientInstance', Factory.getMock());
    //var result = karate.callSingle('classpath:demo/headers/common-noheaders.feature', config);
  } else if (env == 'e2e') {
    // customize
  }
  return config;
}