package io.github.zackyl.robinhoodphonescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // write an app, fetches data from endpoint in JSON format
    // stocks etfs, instruments
    // render separate by line break, in single text field

    private static String ENDPOINT = "https://gist.githubusercontent.com/dns-mcdaid/b248c852b743ad960616bac50409f0f0/raw/6921812bfb76c1bea7868385adf62b7f447048ce/instruments.json";
    private JSONArray instruments;
    private static String INSTRUMENT_NAME = "name";
    private static String INSTRUMENT_TYPE = "instrument_type";


    private TextView txtViewInstrument;
    private EditText editTxtInstrument;
    private Button btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewInstrument = findViewById(R.id.txtViewInstrument);
        editTxtInstrument = findViewById(R.id.editTextInstrumentFilter);
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> {
            List<String> filteredInstruments = filterInstruments(editTxtInstrument.getText().toString());
            txtViewInstrument.setText(buildString(filteredInstruments));
        });

        new JsonTask().execute(ENDPOINT);
    }

    private String buildString(List<String> strings) {
        StringBuilder str = new StringBuilder();
        for (String string : strings) {
            str.append(string + "\n");
        }
        return str.toString();
    }

    // NOTE asyncTask deprecated
    private class JsonTask extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            JSONArray jsonArray;
            try {
                instruments = new JSONArray(result);
                Log.d("My App", instruments.toString());
            } catch (Throwable t) {
                Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
                return;
            }
            StringBuilder str = new StringBuilder();

            List<String> filteredInstruments = filterInstruments(editTxtInstrument.getText().toString());
            txtViewInstrument.setText(buildString(filteredInstruments));
        }
    }

    private List<String> filterInstruments(String filter) {
        List<String> filteredInstruments = new ArrayList<>();
        if (instruments == null) {
            return filteredInstruments;
        }
        try {
            for (int i = 0; i < instruments.length(); i++) {
                JSONObject jsonObject = instruments.getJSONObject(i);
                if (filter.isEmpty() || jsonObject.get(INSTRUMENT_TYPE).equals(filter)) {
                    filteredInstruments.add(jsonObject.get(INSTRUMENT_NAME).toString());
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return filteredInstruments;
    }

}