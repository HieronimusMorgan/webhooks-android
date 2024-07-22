package com.morg.webhook.configuration;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.morg.webhook.bottomsheet.BottomSheetWebhooks;
import com.morg.webhook.data.RetrofitHttpsCall;
import com.morg.webhook.data.WebhooksServiceAPI;
import com.morg.webhook.model.Embeds;
import com.morg.webhook.model.Fields;
import com.morg.webhook.model.Webhooks;
import com.morg.webhook.utils.AsyncTask;
import com.morg.webhook.utils.Const;
import com.morg.webhook.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebhooksConfiguration {
    private static final String TAG = WebhooksConfiguration.class.getSimpleName();
    private Context context = null;
    private String title;
    private String description;
    private String titleBottomSheet;
    private String descriptionBottomSheet;
    private List<Fields> fields;
    private WebhooksServiceAPI webhooksServiceAPI;
    private String webhooksUrl = "";
    private FileUtil fileUtil;

    public WebhooksConfiguration(Context context, String webhooksUrl) {
        if (this.context == null && this.webhooksUrl.isEmpty()) {
            this.webhooksUrl = webhooksUrl;
            this.context = context;
            this.fileUtil = new FileUtil(context);
            this.fields = new ArrayList<>();
            fields.add(new Fields("Project", getApplicationName()));
            webhooksServiceAPI = RetrofitHttpsCall.getInstance(webhooksUrl + Const.Data.SLASH).create(WebhooksServiceAPI.class);
        }
    }

    private String getApplicationName() {
        return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
    }

    public WebhooksConfiguration setTitle(String title) {
        this.title = title;
        return this;
    }

    public WebhooksConfiguration setDescription(String description) {
        this.description = description;
        return this;
    }

    public WebhooksConfiguration addField(Fields fields) {
        this.fields.add(fields);
        return this;
    }

    public WebhooksConfiguration bottomSheet(String title, String description) {
        titleBottomSheet = title;
        descriptionBottomSheet = description;
        return this;
    }

    public WebhooksConfiguration build() {
        try {
            if (titleBottomSheet != null && descriptionBottomSheet != null) {
                BottomSheetWebhooks bottomSheetWebhooks = new BottomSheetWebhooks(titleBottomSheet, descriptionBottomSheet, o -> sendWebhooks());
                bottomSheetWebhooks.show(((FragmentActivity) context).getSupportFragmentManager(), bottomSheetWebhooks.getTag());
            } else
                sendWebhooks();
        } catch (Exception e) {
            Log.e(TAG, "build: ", e);
        }
        return this;
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    private void sendWebhooks() {
        try {
            new AsyncTask().getBackgroundThread().execute(() -> {
                exportSessionAndDatabase();
                File file = fileUtil.getAbsoluteFile();
                RequestBody requestBodyFileZip = RequestBody.create(MediaType.parse("application/zip"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file1", file.getName(), requestBodyFileZip);

                Webhooks webhooks = new Webhooks();
                List<Embeds> embeds = new ArrayList<>();
                Embeds embed = new Embeds();
                embed.setTitle(title);
                embed.setDescription(description);

                embed.setFields(fields);
                embeds.add(embed);
                webhooks.setEmbeds(embeds);

                RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), new Gson().toJson(webhooks));
                webhooksServiceAPI.sendWebhooksRequestBody(webhooksUrl, requestBody, fileToUpload).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: Success " + response.body());
                        fileUtil.deleteFiles();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: Failed " + t.getMessage());
                        fileUtil.deleteFiles();
                    }
                });
            });
        } catch (Exception e) {
            Log.e(TAG, "build: ", e);
        }
    }

    public WebhooksConfiguration sendLog() {
        try {
            sendLogWebhooks();
        } catch (Exception e) {
            Log.e(TAG, "build: ", e);
        }
        return this;
    }

    private void sendLogWebhooks() {
        try {
            new AsyncTask().getBackgroundThread().execute(() -> {
                Webhooks webhooks = new Webhooks();
                List<Embeds> embeds = new ArrayList<>();
                Embeds embed = new Embeds();
                embed.setTitle(title + " " + getApplicationName(context));
                embed.setDescription(description);

                embeds.add(embed);
                webhooks.setEmbeds(embeds);

                RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), new Gson().toJson(webhooks));
                try {
                    webhooksServiceAPI.sendWebhooksRequestBodyLog(webhooksUrl, requestBody).execute();
                } catch (IOException e) {
                    Log.e(TAG, "sendLogWebhooks: ", e);
                }

            });
        } catch (Exception e) {
            Log.e(TAG, "build: ", e);
        }
    }

    private void exportSessionAndDatabase() {
        File files = fileUtil.getAbsoluteFile();
        String[] arraySession = exportSession(files);
        String[] arrayDatabase = exportDB(files);

        int databaseLength = arrayDatabase.length;
        int sessionLength = arraySession.length;
        String[] result = new String[databaseLength + sessionLength];

        System.arraycopy(arrayDatabase, 0, result, 0, databaseLength);
        System.arraycopy(arraySession, 0, result, databaseLength, sessionLength);
        FileUtil.zip(result, fileUtil.getAbsoluteFile().getAbsolutePath());
    }

    private String[] exportDB(File file) {
        try {
            String databasePath = Const.Data.DATA + context.getPackageName() + Const.Data.DATABASE_PATH;
            File currentDatabasesPath = new File(Environment.getDataDirectory(), databasePath);
            fileUtil.copy(currentDatabasesPath, file);
            return fileUtil.getFileFromFolder(currentDatabasesPath.getAbsolutePath());
        } catch (Exception e) {
            return new String[0];
        }
    }

    private String[] exportSession(File file) {
        try {
            String sessionPath = Const.Data.DATA + context.getPackageName() + Const.Data.SHARED_PREF_PATH;
            File currentSessionPath = new File(Environment.getDataDirectory(), sessionPath);
            fileUtil.copy(currentSessionPath, file);
            return fileUtil.getFileFromFolder(currentSessionPath.getAbsolutePath());
        } catch (Exception e) {
            return new String[0];
        }
    }

}
