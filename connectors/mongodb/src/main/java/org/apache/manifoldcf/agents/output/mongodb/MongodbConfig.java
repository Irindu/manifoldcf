package org.apache.manifoldcf.agents.output.mongodb;

import org.apache.manifoldcf.core.interfaces.ConfigParams;

public class MongodbConfig extends MongodbParam {

    private static final long serialVersionUID = -2071296573398352538L;

    /** Parameters used for the configuration */
    final private static ParameterEnum[] CONFIGURATIONLIST =
            {
                    ParameterEnum.SERVERLOCATION,
                    ParameterEnum.PORTNUMBER,
                    ParameterEnum.USERNAME,
                    ParameterEnum.PASSWORD,
                    ParameterEnum.DATABASE,
                    ParameterEnum.COLLECTION,
                    ParameterEnum.DOCUMENT
            };

    /** Build a set of ElasticSearchParameters by reading ConfigParams. If the
     * value returned by ConfigParams.getParameter is null, the default value is
     * set.
     *
     * @param paramList
     * @param params */
    public MongodbConfig(ConfigParams params)
    {
        super(CONFIGURATIONLIST);
        for (ParameterEnum param : CONFIGURATIONLIST)
        {
            String value = params.getParameter(param.name());
            if (value == null)
                value = param.defaultValue;
            put(param, value);
        }
    }
}
