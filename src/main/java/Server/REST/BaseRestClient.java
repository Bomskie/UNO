package Server.REST;

import Server.REST.DataObjects.BaseRequestObject;
import Shared.Logging.Logger;
import com.google.gson.Gson;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;

public abstract class BaseRestClient {
    private final Gson gson = new Gson();

    abstract String getBaseURL();

    public  <T> T executeQueryGet(String queryGet, Class<T> clazz)
    {
        // Build the query for the REST service
        final String query = getBaseURL() + queryGet;
        // Perform the query
        HttpGet httpGet = new HttpGet(query);

        return executeRequest(httpGet, clazz);
    }

    private <T> T executeRequest(HttpUriRequest request, Class<T> clazz)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request))
        {
            return gson.fromJson(EntityUtils.toString(response.getEntity()), clazz);
        }
        catch (Exception exc)
        {
            Logger.getInstance().log(exc);
            return null;
        }
    }

    <T> T executeQueryPost(BaseRequestObject request, String queryPost, Class<T> clazz)
    {
        HttpPost httpPost = new HttpPost(getBaseURL() + queryPost);
        httpPost.addHeader("content-type", "application/json");
        try
        {
            httpPost.setEntity(new StringEntity(gson.toJson(request)));
        }
        catch (Exception exc)
        {
            Logger.getInstance().log(exc);
        }
        return executeRequest(httpPost, clazz);
    }

    public <T> T  executeQueryPut(BaseRequestObject petRequest, String queryPut , Class<T> clazz)
    {
        // Build the query for the REST service
        final String query = getBaseURL() + queryPut;

        // Perform the query
        HttpPut httpPut = new HttpPut(query);
        httpPut.addHeader("content-type", "application/json");
        StringEntity params;
        try
        {
            params = new StringEntity(gson.toJson(petRequest));
            httpPut.setEntity(params);
        }
        catch (UnsupportedEncodingException ex)
        {
            Logger.getInstance().log(ex);
        }

        return executeRequest(httpPut, clazz);
    }

    public <T> T executeQueryDelete(String queryDelete, Class<T> clazz)
    {
        // Build the query for the REST service
        final String query = getBaseURL() + queryDelete;

        // Perform the query
        HttpDelete httpDelete = new HttpDelete(query);

        return executeRequest(httpDelete, clazz);
    }
}
