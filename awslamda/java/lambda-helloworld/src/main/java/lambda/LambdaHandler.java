package lambda;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;

public class LambdaHandler 

{
    public String myHandler(Person person, Context context) {
    	AWSCredentials credentials = new BasicAWSCredentials("AKIAI66DD3IIGLEVVCXQ", "1S10CeT0RrUj4/gUfuXYqxYfi5O7iTiHBekfdino");
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials);
        client.setRegion(Region.getRegion(Regions.EU_CENTRAL_1));
        DynamoDB dynamoDB = new DynamoDB(client);
    	Table table = dynamoDB.getTable("person");
    	Item item = new Item().withString("name", person.getName()).withInt("age", person.getAge());
    	table.putItem(item);	 
        return "Success";
    } 
}
