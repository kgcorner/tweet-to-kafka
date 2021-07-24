package com.kgcorner.twitter.client;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;

/**
 * Description : <Write class Description>
 * Author: kumar
 * Created on : 18/07/21
 */

public class TwitterClient {
    private static final String TOKEN = "token";
    private static final String SECRET = "secret";
    private static final String CONSUMER_KEY = "consumer.key";
    private static final String CONSUMER_TOKEN = "consumer.secret";
    private static final String APP_NAME = "app.name";
    private static Authentication authentication = null;
    private static ClientBuilder clientBuilder;

    public static void initClient() {
        Properties properties = new Properties();
        try {
            properties.load(TwitterClient.class.getResourceAsStream("/twitter.properties"));
        } catch (IOException e) {
            throw new IllegalStateException("No properties file found");
        }

        String token = properties.getProperty(TOKEN);
        String secret = properties.getProperty(SECRET);
        String consumerKey = properties.getProperty(CONSUMER_KEY);
        String consumerSecret = properties.getProperty(CONSUMER_TOKEN);

        authentication = new OAuth1(consumerKey, consumerSecret, token, secret);
        clientBuilder = new ClientBuilder()
            .name(properties.getProperty(APP_NAME))
            .authentication(authentication);

    }

    public static Client createClient(BlockingQueue<String> messageQueue, List<String> terms, BlockingQueue<Event> eventQueue) {
        if(clientBuilder == null)
            throw new IllegalStateException("Client not initialized");
        Hosts streamHost = new HttpHosts(Constants.STREAM_HOST);
        List<Long> followings = Lists.newArrayList(1234L, 566788L);
        StatusesFilterEndpoint statusesFilterEndpoint = new StatusesFilterEndpoint();
        statusesFilterEndpoint.trackTerms(terms);
        statusesFilterEndpoint.followings(followings);

        return clientBuilder.endpoint(statusesFilterEndpoint)
        .hosts(streamHost)
        .eventMessageQueue(eventQueue)
        .processor(new StringDelimitedProcessor(messageQueue)).build();
    }

}