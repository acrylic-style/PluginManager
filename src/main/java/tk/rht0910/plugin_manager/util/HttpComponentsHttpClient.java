package tk.rht0910.plugin_manager.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpComponentsHttpClient extends Thread {
	private static String URI = "";

	public HttpComponentsHttpClient(String URI) {
		HttpComponentsHttpClient.URI = URI;
	}

    public static void main(String[] args) {
        executePost(args[0]);
    }

    public void run() {
        System.out.println("===== HTTP GET Start =====");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        // もしくは
        // try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet getMethod = new HttpGet(URI);

            try (CloseableHttpResponse response = httpClient.execute(getMethod)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    System.out.println(EntityUtils.toString(entity,
                                                            StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("===== HTTP GET End =====");
        return;
    }

    public static void executePost(String URI) {
        System.out.println("===== HTTP POST Start =====");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
        // もしくは
        // try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost postMethod = new HttpPost(URI);

            StringBuilder builder = new StringBuilder();
            builder.append("POST Body");
            builder.append("\r\n");
            builder.append("Hello Http Server!!");
            builder.append("\r\n");

            postMethod.setEntity(new StringEntity(builder.toString(),
                                                  StandardCharsets.UTF_8));

            try (CloseableHttpResponse response = httpClient.execute(postMethod)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    System.out.println(EntityUtils.toString(entity,
                                                            StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===== HTTP POST End =====");
    }
}