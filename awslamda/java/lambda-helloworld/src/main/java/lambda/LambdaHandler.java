package lambda;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;

public class LambdaHandler 

{
    public String myHandler(Person person, Context context) {
    	AWSCredentials credentials = new BasicAWSCredentials("AKIAJN6OE7WDGRBNJEOA", "wSWGGcbRSHYmZrFPojqNQpRp/SpsZWccg48Sz2Vf");
    	DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(credentials));
    	Table table = dynamoDB.getTable("person");
    	Item item = new Item().withString("name", person.getName()).withInt("age", person.getAge());
    	table.putItem(item);	 
        return "Success";
    } 
}
