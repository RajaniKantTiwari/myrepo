package com.app.community.ui.activity.uploadfile;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.app.community.event.EncodedBitmap;
import com.app.community.ui.base.BaseActivity;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class Upload extends AsyncTask<Void, Void, String> {
    private static final String TAG = Upload.class.getSimpleName();

    private final BaseActivity activity;
    private Bitmap image;

    public Upload(BaseActivity activity, Bitmap image) {
        this.activity = activity;
        this.image = image;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showProgress();

    }

    @Override
    protected String doInBackground(Void... params) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //compress the image to jpg format
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            /*
            * encode image to base64 so that it can be picked by saveImage.php file
            * */
        String encodeImage = "data:image/jpg;base64," + Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
      /*  //generate hashMap to store encodedImage and the name
        HashMap<String, String> detail = new HashMap<>();
        detail.put("email", "abcd@yopmail.com");
        detail.put("image", encodeImage);

        try {
            //convert this HashMap to encodedUrl to send to php file
            String dataToSend = hashMapToUrl(detail);
            //make a Http request and send data to saveImage.php file

            String response = Request.post(ApiService.SERVER_URL + ApiService.ADD_PLACES, dataToSend);

            //return the response
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "ERROR  " + e);
            return null;
        }*/
      return encodeImage;
    }


    @Override
    protected void onPostExecute(String encodeImage) {
        EventBus.getDefault().post(new EncodedBitmap(encodeImage));
        activity.hideProgress();
    }


    private String hashMapToUrl(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
