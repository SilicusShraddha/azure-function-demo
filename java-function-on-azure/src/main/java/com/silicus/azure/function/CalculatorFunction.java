package com.silicus.azure.function;

import java.util.Optional;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.HttpRequestMessage;
import com.microsoft.azure.serverless.functions.HttpResponseMessage;
import com.microsoft.azure.serverless.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.serverless.functions.annotation.FunctionName;
import com.microsoft.azure.serverless.functions.annotation.HttpTrigger;
import com.silicus.azure.Calculator;

public class CalculatorFunction 
{
   
        @FunctionName("calulatorFunction")
        public HttpResponseMessage<String> calulatorFunction(@HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
                final ExecutionContext context) {
            context.getLogger().info("Java HTTP trigger processed a request.");

            // Parse query parameter
            String query = request.getQueryParameters().get("name");
            
            String query1 = request.getQueryParameters().get("name1");
            String name = request.getBody().orElse(query);
            String name1 = request.getBody().orElse(query1);
            

            if (name == null) {
                return request.createResponse(400, "Please pass a name on the query string or in the request body");
            } else {
            	Calculator cal = new Calculator();
                return request.createResponse(200, name+"***"+name1);
            }
        }
  
}
   
