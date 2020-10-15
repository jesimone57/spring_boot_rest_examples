function fn() {    
   var env = karate.env; // get system property 'karate.env'
   if (!env) {
      env = 'dev';
   }
   karate.log('karate.env system property was:', env);

   // configure variables that will be available in every test
   var config = {
      env: env,
	  myVarName: 'someValue',
	  baseUrl: 'http://localhost:8080'
   }

   if (env == 'dev') {
      var Factory = Java.type('MockSpringMvcServlet');
      karate.configure('httpClientInstance', Factory.getMock());
      //var result = karate.callSingle('classpath:demo/headers/common-noheaders.feature', config);
   } else if (env == 'stg') {
      // customize
   } else if (env == 'prod') {
      // customize
   }

   return config;
}