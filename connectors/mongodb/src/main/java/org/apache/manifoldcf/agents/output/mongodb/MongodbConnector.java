package org.apache.manifoldcf.agents.output.mongodb;

import org.apache.manifoldcf.agents.output.BaseOutputConnector;
import org.apache.manifoldcf.core.interfaces.ConfigParams;
import org.apache.manifoldcf.core.interfaces.ManifoldCFException;

public class MongodbConnector extends BaseOutputConnector {
    private final static String MONGODB_INDEXATION_ACTIVITY = "Indexation";
    private final static String MONGODB_DELETION_ACTIVITY = "Deletion";
    private final static String MONGODB_OPTIMIZE_ACTIVITY = "Optimize";

    private final static String[] MONGODB_ACTIVITIES =
            { MONGODB_INDEXATION_ACTIVITY, MONGODB_DELETION_ACTIVITY,
                    MONGODB_OPTIMIZE_ACTIVITY };

    private final static String MONGODB_TAB_PARAMETERS = "MongodbConnector.Parameters";

    /** Forward to the javascript to check the configuration parameters */
    private static final String EDIT_CONFIG_HEADER_FORWARD = "editConfiguration.js";

    /** Forward to the HTML template to edit the configuration parameters */
    private static final String EDIT_CONFIG_FORWARD_PARAMETERS = "editConfiguration_Parameters.html";

    /** Forward to the HTML template to view the configuration parameters */
    private static final String VIEW_CONFIG_FORWARD = "viewConfiguration.html";

    /** Connection expiration interval */
    private static final long EXPIRATION_INTERVAL = 60000L;

    private long expirationTime = -1L;

    public MongodbConnector()
    {
    }

    @Override
    public void connect(ConfigParams configParams)
    {
        super.connect(configParams);
    }


    /** This method is called to assess whether to count this connector instance should
     * actually be counted as being connected.
     *@return true if the connector instance is actually connected.
     */
    @Override
    public boolean isConnected()
    {
        return false;
    }

    @Override
    public void disconnect()
            throws ManifoldCFException
    {
        super.disconnect();
       // closeSession();
    }

    @Override
    public void poll()
            throws ManifoldCFException
    {
        super.poll();
//        if (connectionManager != null)
//        {
//            if (System.currentTimeMillis() > expirationTime)
//            {
//                closeSession();
//            }
//        }
    }

    @Override
    public String[] getActivitiesList()
    {
        return MONGODB_ACTIVITIES;
    }

    /** Build a Set of ElasticSearch parameters. If configParams is null,
     * getConfiguration() is used.
     *
     * @param configParams */
    final private MongodbConfig getConfigParameters(
            ConfigParams configParams)
    {
        if (configParams == null)
            configParams = getConfiguration();
        return new MongodbConfig(configParams);
    }
}

