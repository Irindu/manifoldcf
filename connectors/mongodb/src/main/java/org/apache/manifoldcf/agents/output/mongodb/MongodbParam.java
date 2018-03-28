package org.apache.manifoldcf.agents.output.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.apache.manifoldcf.agents.output.mongodb.MongodbParam.ParameterEnum;

public class MongodbParam extends HashMap<ParameterEnum, String> {
    /** Parameters constants */
    public enum ParameterEnum
    {
        SERVERLOCATION("localhost"),

        PORTNUMBER("2170"),

        USERNAME("user"),

        PASSWORD("pass"),

        DATABASE("testdb"),

        COLLECTION("user"),

        DOCUMENT("a");

        final protected String defaultValue;

        private ParameterEnum(String defaultValue)
        {
            this.defaultValue = defaultValue;
        }
    }

    private static final long serialVersionUID = -1593234685772720029L;

    protected MongodbParam(ParameterEnum[] params)
    {
        super(params.length);
    }

    final public Map<String, String> buildMap()
    {
        Map<String, String> rval = new HashMap<String, String>();
        for (Map.Entry<ParameterEnum, String> entry : this.entrySet())
            rval.put(entry.getKey().name(), entry.getValue());
        return rval;
    }

}

