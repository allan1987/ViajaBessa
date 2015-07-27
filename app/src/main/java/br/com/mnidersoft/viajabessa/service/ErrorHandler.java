package br.com.mnidersoft.viajabessa.service;

import android.util.Log;

import org.androidannotations.annotations.EBean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@EBean
public class ErrorHandler implements ResponseErrorHandler {
    private static final String TAG = "ErrorHandler";

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        int minor = Integer.parseInt(response.getHeaders().getFirst("minor"));
        Log.i(TAG, "Response statuscode: " + response.getStatusCode() + " minor: " + minor);
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        Log.i(TAG, "Response statuscode: " + response.getStatusCode());
        if (response.getStatusCode().value() >= 400) {
            return true;
        }
        return false;
    }
}