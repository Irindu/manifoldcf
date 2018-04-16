package org.apache.manifoldcf.agents.output.mongodb;

import org.apache.manifoldcf.core.interfaces.ConfigParams;
import org.apache.manifoldcf.core.interfaces.IPostParameters;
import java.util.Locale;

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

    public final static void contextToConfig(IPostParameters variableContext,
                                             ConfigParams parameters)
    {
        for (ParameterEnum param : CONFIGURATIONLIST)
        {
            String p = variableContext.getParameter(param.name().toLowerCase(Locale.ROOT));
            if (p != null)
                parameters.setParameter(param.name(), p);
        }

    }

    final public String getServerLocation()
    {
        return get(ParameterEnum.SERVERLOCATION);
    }
    final public String getPortNumber()
    {
        return get(ParameterEnum.PORTNUMBER);
    }
    final public String getUsername()
    {
        return get(ParameterEnum.USERNAME);
    }
    final public String getPassword()
    {
        return get(ParameterEnum.PASSWORD);
    }
    final public String getDatabase()
    {
        return get(ParameterEnum.DATABASE);
    }
    final public String getCollection()
    {
        return get(ParameterEnum.COLLECTION);
    }
    final public String getDocument()
    {
        return get(ParameterEnum.DOCUMENT);
    }

}
