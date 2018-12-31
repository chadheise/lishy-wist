package io.chadheise.lishyWist.guice;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.google.inject.Provider;

public class DynamoDBProvider implements Provider<DynamoDB> {

    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";

    @Override
    public DynamoDB get() {
        AWSCredentials creds = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        AWSCredentialsProvider credsProvider = new AWSStaticCredentialsProvider(creds);
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(credsProvider).build();
        return new DynamoDB(amazonDynamoDB);
    }

}
