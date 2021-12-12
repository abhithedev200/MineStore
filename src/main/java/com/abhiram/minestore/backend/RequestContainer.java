package com.abhiram.minestore.backend;

import com.abhiram.minestore.MineStore;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RequestContainer <T>{
    private final Executor request_executor = Executors.newSingleThreadExecutor();
    private final Gson gson = new Gson();
    private final String endpoint;
    private final String main_url;
    private final Class<T> clazz;

    public RequestContainer(MineStore plugin,String endpoint, Class<T> clazz){
        this.endpoint = endpoint;
        this.clazz = clazz;
        this.main_url = plugin.getMainPluginConfig().getConfig().getString("Url");
    }

    public T request() throws ExecutionException, InterruptedException {
        CompletableFuture<T> request = new CompletableFuture<>();

        request_executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(main_url + endpoint);
                    URLConnection connection = url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                    connection.setConnectTimeout(500000);
                    connection.connect();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    StringBuilder buffer = new StringBuilder();
                    while((line = in.readLine()) != null) {
                        buffer.append(line);
                    }
                    in.close();

                    request.complete(gson.fromJson(buffer.toString(),clazz));
                } catch (Exception e) {
                    // e.printStackTrace();
                    request.cancel(false);
                }
            }
        });

        return request.get();
    }
}
