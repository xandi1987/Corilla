package corilla.services;
import android.app.IntentService;
import android.content.Intent;
import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MessageService extends IntentService {

     private static final String URL_ACTIVITIES = "http://corilla.net/en/activities/in.json";

    public static final String NOTIFICATION = "com.vogella.android.service.receiver";
    public MessageService() {
        super("MessageService");
    }

    // will be called asynchronously by Android
    @Override
    protected void onHandleIntent(Intent intent) {

        ArrayList<CorillaActivity> result = (ArrayList<CorillaActivity>) getMessage();
        Intent resultIntent = new Intent(NOTIFICATION);
        resultIntent.putExtra("result",result);
        sendBroadcast(resultIntent);
        //TEst
    }

    private List readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List retVal = null;
        try {
            retVal = readMessagesArray(reader);}
        catch (IOException ex) {
        }
        finally {
            reader.close();
        }
        return retVal;
    }


    private List readMessagesArray(JsonReader reader) throws IOException {
        List messages = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            try{ messages.add(readMessage(reader));}catch (Exception ex){
                String t = ex.getMessage();
                reader.endObject();
            }finally {

            }
        }
        reader.endArray();
        return messages;
    }

    private CorillaActivity readMessage(JsonReader reader) throws IOException,ParseException {
        CorillaActivity activity = new CorillaActivity();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("b")) {
                String string = reader.nextString();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
                    activity.setB(format.parse(string));
            } else if (name.equals("e")) {
                String string = reader.nextString();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
                activity.setE(format.parse(string));
            } else if (name.equals("location")) {
                String string = reader.nextString();
                activity.setLocation(string);
            } else if (name.equals("subscriptions_count")) {
                int count = reader.nextInt();
                activity.setSubscriptionsCount(count);
            } else if (name.equals("subscriptions_count")) {
                String timeZone = reader.nextString();
                activity.setTimeZone(timeZone);
            } else if (name.equals("title")) {
                String title = reader.nextString();
                activity.setTitle(title);
            }else if (name.equals("url")) {
                String url = reader.nextString();
                activity.setUrl(url);
            }
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return activity;
    }


    private List getMessage() {
        String urlPath = URL_ACTIVITIES;//intent.getStringExtra(URL_ACTIVITIES);
        InputStream stream = null;
        List retVal = null;
        try {

            URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            retVal =  readJsonStream(stream);

        } catch (Exception e) {}finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return retVal;
    }
}

