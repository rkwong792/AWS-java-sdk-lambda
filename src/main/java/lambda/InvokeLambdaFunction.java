package lambda;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

import java.nio.charset.StandardCharsets;

public class InvokeLambdaFunction {
    public static void main(String [] args) {
        // The function name is the ARN (Amazon Resource Name)
        String functionName = "arn:aws:lambda:us-east-2:368557045598:function:myTestFunction";

        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(functionName)
                .withPayload("{\n" +
                        " \"Hello \": \"Paris\",\n" +
                        " \"countryCode\": \"FR\"\n" +
                        "}");

        System.out.println("Request Payload:");
        System.out.println(new String(invokeRequest.getPayload().array(), StandardCharsets.UTF_8));

        InvokeResult invokeResult;

        AWSLambda awsLambda = AWSLambdaClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(Regions.US_EAST_2).build();

        invokeResult = awsLambda.invoke(invokeRequest);

        String ans = new String(invokeResult.getPayload().array(), StandardCharsets.UTF_8);

        //write out the return value
        System.out.println("Response Payload:");
        System.out.println(ans);





    }
}
