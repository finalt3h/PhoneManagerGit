package com.fsoft.sonnm6.phonemanagerapp.data;//package com.lge.car.app.synccontact.data;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.lge.car.app.profile.model.ProfileEntity;
//import com.lge.car.app.profile.model.TimeUsedEntity;
//import com.lge.car.app.profile.util.AppConfig;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//
//import static com.lge.car.app.profile.util.AppConfig.PACKAGE_MEDIA_APPLICATION;
//import static com.lge.car.app.profile.util.AppConfig.PACKAGE_NAVIGATION_MAP_APP;
//import static com.lge.car.app.profile.util.AppConfig.PACKAGE_PHONE_APPLICATION;
//
//
///**
// * Created by HungNN19 on 3/8/2017.
// */
//
//public class DataBaseHandler extends SQLiteOpenHelper {
//    // Contacts Table Columns names
//    public static final String KEY_ID = "id";
//    public static final String KEY_LAST_NAME = "last_name";
//    public static final String KEY_FIRST_NAME = "first_name";
//    public static final String KEY_MAIL = "mail";
//    public static final String KEY_IMAGE_USER = "image_user";
//    public static final String KEY_PASS = "pass";
//    public static final String KEY_BT_LOGIN = "bt_login";
//    public static final String KEY_SEAT_HANDLE = "seat_handle";
//    public static final String KEY_CLIMATE = "climate";
//    public static final String KEY_DRIVING_CLUSTER = "driving_cluster";
//    public static final String KEY_WIDGET = "widget";
//    public static final String KEY_GLOBAL_MENU = "global_menu";
//    public static final String KEY_NAVIGATION = "navigation";
//    public static final String KEY_MEDIA = "media";
//    public static final String KEY_CALENDAR = "calendar";
//    public static final String KEY_GOOGLE_NOW = "google_now";
//    public static final String KEY_WARNING_ALERT = "warning_alert";
//    public static final String KEY_STRONG_WIND = "strong_wind";
//    public static final String KEY_CLIMATE_DRIVER = "climate_driver";
//    public static final String KEY_CLIMATE_PASSENGER = "climate_passenger";
//    public static final String KEY_CLIMATE_FRONT_WINDOW = "climate_front_window";
//    public static final String KEY_CLIMATE_REAR_WINDOW = "climate_rear_window";
//    public static final String KEY_CLIMATE_AIR_MODE = "air_mode";
//    public static final String KEY_CLIMATE_AUTO_MODE = "auto_mode";
//    public static final String KEY_CLIMATE_AC_MODE = "ac_mode";
//    public static final String KEY_CLIMATE_SYNC_MODE = "sync_mode";
//    public static final String KEY_CLIMATE_ECO_MODE = "eco_mode";
//    public static final String KEY_CLIMATE_DRIVER_SEAT = "driver_seat";
//    public static final String KEY_CLIMATE_PASSENGER_SEAT = "passenger_seat";
//    public static final String KEY_CLIMATE_WIND_DIRECTION_1 = "wind_direction_1";
//    public static final String KEY_CLIMATE_WIND_DIRECTION_2 = "wind_direction_2";
//    public static final String KEY_TOTAL_AFTER = "total_after";
//    public static final String KEY_LAST_LOGIN = "last_login";
//    public static final String TAG = "DataBaseHandler";
//    public static final String KEY_APP_FIRST = "app_first";
//    public static final String KEY_APP_SECOND = "app_second";
//    public static final String KEY_APP_THIRD = "app_third";
//    public static final String KEY_VALUE_ITEM_VIEW_WIDGET_FIRST = "ValueItemViewWidgetFirst";
//    public static final String KEY_VALUE_ITEM_VIEW_WIDGET_SECOND = "ValueItemViewWidgetSecond";
//    public static final String KEY_VALUE_ITEM_VIEW_WIDGET_THIRD = "ValueItemViewWidgetThird";
//    private static final String DATABASE = "profile_data";
//    // All Static variables
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//    // Database Name
//    private static final String DATABASE_NAME = "ProfileManager";
//    // Profile table name
//    private static final String TABLE_PROFILE = "UserProfile";
//    private static final String MAIL_DEFAULT = "default!.com";
//    private static final String[] ARR_COLUMN_CLIMATE = new String[]{KEY_MAIL, KEY_CLIMATE_DRIVER, KEY_CLIMATE_PASSENGER, KEY_CLIMATE_DRIVER_SEAT,
//            KEY_CLIMATE_PASSENGER_SEAT, KEY_STRONG_WIND, KEY_CLIMATE_WIND_DIRECTION_1, KEY_CLIMATE_WIND_DIRECTION_2, KEY_CLIMATE_FRONT_WINDOW, KEY_CLIMATE_REAR_WINDOW,
//            KEY_CLIMATE_AIR_MODE, KEY_CLIMATE_AUTO_MODE, KEY_CLIMATE_AC_MODE, KEY_CLIMATE_SYNC_MODE, KEY_CLIMATE_ECO_MODE};
//    private static final String[] ARR_COLUMN_NAVI = new String[]{KEY_APP_FIRST};
//    private static final String[] ARR_COLUMN_WIDGET = new String[]{KEY_VALUE_ITEM_VIEW_WIDGET_FIRST,KEY_VALUE_ITEM_VIEW_WIDGET_SECOND,KEY_VALUE_ITEM_VIEW_WIDGET_THIRD};
//    public static DataBaseHandler sDataBaseHandler;
//    public Context mContext;
//    private SharedPreferences mSharePre;
//
//    public DataBaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        mContext = context;
//        mSharePre = context.getSharedPreferences(DATABASE, mContext.MODE_PRIVATE);
//        if (!isHasDataDefault()) {
//            ProfileEntity profileEntity = new ProfileEntity(new TimeUsedEntity(0, 0));
//            profileEntity.setMail(MAIL_DEFAULT);
//            profileEntity.setClimateDriver(AppConfig.KEY_CLIMATE_DRIVER);
//            profileEntity.setClimatePassenger(AppConfig.KEY_PASS_DRIVER);
//            profileEntity.setAppFirst(PACKAGE_NAVIGATION_MAP_APP);
//            profileEntity.setAppSecond(PACKAGE_MEDIA_APPLICATION);
//            profileEntity.setAppThird(PACKAGE_PHONE_APPLICATION);
//            profileEntity.setStrongWind(2);
//            profileEntity.setmWidgetFirst(1);
//            profileEntity.setmWidgetSecond(2);
//            profileEntity.setmWidgetThird(5);
//            addProfile(profileEntity);
//        }
//    }
//
//    public static DataBaseHandler newInstance(Context context) {
//        if (sDataBaseHandler == null) {
//            sDataBaseHandler = new DataBaseHandler(context);
//
//        }
//        return sDataBaseHandler;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
//                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LAST_NAME + " TEXT," + KEY_FIRST_NAME + " INTEGER,"
//                + KEY_MAIL + " TEXT ," + KEY_IMAGE_USER + " TEXT," + KEY_PASS + " TEXT,"
//                + KEY_BT_LOGIN + " INTEGER," + KEY_SEAT_HANDLE + " INTEGER," + KEY_CLIMATE + " INTEGER,"
//                + KEY_DRIVING_CLUSTER + " INTEGER," + KEY_WIDGET + " INTEGER," + KEY_GLOBAL_MENU + " INTEGER,"
//                + KEY_NAVIGATION + " INTEGER," + KEY_MEDIA + " INTEGER," + KEY_CALENDAR + " INTEGER,"
//                + KEY_GOOGLE_NOW + " INTEGER," + KEY_WARNING_ALERT + " INTEGER,"
//
//                + KEY_VALUE_ITEM_VIEW_WIDGET_FIRST + " INTEGER,"
//                + KEY_VALUE_ITEM_VIEW_WIDGET_SECOND + " INTEGER,"
//                + KEY_VALUE_ITEM_VIEW_WIDGET_THIRD + " INTEGER,"
//                + KEY_STRONG_WIND + " INTEGER," + KEY_CLIMATE_DRIVER + " INTEGER,"
//                + KEY_CLIMATE_PASSENGER + " INTEGER," + KEY_CLIMATE_FRONT_WINDOW + " INTEGER,"
//                + KEY_CLIMATE_REAR_WINDOW + " INTEGER," + KEY_CLIMATE_AIR_MODE + " INTEGER," + KEY_CLIMATE_AUTO_MODE + " INTEGER,"
//                + KEY_CLIMATE_AC_MODE + " INTEGER," + KEY_CLIMATE_SYNC_MODE + " INTEGER," + KEY_CLIMATE_ECO_MODE + " INTEGER,"
//                + KEY_CLIMATE_DRIVER_SEAT + " INTEGER," + KEY_CLIMATE_PASSENGER_SEAT + " INTEGER,"
//                + KEY_CLIMATE_WIND_DIRECTION_1 + " INTEGER," + KEY_CLIMATE_WIND_DIRECTION_2 + " INTEGER,"
//                + KEY_LAST_LOGIN + " INTEGER," + KEY_TOTAL_AFTER + " INTEGER,"
//                + KEY_APP_FIRST + " TEXT," + KEY_APP_SECOND + " TEXT," + KEY_APP_THIRD + " TEXT"
//                + ")";
//
//        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        // Drop older table if existed
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
//        // Create tables again
//        onCreate(sqLiteDatabase);
//    }
//
//    public String getCurrentMailLogin() {
//        return mSharePre.getString(KEY_MAIL, null);
//    }
//
//    void setCurrentMailLogin(String mailLogin) {
//        Log.d(TAG, "setCurrentMailLogin: "+mailLogin);
//        SharedPreferences.Editor edit = mSharePre.edit();
//        edit.putString(KEY_MAIL, mailLogin);
//        edit.commit();
//    }
//
//    private void deleteMail() {
//        Log.d(TAG, "deleteMail: ");
//        SharedPreferences.Editor edit = mSharePre.edit();
//        edit.remove(KEY_MAIL);
//        edit.commit();
//    }
//
//
//    private boolean isHasDataDefault() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT * FROM " + TABLE_PROFILE + " WHERE " + KEY_MAIL + " = " +"'" +MAIL_DEFAULT+"'";
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * All CRUD(Create, Read, Update, Delete) Operations
//     */
//
//    void addProfile(ProfileEntity profileEntity) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_LAST_NAME, profileEntity.getLastName());
//        values.put(KEY_FIRST_NAME, profileEntity.getFirstName());
//        values.put(KEY_MAIL, profileEntity.getMail());
//        values.put(KEY_IMAGE_USER, profileEntity.getImage());
//        values.put(KEY_PASS, profileEntity.getPass());
//        values.put(KEY_BT_LOGIN, profileEntity.getBTLogin());
//        values.put(KEY_TOTAL_AFTER, profileEntity.getTimeUsedEntily().getTotalAfter());
//        values.put(KEY_LAST_LOGIN, profileEntity.getTimeUsedEntily().getTimeLogin());
//
//        values.put(KEY_SEAT_HANDLE, profileEntity.getSeatHandle());
//        values.put(KEY_CLIMATE, profileEntity.getClimate());
//        values.put(KEY_DRIVING_CLUSTER, profileEntity.getDrivingCluster());
//        values.put(KEY_WIDGET, profileEntity.getWidget());
//        values.put(KEY_GLOBAL_MENU, profileEntity.getGlobalMenu());
//        values.put(KEY_NAVIGATION, profileEntity.getNavigation());
//        values.put(KEY_MEDIA, profileEntity.getMedia());
//        values.put(KEY_CALENDAR, profileEntity.getCalendar());
//        values.put(KEY_GOOGLE_NOW, profileEntity.getGoogleNow());
//        values.put(KEY_WARNING_ALERT, profileEntity.getWarningAlert());
//        values.put(KEY_VALUE_ITEM_VIEW_WIDGET_FIRST, profileEntity.getmWidgetFirst());
//        values.put(KEY_VALUE_ITEM_VIEW_WIDGET_SECOND, profileEntity.getmWidgetSecond());
//        values.put(KEY_VALUE_ITEM_VIEW_WIDGET_THIRD, profileEntity.getmWidgetThird());
//        values.put(KEY_STRONG_WIND, profileEntity.getStrongWind());
//        values.put(KEY_CLIMATE_DRIVER, profileEntity.getClimateDriver());
//        values.put(KEY_CLIMATE_PASSENGER, profileEntity.getClimatePassenger());
//        values.put(KEY_CLIMATE_FRONT_WINDOW, profileEntity.getFrontWindow());
//        values.put(KEY_CLIMATE_REAR_WINDOW, profileEntity.getRearWindow());
//        values.put(KEY_CLIMATE_AIR_MODE, profileEntity.getAirMode());
//        values.put(KEY_CLIMATE_AUTO_MODE, profileEntity.getAutoMode());
//        values.put(KEY_CLIMATE_AC_MODE, profileEntity.getACMode());
//        values.put(KEY_CLIMATE_SYNC_MODE, profileEntity.getSyncMode());
//        values.put(KEY_CLIMATE_ECO_MODE, profileEntity.getEcoMode());
//        values.put(KEY_CLIMATE_DRIVER_SEAT, profileEntity.getDriverSeat());
//        values.put(KEY_CLIMATE_PASSENGER_SEAT, profileEntity.getPassengerSeat());
//        values.put(KEY_CLIMATE_WIND_DIRECTION_1, profileEntity.getWindDirection1());
//        values.put(KEY_CLIMATE_WIND_DIRECTION_2, profileEntity.getWindDirection2());
//
//        values.put(KEY_APP_FIRST, profileEntity.getAppFirst());
//        values.put(KEY_APP_SECOND, profileEntity.getAppSecond());
//        values.put(KEY_APP_THIRD, profileEntity.getAppThird());
//
//        if (isHasDataDefault()) {
//            if (profileEntity.getClimate() == 1) {
//                values.putAll(getContentValuesDefault(getProfileEnityDefault(), ARR_COLUMN_CLIMATE));
//            }
//            if (profileEntity.getNavigation() == 1) {
//                values.putAll(getContentValuesDefault(getProfileEnityDefault(), ARR_COLUMN_NAVI));
//            }
//
//            if (profileEntity.getWidget() == 1) {
//                values.putAll(getContentValuesDefault(getProfileEnityDefault(), ARR_COLUMN_WIDGET));
//            }
//        }
//
//        // Inserting Row
//        db.insert(TABLE_PROFILE, null, values);
//        db.close(); // Closing database connection
//
//    }
//
//    //logout all
//    public void logOutAll() {
//        deleteMail();
//        mContext.getContentResolver().notifyChange(ProfileContentProvider.CONTENT_URI_CLIMATE, null);
//        mContext.getContentResolver().notifyChange(ProfileContentProvider.CONTENT_URI_NAVI, null);
//        mContext.getContentResolver().notifyChange(ProfileContentProvider.CONTENT_URI_WIDGET, null);
//
//    }
//
//    // Getting single product
//    public ProfileEntity getProfileEntity(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_PROFILE,
//                null, KEY_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        ProfileEntity profileEntity = new ProfileEntity(new TimeUsedEntity(0, 0));
//        profileEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//        profileEntity.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
//        profileEntity.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
//        profileEntity.setMail(cursor.getString(cursor.getColumnIndex(KEY_MAIL)));
//        profileEntity.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_USER)));
//        profileEntity.setPass(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
//        profileEntity.setBTLogin(cursor.getInt(cursor.getColumnIndex(KEY_BT_LOGIN)));
//        profileEntity.setSeatHandle(cursor.getInt(cursor.getColumnIndex(KEY_SEAT_HANDLE)));
//        profileEntity.setClimate(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE)));
//        profileEntity.setDrivingCluster(cursor.getInt(cursor.getColumnIndex(KEY_DRIVING_CLUSTER)));
//        profileEntity.setWidget(cursor.getInt(cursor.getColumnIndex(KEY_WIDGET)));
//        profileEntity.setGlobalMenu(cursor.getInt(cursor.getColumnIndex(KEY_GLOBAL_MENU)));
//        profileEntity.setNavigation(cursor.getInt(cursor.getColumnIndex(KEY_NAVIGATION)));
//        profileEntity.setMedia(cursor.getInt(cursor.getColumnIndex(KEY_MEDIA)));
//        profileEntity.setCalendar(cursor.getInt(cursor.getColumnIndex(KEY_CALENDAR)));
//        profileEntity.setGoogleNow(cursor.getInt(cursor.getColumnIndex(KEY_GOOGLE_NOW)));
//        profileEntity.setWarningAlert(cursor.getInt(cursor.getColumnIndex(KEY_WARNING_ALERT)));
//
//        profileEntity.setmWidgetFirst(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_FIRST)));
//        profileEntity.setmWidgetSecond(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_SECOND)));
//        profileEntity.setmWidgetThird(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_THIRD)));
//
//        profileEntity.setStrongWind(cursor.getInt(cursor.getColumnIndex(KEY_STRONG_WIND)));
//        profileEntity.setClimateDriver(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER)));
//        profileEntity.setClimatePassenger(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER)));
//        profileEntity.setFrontWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_FRONT_WINDOW)));
//        profileEntity.setRearWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_REAR_WINDOW)));
//        profileEntity.setAirMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AIR_MODE)));
//        profileEntity.setAutoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AUTO_MODE)));
//        profileEntity.setACMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AC_MODE)));
//        profileEntity.setSyncMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_SYNC_MODE)));
//        profileEntity.setEcoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_ECO_MODE)));
//        profileEntity.setDriverSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER_SEAT)));
//        profileEntity.setPassengerSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER_SEAT)));
//        profileEntity.setWindDirection1(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_1)));
//        profileEntity.setWindDirection2(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_2)));
//
//        profileEntity.getTimeUsedEntily().setTotalAfter(cursor.getLong(cursor.getColumnIndex(KEY_TOTAL_AFTER)));
//        profileEntity.getTimeUsedEntily().setTimeLogin(cursor.getLong(cursor.getColumnIndex(KEY_LAST_LOGIN)));
//
//        profileEntity.setAppFirst(cursor.getString(cursor.getColumnIndex(KEY_APP_FIRST)));
//        profileEntity.setAppSecond(cursor.getString(cursor.getColumnIndex(KEY_APP_SECOND)));
//        profileEntity.setAppThird(cursor.getString(cursor.getColumnIndex(KEY_APP_THIRD)));
//
//        return profileEntity;
//    }
//
//
//    //Check login
//
//
//    // Getting single product
//    boolean accLogin(String email, String pass) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_PROFILE,
//                null, KEY_MAIL + " =? and " + KEY_PASS + " =?",
//                new String[]{String.valueOf(email), String.valueOf(pass)}, null, null, null, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            setCurrentMailLogin(email);
//
//            ContentValues values = new ContentValues();
//            values.put(DataBaseHandler.KEY_LAST_LOGIN, new Timestamp(System.currentTimeMillis()).getTime());
//            updateCurrentProfileLogin(values);
//            return true;
//
//        }
//
//        return false;
//    }
//
//
//    // Getting single product
//    ProfileEntity getProfileEntity(String email) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_PROFILE,
//                null, KEY_MAIL + " =?",
//                new String[]{String.valueOf(email)}, null, null, null, null);
//        ProfileEntity profileEntity = new ProfileEntity(new TimeUsedEntity(0, 0));
//
//        if (cursor != null && cursor.moveToFirst()) {
//
//            profileEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//            profileEntity.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
//            profileEntity.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
//            profileEntity.setMail(cursor.getString(cursor.getColumnIndex(KEY_MAIL)));
//            profileEntity.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_USER)));
//            profileEntity.setPass(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
//            profileEntity.setBTLogin(cursor.getInt(cursor.getColumnIndex(KEY_BT_LOGIN)));
//            profileEntity.setSeatHandle(cursor.getInt(cursor.getColumnIndex(KEY_SEAT_HANDLE)));
//            profileEntity.setClimate(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE)));
//            profileEntity.setDrivingCluster(cursor.getInt(cursor.getColumnIndex(KEY_DRIVING_CLUSTER)));
//            profileEntity.setWidget(cursor.getInt(cursor.getColumnIndex(KEY_WIDGET)));
//            profileEntity.setGlobalMenu(cursor.getInt(cursor.getColumnIndex(KEY_GLOBAL_MENU)));
//            profileEntity.setNavigation(cursor.getInt(cursor.getColumnIndex(KEY_NAVIGATION)));
//            profileEntity.setMedia(cursor.getInt(cursor.getColumnIndex(KEY_MEDIA)));
//            profileEntity.setCalendar(cursor.getInt(cursor.getColumnIndex(KEY_CALENDAR)));
//            profileEntity.setGoogleNow(cursor.getInt(cursor.getColumnIndex(KEY_GOOGLE_NOW)));
//            profileEntity.setWarningAlert(cursor.getInt(cursor.getColumnIndex(KEY_WARNING_ALERT)));
//            profileEntity.setmWidgetFirst(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_FIRST)));
//            profileEntity.setmWidgetSecond(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_SECOND)));
//            profileEntity.setmWidgetThird(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_THIRD)));
//            profileEntity.setStrongWind(cursor.getInt(cursor.getColumnIndex(KEY_STRONG_WIND)));
//            profileEntity.setClimateDriver(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER)));
//            profileEntity.setClimatePassenger(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER)));
//            profileEntity.setFrontWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_FRONT_WINDOW)));
//            profileEntity.setRearWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_REAR_WINDOW)));
//            profileEntity.setAirMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AIR_MODE)));
//            profileEntity.setAutoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AUTO_MODE)));
//            profileEntity.setACMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AC_MODE)));
//            profileEntity.setSyncMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_SYNC_MODE)));
//            profileEntity.setEcoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_ECO_MODE)));
//            profileEntity.setDriverSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER_SEAT)));
//            profileEntity.setPassengerSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER_SEAT)));
//            profileEntity.setWindDirection1(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_1)));
//            profileEntity.setWindDirection2(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_2)));
//
//            profileEntity.getTimeUsedEntily().setTotalAfter(cursor.getLong(cursor.getColumnIndex(KEY_TOTAL_AFTER)));
//            profileEntity.getTimeUsedEntily().setTimeLogin(cursor.getLong(cursor.getColumnIndex(KEY_LAST_LOGIN)));
//
//            profileEntity.setAppFirst(cursor.getString(cursor.getColumnIndex(KEY_APP_FIRST)));
//            profileEntity.setAppSecond(cursor.getString(cursor.getColumnIndex(KEY_APP_SECOND)));
//            profileEntity.setAppThird(cursor.getString(cursor.getColumnIndex(KEY_APP_THIRD)));
//        }
//        cursor.close();
//        db.close();
//
//
//        return profileEntity;
//    }
//
//    Cursor getProfileEnityDefault() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_PROFILE, null
//                , KEY_MAIL + " =?",
//                new String[]{MAIL_DEFAULT}, null, null, null, null);
//        return cursor;
//    }
//
//    Cursor getProfileEntityLogining() {
//        Cursor cursor = null;
//
//        String mail = getCurrentMailLogin();
//        Log.d(TAG, "getProfileEntityLogining: "+mail);
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (mail != null) {
//
//            cursor = db.query(TABLE_PROFILE,
//                    null
//                    , KEY_MAIL + " =?",
//                    new String[]{mail}, null, null, null, null);
//            if (!cursor.moveToFirst()) {
//                Log.d(TAG, "getProfileEntityLogining: dèaidfsdfsdf");
//                cursor = getProfileEnityDefault();
//            } else {
//                if (cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE)) != 1) {
//                    synchronousDataDefaultToDataUser(ARR_COLUMN_CLIMATE);
//                }
//                if (cursor.getInt(cursor.getColumnIndex(KEY_NAVIGATION)) != 1) {
//                    synchronousDataDefaultToDataUser(ARR_COLUMN_NAVI);
//                }
//                if (cursor.getInt(cursor.getColumnIndex(KEY_WIDGET)) != 1) {
//                    synchronousDataDefaultToDataUser(ARR_COLUMN_WIDGET);
//                }
//            }
//        } else {
//            cursor = getProfileEnityDefault();
//        }
//
//        return cursor;
//    }
//
//
//    //get climeta
//    Cursor getClimateProfileEntityLogining() {
//        Cursor cursor;
//        String mail = getCurrentMailLogin();
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (mail != null) {
//
//            cursor = db.query(TABLE_PROFILE,
//                    ARR_COLUMN_CLIMATE
//                    , KEY_MAIL + " =? AND " + KEY_CLIMATE + " =?",
//                    new String[]{mail, String.valueOf(1)}, null, null, null, null);
//            if (!cursor.moveToFirst()) {
//                cursor = getProfileEnityDefault();
//            }
//        } else {
//            cursor = getProfileEnityDefault();
//        }
//        return cursor;
//    }
//
//    private void synchronousDataDefaultToDataUser(String[] listCulumn) {
//        String mail = getCurrentMailLogin();
//        if (mail != null) {
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor cursorDefault = db.query(TABLE_PROFILE, null
//                    , KEY_MAIL + " =?",
//
//                    new String[]{MAIL_DEFAULT}, null, null, null, null);
//
//            cursorDefault.moveToFirst();
//            ContentValues values = new ContentValues();
//            for (int i = 0; i < listCulumn.length; i++) {
//                if(!listCulumn[i].equals(KEY_MAIL)) {
//                    switch (cursorDefault.getType(cursorDefault.getColumnIndexOrThrow(listCulumn[i]))) {
//                        case Cursor.FIELD_TYPE_INTEGER:
//                            values.put(listCulumn[i], cursorDefault.getInt(cursorDefault.getColumnIndexOrThrow(listCulumn[i])));
//                            break;
//                        case Cursor.FIELD_TYPE_STRING:
//                            values.put(listCulumn[i], cursorDefault.getString(cursorDefault.getColumnIndexOrThrow(listCulumn[i])));
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//
//            updateCurrentProfileLogin(values);
//            cursorDefault.close();
//        }
//    }
//
//    private ContentValues getContentValuesDefault(Cursor cursorDefault, String[] listCulumn) {
//        cursorDefault.moveToFirst();
//        ContentValues values = new ContentValues();
//        for (int i = 0; i < listCulumn.length; i++) {
//            if(!listCulumn[i].equals(KEY_MAIL)) {
//                switch (cursorDefault.getType(cursorDefault.getColumnIndexOrThrow(listCulumn[i]))) {
//                    case Cursor.FIELD_TYPE_INTEGER:
//                        values.put(listCulumn[i], cursorDefault.getInt(cursorDefault.getColumnIndexOrThrow(listCulumn[i])));
//                        break;
//                    case Cursor.FIELD_TYPE_STRING:
//                        values.put(listCulumn[i], cursorDefault.getString(cursorDefault.getColumnIndexOrThrow(listCulumn[i])));
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//        }
//        return values;
//    }
//
//    //get navi
//    Cursor getNavigationProfileEntityLogining() {
//        Cursor cursor = null;
//        String mail = getCurrentMailLogin();
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (mail != null) {
//
//            cursor = db.query(TABLE_PROFILE,
//                    null
//                    , KEY_MAIL + " =? AND " + KEY_NAVIGATION + " =?",
//                    new String[]{mail, String.valueOf(1)}, null, null, null, null);
//            if (!cursor.moveToFirst()) {
//                cursor = getProfileEnityDefault();
//            }
//        } else {
//            cursor = getProfileEnityDefault();
//        }
//        return cursor;
//    }
//
//    //get Widget
//    Cursor getWidgetProfileEntityLogining() {
//        Cursor cursor = null;
//        String mail = getCurrentMailLogin();
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (mail != null) {
//
//            cursor = db.query(TABLE_PROFILE,
//                    null
//                    , KEY_MAIL + " =? AND " + KEY_WIDGET + " =?",
//                    new String[]{mail, String.valueOf(1)}, null, null, null, null);
//            if (!cursor.moveToFirst()) {
//                cursor = getProfileEnityDefault();
//            }
//        } else {
//            cursor = getProfileEnityDefault();
//        }
//        return cursor;
//    }
//
//
//    public boolean isExitsProfileLoginNoDefault() {
//        if (getCurrentMailLogin() != null) {
//            return true;
//
//        }
//        return false;
//    }
//
//    public ProfileEntity getProfileEntityLoginingNoDefaultObj() {
//
//        String mail = getCurrentMailLogin();
//        ProfileEntity profileEntity = null;
//        if (mail != null) {
//            profileEntity = new ProfileEntity(new TimeUsedEntity(0, 0));
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor cursor = db.query(TABLE_PROFILE,
//                    null
//                    , KEY_MAIL + " =?",
//                    new String[]{mail}, null, null, null, null);
//            if (cursor.moveToFirst()) {
//                profileEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//                profileEntity.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
//                profileEntity.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
//                profileEntity.setMail(cursor.getString(cursor.getColumnIndex(KEY_MAIL)));
//                profileEntity.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_USER)));
//                profileEntity.setPass(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
//                profileEntity.setBTLogin(cursor.getInt(cursor.getColumnIndex(KEY_BT_LOGIN)));
//                profileEntity.setSeatHandle(cursor.getInt(cursor.getColumnIndex(KEY_SEAT_HANDLE)));
//                profileEntity.setClimate(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE)));
//                profileEntity.setDrivingCluster(cursor.getInt(cursor.getColumnIndex(KEY_DRIVING_CLUSTER)));
//                profileEntity.setWidget(cursor.getInt(cursor.getColumnIndex(KEY_WIDGET)));
//                profileEntity.setGlobalMenu(cursor.getInt(cursor.getColumnIndex(KEY_GLOBAL_MENU)));
//                profileEntity.setNavigation(cursor.getInt(cursor.getColumnIndex(KEY_NAVIGATION)));
//                profileEntity.setMedia(cursor.getInt(cursor.getColumnIndex(KEY_MEDIA)));
//                profileEntity.setCalendar(cursor.getInt(cursor.getColumnIndex(KEY_CALENDAR)));
//                profileEntity.setGoogleNow(cursor.getInt(cursor.getColumnIndex(KEY_GOOGLE_NOW)));
//                profileEntity.setWarningAlert(cursor.getInt(cursor.getColumnIndex(KEY_WARNING_ALERT)));
//                profileEntity.setmWidgetFirst(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_FIRST)));
//                profileEntity.setmWidgetSecond(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_SECOND)));
//                profileEntity.setmWidgetThird(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_THIRD)));
//                profileEntity.setStrongWind(cursor.getInt(cursor.getColumnIndex(KEY_STRONG_WIND)));
//                profileEntity.setClimateDriver(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER)));
//                profileEntity.setClimatePassenger(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER)));
//                profileEntity.setFrontWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_FRONT_WINDOW)));
//                profileEntity.setRearWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_REAR_WINDOW)));
//                profileEntity.setAirMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AIR_MODE)));
//                profileEntity.setAutoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AUTO_MODE)));
//                profileEntity.setACMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AC_MODE)));
//                profileEntity.setSyncMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_SYNC_MODE)));
//                profileEntity.setEcoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_ECO_MODE)));
//                profileEntity.setDriverSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER_SEAT)));
//                profileEntity.setPassengerSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER_SEAT)));
//                profileEntity.setWindDirection1(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_1)));
//                profileEntity.setWindDirection2(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_2)));
//
//
//                profileEntity.getTimeUsedEntily().setTotalAfter(cursor.getLong(cursor.getColumnIndex(KEY_TOTAL_AFTER)));
//                profileEntity.getTimeUsedEntily().setTimeLogin(cursor.getLong(cursor.getColumnIndex(KEY_LAST_LOGIN)));
//
//                profileEntity.setAppFirst(cursor.getString(cursor.getColumnIndex(KEY_APP_FIRST)));
//                profileEntity.setAppSecond(cursor.getString(cursor.getColumnIndex(KEY_APP_SECOND)));
//                profileEntity.setAppThird(cursor.getString(cursor.getColumnIndex(KEY_APP_THIRD)));
//                db.close();
//            } else {
//                return null;
//            }
//        }
//        return profileEntity;
//    }
//
//    // Getting All Product
//    ArrayList<ProfileEntity> getAllProfile() {
//
//        ArrayList<ProfileEntity> profileList = new ArrayList<>();
//        // Select All Query
//        String selectQuery = "SELECT * FROM " + TABLE_PROFILE + " ORDER BY " + KEY_LAST_NAME + " ASC";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                ProfileEntity profileEntity = new ProfileEntity(new TimeUsedEntity(0, 0));
//                profileEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//                profileEntity.setLastName(cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
//                profileEntity.setFirstName(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)));
//                profileEntity.setMail(cursor.getString(cursor.getColumnIndex(KEY_MAIL)));
//                profileEntity.setImage(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_USER)));
//                profileEntity.setPass(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
//                profileEntity.setBTLogin(cursor.getInt(cursor.getColumnIndex(KEY_BT_LOGIN)));
//                profileEntity.setSeatHandle(cursor.getInt(cursor.getColumnIndex(KEY_SEAT_HANDLE)));
//                profileEntity.setClimate(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE)));
//                profileEntity.setDrivingCluster(cursor.getInt(cursor.getColumnIndex(KEY_DRIVING_CLUSTER)));
//                profileEntity.setWidget(cursor.getInt(cursor.getColumnIndex(KEY_WIDGET)));
//                profileEntity.setGlobalMenu(cursor.getInt(cursor.getColumnIndex(KEY_GLOBAL_MENU)));
//                profileEntity.setNavigation(cursor.getInt(cursor.getColumnIndex(KEY_NAVIGATION)));
//                profileEntity.setMedia(cursor.getInt(cursor.getColumnIndex(KEY_MEDIA)));
//                profileEntity.setCalendar(cursor.getInt(cursor.getColumnIndex(KEY_CALENDAR)));
//                profileEntity.setGoogleNow(cursor.getInt(cursor.getColumnIndex(KEY_GOOGLE_NOW)));
//                profileEntity.setWarningAlert(cursor.getInt(cursor.getColumnIndex(KEY_WARNING_ALERT)));
//                profileEntity.setmWidgetFirst(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_FIRST)));
//                profileEntity.setmWidgetSecond(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_SECOND)));
//                profileEntity.setmWidgetThird(cursor.getInt(cursor.getColumnIndex(KEY_VALUE_ITEM_VIEW_WIDGET_THIRD)));
//                profileEntity.setStrongWind(cursor.getInt(cursor.getColumnIndex(KEY_STRONG_WIND)));
//                profileEntity.setClimateDriver(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER)));
//                profileEntity.setClimatePassenger(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER)));
//                profileEntity.setFrontWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_FRONT_WINDOW)));
//                profileEntity.setRearWindow(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_REAR_WINDOW)));
//                profileEntity.setAirMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AIR_MODE)));
//                profileEntity.setAutoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AUTO_MODE)));
//                profileEntity.setACMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_AC_MODE)));
//                profileEntity.setSyncMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_SYNC_MODE)));
//                profileEntity.setEcoMode(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_ECO_MODE)));
//                profileEntity.setDriverSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_DRIVER_SEAT)));
//                profileEntity.setPassengerSeat(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_PASSENGER_SEAT)));
//                profileEntity.setWindDirection1(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_1)));
//                profileEntity.setWindDirection2(cursor.getInt(cursor.getColumnIndex(KEY_CLIMATE_WIND_DIRECTION_2)));
//                if (profileEntity.getTimeUsedEntily() == null) {
//                    profileEntity.setTimeUsedEntily(new TimeUsedEntity(0, 0));
//                }
//                profileEntity.getTimeUsedEntily().setTotalAfter(cursor.getLong(cursor.getColumnIndex(KEY_TOTAL_AFTER)));
//                profileEntity.getTimeUsedEntily().setTimeLogin(cursor.getLong(cursor.getColumnIndex(KEY_LAST_LOGIN)));
//
//                profileEntity.setAppFirst(cursor.getString(cursor.getColumnIndex(KEY_APP_FIRST)));
//                profileEntity.setAppSecond(cursor.getString(cursor.getColumnIndex(KEY_APP_SECOND)));
//                profileEntity.setAppThird(cursor.getString(cursor.getColumnIndex(KEY_APP_THIRD)));
//                //Adding profile to list
//                if (!TextUtils.equals(profileEntity.getMail(),MAIL_DEFAULT)) {
//                    profileList.add(profileEntity);
//                }
//
//            } while (cursor.moveToNext());
//        }
//
//        // close inserting data from database
//        db.close();
//        // return product list
//        return profileList;
//    }
//
//
//    //Updating single Profile
//    int updateProfile(ProfileEntity profileEntity) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Log.d(TAG, "updateProfile: google: "+profileEntity.getGoogleNow());
//        ContentValues values = new ContentValues();
//        values.put(KEY_LAST_NAME, profileEntity.getLastName());
//        values.put(KEY_FIRST_NAME, profileEntity.getFirstName());
//        values.put(KEY_MAIL, profileEntity.getMail());
//        values.put(KEY_IMAGE_USER, profileEntity.getImage());
//        values.put(KEY_PASS, profileEntity.getPass());
//        values.put(KEY_BT_LOGIN, profileEntity.getBTLogin());
//        values.put(KEY_SEAT_HANDLE, profileEntity.getSeatHandle());
//        values.put(KEY_CLIMATE, profileEntity.getClimate());
//        values.put(KEY_DRIVING_CLUSTER, profileEntity.getDrivingCluster());
//        values.put(KEY_WIDGET, profileEntity.getWidget());
//        values.put(KEY_GLOBAL_MENU, profileEntity.getGlobalMenu());
//        values.put(KEY_NAVIGATION, profileEntity.getNavigation());
//        values.put(KEY_MEDIA, profileEntity.getMedia());
//        values.put(KEY_CALENDAR, profileEntity.getCalendar());
//        values.put(KEY_GOOGLE_NOW, profileEntity.getGoogleNow());
//        values.put(KEY_WARNING_ALERT, profileEntity.getWarningAlert());
//
//        values.put(KEY_TOTAL_AFTER, profileEntity.getTimeUsedEntily().getTotalAfter());
//        values.put(KEY_LAST_LOGIN, profileEntity.getTimeUsedEntily().getTimeLogin());
//
//
//        // updating row
//        return db.update(TABLE_PROFILE, values, KEY_MAIL + " = ?",
//                new String[]{String.valueOf(profileEntity.getMail())});
//    }
//
//
//    //Updating single Profile logining
//    int updateCurrentProfileLogin(ContentValues values) {
//        String mail = getCurrentMailLogin();
//        int rowNumber = -1;
//        SQLiteDatabase db = this.getWritableDatabase();
//        if (mail != null) {
//
//            // updating row
//            rowNumber = db.update(TABLE_PROFILE, values, KEY_MAIL + " = ?",
//                    new String[]{mail});
//            if (rowNumber < 1) {
//                rowNumber = updateToDataDefault(db, values);
//            } else {
//                updateToDataDefault(db, values);
//            }
//        } else {
//            rowNumber = updateToDataDefault(db, values);
//        }
//       //   db.close();
//        return rowNumber;
//    }
//
//    // updated climate
//    int updateClimateCurrentProfileLogin(ContentValues values) {
//        String mail = getCurrentMailLogin();
//        int rowNumber = -1;
//        SQLiteDatabase db = this.getWritableDatabase();
//        if (mail != null) {
//            // updating row
//            rowNumber = db.update(TABLE_PROFILE, values, KEY_MAIL + " = ? AND " + KEY_CLIMATE + " =?",
//                    new String[]{mail, String.valueOf(1)});
//            if (rowNumber < 1) {
//                rowNumber = updateToDataDefault(db, values);
//                db.update(TABLE_PROFILE, values, KEY_MAIL + " = ? ",
//                        new String[]{mail});
//            } else {
//                // if login mail current and personalized Climate. so update data default
//                updateToDataDefault(db, values);
//            }
//        } else {
//            rowNumber = updateToDataDefault(db, values);
//        }
//        //  db.close();
//        return rowNumber;
//    }
//
//    // updated Navigation
//    int updateNavigationCurrentProfileLogin(ContentValues values) {
//        String mail = getCurrentMailLogin();
//        int rowNumber = -1;
//        SQLiteDatabase db = this.getWritableDatabase();
//        if (mail != null) {
//            // updating row
//            rowNumber = db.update(TABLE_PROFILE, values, KEY_MAIL + " = ? AND " + KEY_NAVIGATION + " =?",
//                    new String[]{mail, String.valueOf(1)});
//            if (rowNumber < 1) {
//                rowNumber = updateToDataDefault(db, values);
//                db.update(TABLE_PROFILE, values, KEY_MAIL + " = ? ",
//                        new String[]{mail});
//            } else {
//                // if login mail current and personalized Navi. so update data default
//                updateToDataDefault(db, values);
//            }
//        } else {
//            rowNumber = updateToDataDefault(db, values);
//        }
//        //    db.close();
//        return rowNumber;
//    }
//
//    // updated WIDGET
//    int updateWidgetCurrentProfileLogin(ContentValues values) {
//        String mail = getCurrentMailLogin();
//        int rowNumber = -1;
//        SQLiteDatabase db = this.getWritableDatabase();
//        if (mail != null) {
//            // updating row
//            rowNumber = db.update(TABLE_PROFILE, values, KEY_MAIL + " = ? AND " + KEY_WIDGET + " =?",
//                    new String[]{mail, String.valueOf(1)});
//            if (rowNumber < 1) {
//                rowNumber = updateToDataDefault(db, values);
//                db.update(TABLE_PROFILE, values, KEY_MAIL + " = ? ",
//                        new String[]{mail});
//            } else {
//                // if login mail current and personalized widget. so update data default
//                updateToDataDefault(db, values);
//            }
//        } else {
//            rowNumber = updateToDataDefault(db, values);
//        }
//        //  db.close();
//        return rowNumber;
//    }
//
//
//    // Delete single profile
//    void deleteProfile(ProfileEntity profileEntity) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_PROFILE, KEY_ID + " = ?",
//                new String[]{Integer.toString(profileEntity.getId())});
//        db.close();
//        deleteMail();
//    }
//
//    private int updateToDataDefault(SQLiteDatabase db, ContentValues
//            values) {
//        return db.update(TABLE_PROFILE, values, KEY_MAIL + " = ?",
//                new String[]{String.valueOf(MAIL_DEFAULT)});
//    }
//}
