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
	private static int instanceCounter;
	
	public CalculatorFunction() {
		instanceCounter++;
	}
   
        @FunctionName("calulatorFunction")
        public HttpResponseMessage<String> calulatorFunction(@HttpTrigger(name = "req", methods = {"get", "post"}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
                final ExecutionContext context) {
            context.getLogger().info("Java HTTP trigger processed a request.");

            // Parse query parameter
            String query = request.getQueryParameters().get("firstNumAsString");
            String firstNumAsString = request.getBody().orElse(query);
            
            String query1 = request.getQueryParameters().get("secondNumAsString");
            String secondNumAsString = request.getBody().orElse(query1);
            

            if (firstNumAsString == null || secondNumAsString==null) {
                return request.createResponse(400, "Please pass a name on the query string or in the request body");
            } else {
            	int firstNum = Integer.parseInt(firstNumAsString);
            	int secondNum = Integer.parseInt(secondNumAsString);
            	Calculator caluator = new Calculator();
                return request.createResponse(200, "The current instance is: "+instanceCounter+" and The summation is: "+caluator.add(firstNum, secondNum));
            }
        }
  
}
   
