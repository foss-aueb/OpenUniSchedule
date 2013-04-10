package com.foss.aueb.openunischedule.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;

public class AUEBDownloader implements ScheduleDownloader {

	@Override
	public JSONArray downloadSchedule() {
		JSONArray jsonArray = null;
		
		StringBuilder builder = new StringBuilder();
        HttpParams httpParameters = new BasicHttpParams();
        
        int timeoutConnection = 4000;
        
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpClient client = new DefaultHttpClient(httpParameters);
        HttpGet httpGet = new HttpGet("http://schedule.aueb.gr/mobile/index.php");
        try {
           HttpResponse response = client.execute(httpGet);
           StatusLine statusLine = response.getStatusLine();
           int statusCode = statusLine.getStatusCode();
           //If downloaded successfully
           if (statusCode == 200) {
              HttpEntity entity = response.getEntity();
              InputStream content = entity.getContent();
              BufferedReader reader = new BufferedReader( new InputStreamReader(content) );
              String line;
              while ((line = reader.readLine()) != null) {
                 builder.append(line);
              }
              jsonArray = new JSONArray(builder.toString());
           } else {
              //Do something else in case of error
           }
        } catch (ClientProtocolException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
	}

	@Override
	public boolean checkForExistingUpdate() {
		// TODO 
		return false;
	}

}
