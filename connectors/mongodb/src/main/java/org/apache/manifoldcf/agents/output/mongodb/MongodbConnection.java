package org.apache.manifoldcf.agents.output.mongodb;

import com.mongodb.MongoClient;

public class MongodbConnection   {

    protected MongodbConfig config;

    private MongoClient mongoClient;

    private String serverLocation;

    private short portNumber;

    private String userName;

    private String database;

    private String collection;

    private String document;

    private String response;

    private String resultCode;


    public enum Result
    {
        OK, ERROR, UNKNOWN;
    }

    private Result result;

    protected MongodbConnection(MongodbConfig config, MongoClient mongoClient)
    {
        this.config = config;
//        this.mongoClient = new MongoClient("localhost" ,27017 );
        this.mongoClient = mongoClient;
        result = Result.UNKNOWN;
        response = null;

    }
}

