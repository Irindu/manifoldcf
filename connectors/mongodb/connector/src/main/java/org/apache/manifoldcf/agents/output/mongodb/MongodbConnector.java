package org.apache.manifoldcf.agents.output.mongodb;

import com.mongodb.MongoClient;
import org.apache.manifoldcf.agents.output.BaseOutputConnector;
import org.apache.manifoldcf.core.interfaces.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MongodbConnector extends BaseOutputConnector {
    private final static String MONGODB_INDEXATION_ACTIVITY = "Indexation";
    private final static String MONGODB_DELETION_ACTIVITY = "Deletion";
    private final static String MONGODB_OPTIMIZE_ACTIVITY = "Optimize";

    protected String username = null;
    protected String password = null;
    protected String serverLocation = null;
    protected int portNumber = 27017;
    protected String database = null;
    protected String collection = null;
    protected String document = null;

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

    private MongoClient client = null;

    private long expirationTime = -1L;

    public MongodbConnector()
    {
    }

    @Override
    public void connect(ConfigParams configParams)
    {
        super.connect(configParams);
        username = params.getParameter( MongodbConfig.ParameterEnum.USERNAME.name());
        password = params.getParameter( MongodbConfig.ParameterEnum.PASSWORD.name());
        username = "user";
        password = "pass";
        serverLocation = params.getParameter( MongodbConfig.ParameterEnum.SERVERLOCATION.name());
        portNumber = Integer.parseInt(params.getParameter( MongodbConfig.ParameterEnum.PORTNUMBER.name()));
        database = params.getParameter( MongodbConfig.ParameterEnum.DATABASE.name());
        collection = params.getParameter( MongodbConfig.ParameterEnum.COLLECTION.name());
        document = params.getParameter( MongodbConfig.ParameterEnum.DOCUMENT.name());

    }


    /** This method is called to assess whether to count this connector instance should
     * actually be counted as being connected.
     *@return true if the connector instance is actually connected.
     */
    @Override
    public boolean isConnected()
    {
        return client != null;
    }


    protected MongoClient getSession()
            throws ManifoldCFException
    {
        if (client == null)
        {

            try{
                client = new MongoClient(serverLocation ,portNumber );
                client = new MongoClient("localhost" ,27017 );
            }
            catch(UnknownHostException e){
                            throw new ManifoldCFException("UnknownHostException");
            }
        }

        return client;
    }
    @Override
    public void disconnect()
            throws ManifoldCFException
    {
        super.disconnect();
        client = null;
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

    /** Build a Set of Mongodb parameters. If configParams is null,
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

    @Override
    public void outputConfigurationHeader(IThreadContext threadContext,
                                          IHTTPOutput out, Locale locale, ConfigParams parameters,
                                          List<String> tabsArray) throws ManifoldCFException, IOException
    {
        super.outputConfigurationHeader(threadContext, out, locale, parameters,
                tabsArray);
        tabsArray.add(Messages.getString(locale, MONGODB_TAB_PARAMETERS));
        outputResource(EDIT_CONFIG_HEADER_FORWARD, out, locale, null, null, null, null);
    }

    @Override
    public void outputConfigurationBody(IThreadContext threadContext,
                                        IHTTPOutput out, Locale locale, ConfigParams parameters, String tabName)
            throws ManifoldCFException, IOException
    {
        super.outputConfigurationBody(threadContext, out, locale, parameters,
                tabName);
        MongodbConfig config = this.getConfigParameters(parameters);
        outputResource(EDIT_CONFIG_FORWARD_PARAMETERS, out, locale, config, tabName, null, null);
    }

    /** Read the content of a resource, replace the variable ${PARAMNAME} with the
     * value and copy it to the out.
     *
     * @param resName
     * @param out
     * @throws ManifoldCFException */
    private static void outputResource(String resName, IHTTPOutput out,
                                       Locale locale, MongodbParam params,
                                       String tabName, Integer sequenceNumber, Integer currentSequenceNumber) throws ManifoldCFException
    {
        Map<String,String> paramMap = null;
        if (params != null) {
            paramMap = params.buildMap();
            if (tabName != null) {
                paramMap.put("TabName", tabName);
            }
            if (currentSequenceNumber != null)
                paramMap.put("SelectedNum",currentSequenceNumber.toString());
        }
        else
        {
            paramMap = new HashMap<String,String>();
        }
        if (sequenceNumber != null)
            paramMap.put("SeqNum",sequenceNumber.toString());

        Messages.outputResourceWithVelocity(out, locale, resName, paramMap, true);
    }

    @Override
    public void viewConfiguration(IThreadContext threadContext, IHTTPOutput out,
                                  Locale locale, ConfigParams parameters) throws ManifoldCFException,
            IOException
    {
        outputResource(VIEW_CONFIG_FORWARD, out, locale,
                getConfigParameters(parameters), null, null, null);
    }

    @Override
    public VersionContext getPipelineDescription(Specification os)
            throws ManifoldCFException
    {
        return new VersionContext("",params,os);
    }

    @Override
    public String processConfigurationPost(IThreadContext threadContext,
                                           IPostParameters variableContext, ConfigParams parameters)
            throws ManifoldCFException
    {
        MongodbConfig.contextToConfig(variableContext, parameters);
        return null;
    }


}

