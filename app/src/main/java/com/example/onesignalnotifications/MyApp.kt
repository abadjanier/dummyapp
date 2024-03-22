package com.example.onesignalnotifications

import android.app.Application
import com.onesignal.OneSignal
import com.onesignal.OneSignal.ExternalIdError
import com.onesignal.OneSignal.OSExternalUserIdUpdateCompletionHandler
import org.json.JSONException
import org.json.JSONObject


// NOTE: Replace the below with your own ONESIGNAL_APP_ID
const val ONESIGNAL_APP_ID = "d7c419ce-1581-45a0-b0f2-ce1b2ac68c61"

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // Verbose Logging set to help debug issues, remove before releasing your app.
        //OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // OneSignal Initialization
       // OneSignal.initWithContext(this, ONESIGNAL_APP_ID)
  //      OneSignal.login("17cd9429-7e6c-4bc2-8aeb-6c65d62fe8a4");


//        OneSignal.User.addAlias("external_id","17cd9429-7e6c-4bc2-8aeb-6c65d62fe8a4")
        //OneSignal.User.setLanguage("ru")
        //OneSignal.login("17cd9429-7e6c-4bc2-8aeb-6c65d62fe8a4")

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
       // CoroutineScope(Dispatchers.IO).launch {
       //     OneSignal.Notifications.requestPermission(true)
      //  }

        //OneSignal.login("17cd9429-7e6c-4bc2-8aeb-6c65d62fe8a4")




        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications()
        OneSignal.removeExternalUserId()
        OneSignal.setExternalUserId("17cd9429-7e6c-4bc2-8aeb-6c65d62fe8a4")
        OneSignal.sendTag("external_id","17cd9429-7e6c-4bc2-8aeb-6c65d62fe8a4")

        val externalUserId =
            "17cd9429-7e6c-4bc2-8aeb-6c65d62fe8a4" // You will supply the external user id to the OneSignal SDK


// Setting External User Id with Callback Available in SDK Version 4.0.0+

// Setting External User Id with Callback Available in SDK Version 4.0.0+
        OneSignal.setExternalUserId(
            externalUserId,
            object : OSExternalUserIdUpdateCompletionHandler {
                override fun onSuccess(results: JSONObject) {
                    try {
                        if (results.has("push") && results.getJSONObject("push").has("success")) {
                            val isPushSuccess = results.getJSONObject("push").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for push status: $isPushSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("email") && results.getJSONObject("email").has("success")) {
                            val isEmailSuccess =
                                results.getJSONObject("email").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for email status: $isEmailSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("sms") && results.getJSONObject("sms").has("success")) {
                            val isSmsSuccess = results.getJSONObject("sms").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for sms status: $isSmsSuccess"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(error: ExternalIdError) {
                    // The results will contain channel failure statuses
                    // Use this to detect if external_user_id was not set and retry when a better network connection is made
                    OneSignal.onesignalLog(
                        OneSignal.LOG_LEVEL.VERBOSE,
                        "Set external user id done with error: $error"
                    )
                }
            })


    }

}