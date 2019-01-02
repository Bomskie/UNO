package Shared.REST;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public abstract class RestContext {
    protected String restURL = "http://localhost:8090/rest/";
    protected HttpClient httpClient;
    protected Gson gson;

    public RestContext(){
        httpClient = HttpClientBuilder.create().build();
        gson = new Gson();
    }

    protected RestResponse executePost(String url, HttpPost postrequest){
        //exevute the respons and return the Response from the REST service.
        try {
            HttpResponse response = httpClient.execute(postrequest);
            HttpEntity entity = response.getEntity();

            final String entityString = EntityUtils.toString(entity);
            RestResponse jsonResponse = gson.fromJson(entityString, RestResponse.class);
            return jsonResponse;

        } catch (IOException e) {
            System.out.println("IOException : " + e.toString());
        }
        return null;
    }
}
